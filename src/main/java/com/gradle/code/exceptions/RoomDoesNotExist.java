package com.gradle.code.exceptions;

public class RoomDoesNotExist extends RuntimeException{
    public RoomDoesNotExist(){
        super("Room does not exist!");
    }
}
