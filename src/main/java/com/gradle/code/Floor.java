package com.gradle.code;

import java.util.HashMap;

public class Floor {
    int floorID;
    int floorLevel;
    HashMap<Integer, Room> rooms;

    public Floor(int floor_id){
        this.floorID = floor_id;
        rooms = new HashMap<>();
    }

    public int getFloorID(){
        return this.floorID;
    }

    public void setFloorID(int floorID){
        this.floorID = floorID;
    }

    public int getFloorLevel(){
        return this.floorLevel;
    }

    public void setFloorLevel(int floorLevel){
        this.floorLevel = floorLevel;
    }

    public Room addRoom(int roomID){
        Room newRoom = new Room(roomID);
        this.rooms.put(roomID, newRoom);
        return newRoom;
    }

    public Room getRoom(int roomID){
        return rooms.get(roomID);
    }

    public HashMap<Integer, Room> getRooms(){
        return this.rooms;
    }
}
