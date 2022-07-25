package com.gradle.code;

import java.util.ArrayList;
import java.util.List;

public class Room {
    int room_id;
    List<String> media;

    public Room(int room_id){
        this.room_id = room_id;
        media = new ArrayList<>();
    }

    public List<String> getMedia(){
        return this.media;
    }
}
