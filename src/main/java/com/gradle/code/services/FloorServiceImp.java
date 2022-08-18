package com.gradle.code.services;

import com.gradle.code.Project;
import com.gradle.code.Room;
import com.gradle.code.exceptions.RoomDoesNotExist;
import org.springframework.stereotype.Service;

@Service
public class FloorServiceImp implements FloorService {

    /**
     * Unique ID for each floor
     */
    int roomIdCounter = 0;

    public RoomService RoomService;

    /**
     * When a project is created from ProjectService, it automatically calls a constructor to
     * create an instance of FloorService, this way we end up with each project containing at least
     * one floor with one room
     */
    public FloorServiceImp(){
        RoomService = new RoomServiceImp();
        //this.addRoomToFloor(project, 0 );
    }

    @Override
    public Room addRoomToFloor(Project project, int floorLevel){
        Room addedRoom = project.getFloors().get(floorLevel).addRoom(roomIdCounter++);
        return addedRoom;
    }

    @Override
    public Room removeRoomFromFloor(Project project, int floorLevel, int roomId){
        if(!project.getFloors().get(floorLevel).getRooms().containsKey(roomId)) throw new RoomDoesNotExist();

        Room roomToDelete = project.getFloors().get(floorLevel).getRooms().get(roomId);
        project.getFloors().get(floorLevel).getRooms().remove(roomId);
        return roomToDelete;
    }


    @Override
    public Room getRoomById(Project project, int floorLevel, int roomId){
        return project.getFloor(floorLevel).getRoom(roomId);
    }

    @Override
    public RoomService getRoomService(){
        return this.RoomService;
    }
}
