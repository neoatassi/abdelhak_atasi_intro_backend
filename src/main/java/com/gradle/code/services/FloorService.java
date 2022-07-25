package com.gradle.code.services;

import com.gradle.code.Project;

public interface FloorService {

    void addRoomToFloor(Project project, int floorLevel);

    void removeRoomFromFloor(Project project, int floorLevel, int roomId);
}
