package com.gradle.code.services;

import com.gradle.code.Project;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImp implements RoomService {

    @Override
    public String addMediaToRoom(Project project,
                                 int floorLevel,
                                 int roomId,
                                 String input){
        project.getFloors().get(floorLevel).getRoom(roomId).getMedia().add(input);
        return input;
    }

    @Override
    public String removeMediaFromRoom(Project project,
                                      int floorLevel,
                                      int roomId,
                                      String input){
        project.getFloors().get(floorLevel).getRoom(roomId).getMedia().remove(input);
        return input;
    }

}
