package com.gradle.code.exceptions;

public class FloorDoesNotExist extends RuntimeException{
    public FloorDoesNotExist(){
        super("Floor does not exist!");
    }
}
