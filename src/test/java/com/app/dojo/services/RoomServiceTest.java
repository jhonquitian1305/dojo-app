package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.RoomDTOBuilder;
import com.app.dojo.builders.builderModels.RoomBuilder;
import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.mappers.MapperRoom;
import com.app.dojo.models.Room;
import com.app.dojo.repositories.RoomRepository;
import com.app.dojo.services.implementation.RoomServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @InjectMocks
    private RoomServiceImp roomService;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private MapperRoom mapperRoom;
    private RoomDTO roomDTO;
    private Room room;

    @BeforeEach
    void init(){
        room = new RoomBuilder()
                .setId(1L)
                .setRoomName("201")
                .build();

        roomDTO=new RoomDTOBuilder()
                .setId(1L)
                .setRoomName("201")
                .build();
    }

    @Test
    @DisplayName("Test Service create one room")
    void create(){
        // give
            given(this.roomRepository.save(any(Room.class))).willReturn(room);
            given(this.roomRepository.findByRoomName(roomDTO.getRoomName())).willReturn(Optional.empty());

            given(this.mapperRoom.mapperRoom(any(RoomDTO.class))).willReturn(room);
            given(this.mapperRoom.mapperRoomDTO(any(Room.class))).willReturn(roomDTO);
        // when
            RoomDTO roomCreated=this.roomService.create(roomDTO);
        // then
        assertNotNull(roomCreated);
        assertEquals(1L,roomCreated.getId());
        assertEquals("201", roomCreated.getRoomName());
    }

    @Test
    @DisplayName("Test Service create one room which already exists")
    void failCreate(){
        // give
        given(this.roomRepository.findByRoomName(roomDTO.getRoomName())).willReturn(Optional.of(room));
        // when
        BadRequest exception=assertThrows(BadRequest.class,()->{
                this.roomService.create(roomDTO);
            });
        // then
        assertEquals("Already exists one room with that name",exception.getMessage());
        verify(roomRepository, never()).save(any(Room.class));
    }
}