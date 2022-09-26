package com.app.dojo.repositories;


import com.app.dojo.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    public Optional<Room> findByRoomName(String roomName);
    public Optional<Room> findById(Long id);
    public List<Room> findAll();
    public void deleteById(Long id);
}
