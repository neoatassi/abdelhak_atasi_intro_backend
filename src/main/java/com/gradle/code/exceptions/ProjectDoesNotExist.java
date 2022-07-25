package com.gradle.code.exceptions;

public class ProjectDoesNotExist extends RuntimeException{
    public ProjectDoesNotExist(){
        super("Project does not exist!");
    }
}
