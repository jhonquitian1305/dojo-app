package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.RoomBuilder;
import com.app.dojo.models.Room;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles(profiles = "test")
class RoomRepositoryTest {

    @Autowired
    private  RoomRepository roomRepository;

    private static Room room;
    private static RoomBuilder builder = new RoomBuilder();

    @BeforeAll
    public static void  init(){
        room= builder
                .setRoomName("201")
                .build();
    }

    @Test
    @DisplayName("Test Respository save one room in database")
    void create(){
        Room roomSaved=this.roomRepository.save(room);

        assertNotNull(roomSaved);
        assertThat(roomSaved.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test Repository find all rooms")
    void getAll(){
        // given
            roomRepository.save(room);
        // when
            List<Room> allRooms=this.roomRepository.findAll();
        // then
            assertThat(allRooms).isNotNull();
            assertThat(allRooms.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test Repository find one room by its  id")
    void findOneById(){
        // given
        Room roomSaved=roomRepository.save(room);
        System.out.println(roomSaved.getId());
        // when
        Optional<Room> roomFoundById=this.roomRepository.findById(roomSaved.getId());
        // then
        //FindById
        assertThat(roomFoundById.isEmpty()).isFalse();
        assertThat(roomFoundById.get().getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test Repository find one room by its name")
    void findOneByName(){
        // given
        roomRepository.save(room);
        // when
            Optional<Room> roomFoundByName=this.roomRepository.findByRoomName("201");
        // then
            assertThat(roomFoundByName.get()).isNotNull();
            assertThat(roomFoundByName.get().getId()).isGreaterThan(0);

    }

    @Test
    @DisplayName("Test Repository find one room which doesn't exist")
    void findOneNotExist(){
        // given
        roomRepository.save(room);
        // when
        // FindOneByRoomName
        Optional<Room> roomFoundByName=this.roomRepository.findByRoomName("204");
        // FindOneById
        Optional<Room> roomFoundById=this.roomRepository.findById(10L);
        // then
        // FindByRoomName
        assertThat(roomFoundByName.isEmpty()).isTrue();
        //FindById
        assertThat(roomFoundById.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Test Repository delete one room")
    void delete(){
        // given
        Room roomSaved=roomRepository.save(room);
        // when
        this.roomRepository.deleteById(roomSaved.getId());
        Optional<Room> roomFound=this.roomRepository.findById(roomSaved.getId());
        // that
        assertThat(roomFound.isEmpty()).isTrue();
    }

}