package com.app.dojo.constants;

public final class Message {


    public static final String FIELD_NULL="El campo no puede ser nulo";
    public static final String FIELD_EMPTY="El campo  no puede ser vacío";

    // Room Service
    public static final String MESSAGE_BAD_REQUEST_CREATE_ROOM="Already exists one room with that name";
    public static final String MESSAGE_NOT_FOUND_ROOM_ID="Doesn't exists a room with that id  %s";
    public static final String MESSAGE_NOT_FOUND_ROOM_NAME="Doesn't exists a room with that name %s";

    // Schedule Service
    public static final String MESSAGE_BAD_REQUEST_CREATE_SCHEDULE="This schedule is already created";
    public static final String MESSAGE_NOT_FOUND_SCHEDULE_ID="Doesn't exist a schedule with that id %s";

    //Level Service
    public static  final String MESSAGE_BAD_REQUEST_CREATE_LEVEL="There is already a saved level with that name: %s";

    //Level
    public static final String LEVEL_NAME_SIZE="El nombre del nivel debe ser mínimo de 5 letras";
}
