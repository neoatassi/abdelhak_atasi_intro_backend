package com.gradle.code.services;

import com.gradle.code.Project;

import java.util.Collection;

public interface ProjectService {
    Project createProject(String name);

    Project createProject(Project project);

    void removeProject (int projectID);

    Collection<Project> getProjects();

    void printOutProjects();

    void addFloorToProject(Project project);

    void addFloorToProject(int projectID);

    void removeFloor(Project project, int level);

    void removeFloor(int projectId, int level);
}
