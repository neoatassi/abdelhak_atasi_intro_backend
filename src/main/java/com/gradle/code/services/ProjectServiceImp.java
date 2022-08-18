package com.gradle.code.services;


import com.gradle.code.Floor;
import com.gradle.code.Project;
import com.gradle.code.exceptions.FloorDoesNotExist;
import com.gradle.code.exceptions.ProjectDoesNotExist;
import com.gradle.code.repos.ProjectsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProjectServiceImp implements ProjectService {

    /**
     * static chronological counters for unique IDs
     */
    AtomicInteger projectIdCounter = new AtomicInteger(0);
    AtomicInteger floorIdCounter = new AtomicInteger(0);
    
    public FloorService FloorService;
    public RoomService  RoomService;
    /**
     * Map of projects managed by the service with their respective IDs
     */
   // public Map<Integer, Project> Projects;
    public ProjectsRepo projectRepo;

    @Autowired
    public ProjectServiceImp(ProjectsRepo projectRepo, FloorService floorService, RoomService roomService){
        this.projectRepo = projectRepo;
        this.FloorService = floorService;
        this.RoomService = roomService;
    }

    public void resetID(){
        projectIdCounter.set(0);
        floorIdCounter.set(0);
    }

    /**
     * Create a project with a chosen name
     * @param name
     */
    @Override
    public Project createProject(String name) {
        Project newProject = new Project(name, projectIdCounter.incrementAndGet());
        //Projects.putIfAbsent(projectIdCounter, newProject);
        projectRepo.addProject(projectIdCounter.get(), newProject);
        addFloorToProject(newProject);
        //projectIdCounter++;
        return newProject;
    }

    /**
     *  Create a clone project with a new unique ID
     * @param project
     */
    @Override
    public Project cloneProject(Project project) {
        Project newProject = new Project(project.getProjectName(), projectIdCounter.incrementAndGet());
        //Projects.putIfAbsent(projectIdCounter, newProject);
        projectRepo.addProject(projectIdCounter.get(), newProject);
        addFloorToProject(floorIdCounter.incrementAndGet());
        //projectIdCounter++;
        return newProject;
    }

    @Override
    public Project getById(int id){
        return projectRepo.getById(id);
    }

    /**
     * removes a project provided the id
     * @param projectID
     * @return
     */
    @Override
    public Project removeProject (int projectID) {
        Project toDelete = projectRepo.getById(projectID);
        if (projectRepo.containsProject(projectID)) projectRepo.removeProject(projectID);
        else throw new ProjectDoesNotExist();
        return toDelete;
    }

    
    @Override
    public Collection<Project> getProjects(){
       return this.projectRepo.getAllProjects();
    }

    
    @Override
    public void printOutProjects(){
        for (Project project : getProjects()) {
            System.out.println(project.getProjectName());
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
            Floor newFloor = new Floor(floorIdCounter.incrementAndGet());
            int floorLevel = project.getFloors().size();
            project.getFloors().putIfAbsent(floorLevel, newFloor);
            newFloor.setFloorLevel(floorLevel);
            //FloorService.addRoomToFloor(project, 0);
            FloorService.addRoomToFloor(project, floorLevel);

            //FloorService = new FloorServiceImp(project, floorLevel);
            //floorIdCounter++;
        }
        else throw new ProjectDoesNotExist();
    }

    /**
     * adds a floor to a project referenced by its ID
     * @param projectID
     * @return
     */
    @Override
    public Floor addFloorToProject(int projectID){
        if(projectRepo.containsProject(projectID)) {
            Floor newFloor = new Floor(floorIdCounter.incrementAndGet());
            int floorLevel = projectRepo.getById(projectID).getFloors().size();
            projectRepo.getById(projectID).getFloors().putIfAbsent(floorLevel, newFloor);
            newFloor.setFloorLevel(floorLevel);
            //FloorService.addRoomToFloor(projectRepo.getById(projectID), 0);
            FloorService.addRoomToFloor(projectRepo.getById(projectID), floorLevel);

            //FloorService = new FloorServiceImp(projectRepo.getById(projectID), floorLevel);
            //floorIdCounter++;
            return newFloor;
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
     * @return
     */
    @Override
    public Floor removeFloor(int projectId, int level){
        if(!projectRepo.getById(projectId).getFloors().containsKey(level)) throw new FloorDoesNotExist();
        Floor floorToDelete = projectRepo.getById(projectId).getFloors().get(level);
        projectRepo.getById(projectId).getFloors().remove(level);
        return floorToDelete;
    }

    @Override
    public FloorService getFloorService(){
        return this.FloorService;
    }

}
