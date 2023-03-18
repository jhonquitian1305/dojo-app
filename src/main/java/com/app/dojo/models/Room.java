package com.app.dojo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String roomName;
    @ManyToMany(mappedBy = "rooms",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<GroupClass> groups;

    public Room() {
    }

    public Room(Long id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<GroupClass> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupClass> groupClasses) {
        this.groups = groupClasses;
    }
}
