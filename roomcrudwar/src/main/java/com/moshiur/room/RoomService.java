/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moshiur.room;


import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author MIRAGE
 */
@Service
public interface RoomService {

    //get list
    public List<Room> viewAllRoom();

    //get one
    public Room viewOneRoom(int id);

    //save
    public Room saveRoom(Room room);

    //update
    public void updateRoom(Room room);

    //Delete
    public void deleteRoom(int id);

    //getByType
    public List<Room> viewByType(String type);

    //getBySize
    public List<Room> viewBySize(String size);
}
