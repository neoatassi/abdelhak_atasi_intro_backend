package com.gradle.code.repos;

import com.gradle.code.Project;

import java.util.Collection;

public interface ProjectsRepo {
    void addProject(int projectIdCounter, Project newProject);

    boolean containsProject(int projectID);

    void removeProject(int projectID);

    Collection<Project> getAllProjects();

    Project getById(int projectID);
}
