package com.gradle.code.controllers;

import com.gradle.code.Floor;
import com.gradle.code.Project;
import com.gradle.code.Room;
import com.gradle.code.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class Controller {

    final
    ProjectService projectService;

    @Autowired
    public Controller(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    @ResponseBody
    public String helloName(@RequestParam(name = "name") String name){
        return "Hello" + name;
    }

    @GetMapping("/create_project_by_name")
    @ResponseBody
    public Project createProject(@RequestParam(name = "name") String projectName){
        return projectService.createProject(projectName);
    }

    @GetMapping("/clone_project")
    @ResponseBody
    public Project cloneProject(@RequestParam Project project){
        return projectService.cloneProject(project);
    }

    @GetMapping("/remove_project")
    public Project removeProject(@RequestParam(name = "id") int projectID){
        return projectService.removeProject(projectID);
    }

    @GetMapping("/print_out_all_projects")
    public String[] printOutProjects(){
        var allProjects =  projectService.getProjects();
        var allPs = allProjects.stream().map(project -> project.getProjectName()).toArray(s -> new String[s]);
        return allPs;
    }

    @GetMapping("/get_all_projects")
    public Collection<Project> getAllProjects(){
        return projectService.getProjects();
    }

    @GetMapping("/add_floor_to_project")
    public Floor addFloorToProject (@RequestParam int projectId){
        return projectService.addFloorToProject(projectId);
    }

    @GetMapping("/remove_floor")
    public Floor removeFloor(@RequestParam int projectId, @RequestParam int level){
        return projectService.removeFloor(projectId, level);
    }

    @GetMapping("/add_room_to_floor")
    public Room addRoomToFloor(@RequestParam int projectId, @RequestParam int floorLevel){
        var myProject = projectService.getById(projectId);
        return projectService.getFloorService().addRoomToFloor(myProject, floorLevel);
    }

    @GetMapping("/remove_room")
    public Room remove(@RequestParam int projectId, @RequestParam int floorLevel, @RequestParam int roomId){
        var myProject = projectService.getById(projectId);
        return projectService.getFloorService().removeRoomFromFloor(myProject, floorLevel, roomId);
    }

    @GetMapping("/add_media_to_room")
    public String addMediaToRoom(@RequestParam int projectId,
                                 @RequestParam int floorLevel,
                                 @RequestParam int roomId,
                                 @RequestParam String input)
    {
        var myProject = projectService.getById(projectId);
        return projectService.getFloorService().getRoomService().addMediaToRoom(myProject, floorLevel, roomId, input);
    }

    @GetMapping("/remove_media_from_room")
    public String removeMediaFromRoom(@RequestParam int projectId,
                                      @RequestParam int floorLevel,
                                      @RequestParam int roomId,
                                      @RequestParam String input)
    {
        var myProject = projectService.getById(projectId);
        return projectService.getFloorService().getRoomService().removeMediaFromRoom(myProject, floorLevel, roomId, input);
    }

}
