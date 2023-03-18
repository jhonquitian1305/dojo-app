package com.app.dojo.services;

import com.app.dojo.builders.builderDTO.RoomDTOBuilder;
import com.app.dojo.builders.builderModels.RoomBuilder;
import com.app.dojo.dtos.RoomDTO;
import com.app.dojo.dtos.RoomResponse;
import com.app.dojo.exception.errors.BadRequest;
import com.app.dojo.exception.errors.NotFoundException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

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
        // when
            Room roomCreated=this.roomService.create(roomDTO);
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

    @Test
    @DisplayName("Test Service find one room by its id and name")
    void getOne() throws Exception {
        // give
        given(this.roomRepository.findByRoomName(roomDTO.getRoomName())).willReturn(Optional.of(room));
        given(this.roomRepository.findById(anyLong())).willReturn(Optional.of(room));

        // when
            //FindById
            Room roomFoundById=this.roomService.findById(1L);
            // FindByName
            Room roomFoundByName=this.roomService.findByName(roomDTO.getRoomName());
        // Then

            //FindById
            assertNotNull(roomFoundById);
            assertEquals(1L, roomFoundById.getId());
            //FindByName
            assertNotNull(roomFoundByName);
            assertEquals(1L, roomFoundByName.getId());
    }

    @Test
    @DisplayName("Test Service find one room  which doesn't exist")
    void failGetOne() throws Exception {
        // give
            given(this.roomRepository.findByRoomName(roomDTO.getRoomName())).willReturn(Optional.empty());
            given(this.roomRepository.findById(anyLong())).willReturn(Optional.empty());
        // when
            //FindById
            NotFoundException exceptionId=assertThrows(NotFoundException.class,()->this.roomService.findById(1L));
            // FindByName
            NotFoundException exceptionName=assertThrows(NotFoundException.class,()->this.roomService.findByName(roomDTO.getRoomName()));
        // Then
             // FindById
            assertEquals("Doesn't exists a room with that name %s".formatted(roomDTO.getRoomName()),exceptionName.getMessage());
            //FindByName
            assertEquals("Doesn't exists a room with that id  %s".formatted(1L),exceptionId.getMessage());
    }

    @Test
    @DisplayName("Test Service find all rooms")
    void getAll(){
        // given
        Page<Room> rooms=new PageImpl<>(List.of(room));
        given(this.roomRepository.findAll(any(Pageable.class))).willReturn(rooms);
        given(this.mapperRoom.mapperRoomDTO(any(Room.class))).willReturn(roomDTO);
        // when
        RoomResponse response=this.roomService.findAll(0,10);
        // then
        assertEquals(0,response.getNumberPage());
        assertEquals(1,response.getTotalElements());
        assertThat(response.getContent().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test Service delete a room")
    void delete() throws Exception {
        //given
        given(roomRepository.findById(anyLong())).willReturn(Optional.ofNullable(room));
        willDoNothing().given(roomRepository).deleteById(anyLong());
        // when
        this.roomService.delete(anyLong());
        //then
        verify(roomRepository,times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("Test Service delete a room that doesn't exist")
    void failDelete() throws Exception {
        //given
        given(roomRepository.findById(1L)).willReturn(Optional.empty());
        // when
        NotFoundException exception=assertThrows(NotFoundException.class,()->this.roomService.delete(1L));
        //then
        assertEquals("Doesn't exists a room with that id  %s".formatted(1L),exception.getMessage());
    }
}