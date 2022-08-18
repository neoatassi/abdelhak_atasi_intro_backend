package com.gradle.code.services;

import com.gradle.code.Project;

public interface RoomService {
    String addMediaToRoom(Project project, int floorLevel, int roomId, String input);

    String removeMediaFromRoom(Project project, int floorLevel, int roomId, String input);
}
