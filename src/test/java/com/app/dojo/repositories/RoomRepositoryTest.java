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
    @DisplayName("Test Repository find one room by its name and id")
    void findOne(){
        // given
        roomRepository.save(room);
        // when
            // FindOneByRoomName
            Optional<Room> roomFoundByName=this.roomRepository.findByRoomName("201");
            // FindOneById
            Optional<Room> roomFoundById=this.roomRepository.findById(1L);
        // then
            // FindByRoomName
            assertThat(roomFoundByName.get()).isNotNull();
            assertThat(roomFoundByName.get().getId()).isGreaterThan(0);
            //FindById
            assertThat(roomFoundById.get()).isNotNull();
            assertThat(roomFoundById.get().getId()).isGreaterThan(0);
    }
}