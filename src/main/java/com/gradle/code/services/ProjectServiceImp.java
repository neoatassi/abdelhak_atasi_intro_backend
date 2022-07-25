package com.gradle.code.services;


import com.gradle.code.Floor;
import com.gradle.code.Project;
import com.gradle.code.exceptions.FloorDoesNotExist;
import com.gradle.code.exceptions.ProjectDoesNotExist;

import java.util.HashMap;
import java.util.Map;

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
    public Map<Integer, Project> Projects;

    public ProjectServiceImp(){
        Projects = new HashMap<Integer, Project>();
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
        Projects.putIfAbsent(projectIdCounter, newProject);
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
        Projects.putIfAbsent(projectIdCounter, newProject);
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
        if (Projects.containsKey(projectID)) Projects.remove(projectID);
        else throw new ProjectDoesNotExist();
    }

    
    @Override
    public Map<Integer, Project> getProjects(){
       return this.Projects;
    }

    
    @Override
    public void printOutProjects(){
        this.Projects.entrySet().forEach(integerProjectEntry -> System.out.println(integerProjectEntry.getKey() + " "
                + integerProjectEntry.getValue().getProjectId()));
    }

    /**
     * adds a floor to a directly referenced project
     * @param project
     */
    @Override
    public void addFloorToProject(Project project) {
        // checks if project already exists in the map
        if(Projects.containsValue(project)) {
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
        if(Projects.containsKey(projectID)) {
            Floor newFloor = new Floor(floorIdCounter);
            int floorLevel = Projects.get(projectID).getFloors().size();
            Projects.get(projectID).getFloors().putIfAbsent(floorLevel, newFloor);
            newFloor.setFloorLevel(floorLevel);
            FloorService = new FloorServiceImp(Projects.get(projectID), floorLevel);
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
        if(!Projects.get(projectId).getFloors().containsKey(level)) throw new FloorDoesNotExist();
        Projects.get(projectId).getFloors().remove(level);
    }

}
