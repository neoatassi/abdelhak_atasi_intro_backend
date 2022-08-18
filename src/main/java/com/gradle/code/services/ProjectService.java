package com.gradle.code.services;

import com.gradle.code.Floor;
import com.gradle.code.Project;

import java.util.Collection;

public interface ProjectService {
    Project createProject(String name);

    Project cloneProject(Project project);

    Project getById(int id);

    Project removeProject (int projectID);

    Collection<Project> getProjects();

    void printOutProjects();

    void addFloorToProject(Project project);

    Floor addFloorToProject(int projectID);

    void removeFloor(Project project, int level);

    Floor removeFloor(int projectId, int level);

    FloorService getFloorService();
}
