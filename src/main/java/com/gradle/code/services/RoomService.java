package com.gradle.code.services;

import com.gradle.code.Project;

public interface RoomService {
    void addMediaToRoom(Project project, int floorLevel, int roomId, String input);

    void removeMediaFromRoom(Project project, int floorLevel, int roomId, String input);
}
