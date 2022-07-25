package com.gradle.code.services;

import com.gradle.code.Project;

public class RoomServiceImp implements RoomService {

    @Override
    public void addMediaToRoom(Project project, int floorLevel, int roomId, String input){
        project.getFloors().get(floorLevel).getRoom(roomId).getMedia().add(input);
    }

    @Override
    public void removeMediaFromRoom(Project project, int floorLevel, int roomId, String input){
        project.getFloors().get(floorLevel).getRoom(roomId).getMedia().remove(input);
    }

}
