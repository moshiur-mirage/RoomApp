/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moshiur.room;

import com.moshiur.room.Room;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MIRAGE
 */
@Repository
@Transactional
public class RoomRepository implements RoomService{

   @Autowired
    SessionFactory sessionFactory;
    
    
    @Override
    public List<Room> viewAllRoom() {
        Session session = sessionFactory.getCurrentSession();
        List<Room> roomList = session.createQuery("from Room").list();
        return roomList;    
    }

    @Override
    public Room viewOneRoom(int id) {
        Session session = sessionFactory.getCurrentSession();
        Room room = (Room)session.get(Room.class, id);
        return room;
    
    }

    @Override
    public Room saveRoom(Room room) {
        Session session = sessionFactory.getCurrentSession();
        session.save(room);
        return room;
    }

    @Override
    public void updateRoom(Room room) {
        Session session = sessionFactory.getCurrentSession();
        session.update(room);
    }

    @Override
    public void deleteRoom(int id) {
        Session session = sessionFactory.getCurrentSession();
        Room room = (Room)session.get(Room.class, id);
        session.delete(room);  }

    @Override
    public List<Room> viewByType(String type) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Room.class);
        crit.add(Restrictions.eq("type", type));
        List<Room> allRooms = crit.list();
        return allRooms;
    
    }

    @Override
    public List<Room> viewBySize(String size) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Room.class);
        crit.add(Restrictions.eq("size", size));
        List<Room> allRooms = crit.list();
        return allRooms;
               
    
    }
    
}
