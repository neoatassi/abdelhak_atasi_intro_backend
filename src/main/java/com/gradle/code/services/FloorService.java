package com.gradle.code.services;

import com.gradle.code.Project;
import com.gradle.code.Room;

public interface FloorService {

    Room addRoomToFloor(Project project, int floorLevel);

    Room removeRoomFromFloor(Project project, int floorLevel, int roomId);

    // TODO migrate to interface and continue with controller
    Room getRoomById(Project project, int floorLevel, int roomId);

    RoomService getRoomService();
}
