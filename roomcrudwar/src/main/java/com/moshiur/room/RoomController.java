/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moshiur.room;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MIRAGE
 */
@RestController
public class RoomController {
    
    @Autowired
    RoomService roomService;
    
    @RequestMapping("/")
    public String hello(){
        return "Hello Spring Boot!!";
    }
    
    @GetMapping("/room")
    public List<Room> getAllRoom(){
        return roomService.viewAllRoom();
    }
    
    @GetMapping("room/size/{size}")
    public List<Room>getBySize(@PathVariable("size") int size){
        List roomList = roomService.viewBySize(size);
        return roomList;
    }
    
    @GetMapping("room/type/{type}")
    public List<Room>getByType(@PathVariable("type") String type){
        List roomList = roomService.viewByType(type);
        return roomList;
    }
    
    
    

    
    @PostMapping("/room")
    public Room createStudent(@RequestBody Room room){
        return roomService.saveRoom(room);
    }
    
    @PutMapping("/room/{id}")
    public ResponseEntity<Room> updateStudent(@PathVariable("id") int id, @RequestBody Room room){
        
        Room currentRoom = roomService.viewOneRoom(id);
        
        if (currentRoom == null){
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
        
        currentRoom.setSize(room.getSize());
        currentRoom.setType(room.getType());
        
        
        
        roomService.updateRoom(currentRoom);
        
        return new ResponseEntity<Room>(currentRoom, HttpStatus.OK);
    }
    
    @DeleteMapping("/room/{id}")
    public ResponseEntity<Room> deleteRoom(@PathVariable("id") int id){
         Room room = roomService.viewOneRoom(id);
        if (room == null){
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
        roomService.deleteRoom(id);
        return new ResponseEntity<Room>(HttpStatus.NO_CONTENT);
    }
    
}
