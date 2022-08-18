package com.gradle.code.repos;

import com.gradle.code.Project;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HashProjectsRepo implements ProjectsRepo {


    HashMap<Integer, Project> Projects = new HashMap<>();


    @Override
    public void addProject(int projectIdCounter, Project newProject) {
        Projects.putIfAbsent(projectIdCounter, newProject);
    }


    @Override
    public boolean containsProject(int projectID) {
        return Projects.containsKey(projectID);
    }

    @Override
    public void removeProject(int projectID) {
        Projects.remove(projectID);
    }

    @Override
    public Collection<Project> getAllProjects() {
        return Projects.values();
    }


    @Override
    public Project getById(int projectID) {
        return Projects.get(projectID);
    }

}
