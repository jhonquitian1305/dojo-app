package com.app.dojo.repositories;

import com.app.dojo.builders.builderModels.RoomBuilder;
import com.app.dojo.models.Room;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

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
    @DisplayName("Test Respository save one user in database")
    void create(){
        Room roomSaved=this.roomRepository.save(room);

        assertNotNull(roomSaved);
        assertThat(roomSaved.getId()).isGreaterThan(0);
    }



}