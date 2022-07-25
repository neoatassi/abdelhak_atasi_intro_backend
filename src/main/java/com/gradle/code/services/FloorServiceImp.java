package com.gradle.code.services;

import com.gradle.code.Project;
import com.gradle.code.exceptions.RoomDoesNotExist;


public class FloorServiceImp implements FloorService {

    /**
     * Unique ID for each floor
     */
    int roomIdCounter = 0;

    public RoomServiceImp RoomService;

    /**
     * When a project is created from ProjectService, it automatically calls a constructor to
     * create an instance of FloorService, this way we end up with each project containing at least
     * one floor with one room
     * @param project
     * @param floorLevel
     */
    public FloorServiceImp(Project project, int floorLevel){
        RoomService = new RoomServiceImp();
        this.addRoomToFloor(project, 0 );
    }

    @Override
    public void addRoomToFloor(Project project, int floorLevel){
        project.getFloors().get(floorLevel).addRoom(roomIdCounter);
        roomIdCounter++;
    }

    @Override
    public void removeRoomFromFloor(Project project, int floorLevel, int roomId){
        if(!project.getFloors().get(floorLevel).getRooms().containsKey(roomId)) throw new RoomDoesNotExist();

        project.getFloors().get(floorLevel).getRooms().remove(roomId);
    }

}
