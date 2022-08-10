package com.gradle.code.services;


import com.gradle.code.Floor;
import com.gradle.code.Project;
import com.gradle.code.exceptions.FloorDoesNotExist;
import com.gradle.code.exceptions.ProjectDoesNotExist;
import com.gradle.code.repos.HashProjectsRepo;
import com.gradle.code.repos.ProjectsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProjectServiceImp implements ProjectService {

    /**
     * static chronological counters for unique IDs
     */
    static int projectIdCounter = 0;
    static int floorIdCounter = 0;
    
    public FloorServiceImp FloorService;

    /**
     * Map of projects managed by the service with their respective IDs
     */
   // public Map<Integer, Project> Projects;
    public ProjectsRepo projectRepo;

    @Autowired
    public ProjectServiceImp(ProjectsRepo projectRepo){
        this.projectRepo = projectRepo;
    }

    public void resetID(){
        projectIdCounter = 0;
        floorIdCounter = 0;
    }

    /**
     * Create a project with a chosen name
     * @param name
     */
    @Override
    public Project createProject(String name) {
        Project newProject = new Project(name, projectIdCounter);
        //Projects.putIfAbsent(projectIdCounter, newProject);
        projectRepo.addProject(projectIdCounter, newProject);
        addFloorToProject(newProject);
        projectIdCounter++;
        return newProject;
    }

    /**
     *  Create a clone project with a new unique ID
     * @param project
     */
    @Override
    public Project createProject(Project project) {
        Project newProject = new Project(project.getProjectName(), projectIdCounter);
        //Projects.putIfAbsent(projectIdCounter, newProject);
        projectRepo.addProject(projectIdCounter, newProject);
        addFloorToProject(floorIdCounter);
        projectIdCounter++;
        return newProject;
    }

    /**
     * removes a project provided the id
     * @param projectID
     */
    @Override
    public void removeProject (int projectID) {
        if (projectRepo.containsProject(projectID)) projectRepo.removeProject(projectID);
        else throw new ProjectDoesNotExist();
    }

    
    @Override
    public Collection<Project> getProjects(){
       return this.projectRepo.getAllProjects();
    }

    
    @Override
    public void printOutProjects(){
        for (Project project : getProjects()) {
            System.out.println(project);
        }
    }

    /**
     * adds a floor to a directly referenced project
     * @param project
     */
    @Override
    public void addFloorToProject(Project project) {
        // checks if project already exists in the map
        if(projectRepo.containsProject(project.getProjectId())) {
            Floor newFloor = new Floor(floorIdCounter);
            int floorLevel = project.getFloors().size();
            project.getFloors().putIfAbsent(floorLevel, newFloor);
            newFloor.setFloorLevel(floorLevel);
            FloorService = new FloorServiceImp(project, floorLevel);
            floorIdCounter++;
        }
        else throw new ProjectDoesNotExist();
    }

    /**
     * adds a floor to a project referenced by its ID
     * @param projectID
     */
    @Override
    public void addFloorToProject(int projectID){
        if(projectRepo.containsProject(projectID)) {
            Floor newFloor = new Floor(floorIdCounter);
            int floorLevel = projectRepo.getById(projectID).getFloors().size();
            projectRepo.getById(projectID).getFloors().putIfAbsent(floorLevel, newFloor);
            newFloor.setFloorLevel(floorLevel);
            FloorService = new FloorServiceImp(projectRepo.getById(projectID), floorLevel);
            floorIdCounter++;
        }
        else throw new ProjectDoesNotExist();
    }

    /**
     * removes a floor level from a directly referenced project
     * @param project
     */
    @Override
    public void removeFloor(Project project, int level){
        if(!project.getFloors().containsKey(level)) throw new FloorDoesNotExist();
        project.getFloors().remove(level);
    }

    /**
     * removes a floor level from a project referenced by its ID
     * @param projectId
     */
    @Override
    public void removeFloor(int projectId, int level){
        if(!projectRepo.getById(projectId).getFloors().containsKey(level)) throw new FloorDoesNotExist();
        projectRepo.getById(projectId).getFloors().remove(level);
    }

}
