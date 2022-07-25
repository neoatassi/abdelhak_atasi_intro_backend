package com.gradle.code;

import java.util.HashMap;

public class Project {
    String projectName;
    int projectId;
    HashMap<Integer, Floor> floors;


    public Project(String name, int projectId){
        this.projectName = name;
        this.projectId = projectId;
        floors = new HashMap<>();
    }

    public Project(Project project, int Id){
        this(project.projectName, project.projectId);
        this.projectId = Id;
        floors = new HashMap<>();
    }

    public void addFloor(int floorId){
        floors.putIfAbsent(floorId, new Floor(floorId));
    }

    public void removeFloor(int floorId){
        floors.remove(floorId);
    }

    public String getProjectName(){
        return this.projectName;
    }

    public void setProjectName(String name){
        this.projectName = name;
    }

    public int getProjectId(){
        return this.projectId;
    }

    public void setProjectId(int id){
        this.projectId = id;
    }

    public HashMap<Integer, Floor> getFloors(){
        return floors;
    }

    public void setFloors(HashMap<Integer, Floor> floors){
        this.floors = floors;
    }

    public int getLastLevel(){
        return this.floors.size() - 1;
    }

    public Floor getFloor(int floorLevel){
        return this.floors.get(floorLevel);
    }

}
