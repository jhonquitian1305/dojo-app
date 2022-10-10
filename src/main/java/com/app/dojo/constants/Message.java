package com.app.dojo.constants;

public final class Message {

    // Room Service
    public static final String MESSAGE_BAD_REQUEST_CREATE_ROOM="Already exists one room with that name";
    public static final String MESSAGE_NOT_FOUND_ROOM_ID="Doesn't exists a room with that id  %s";
    public static final String MESSAGE_NOT_FOUND_ROOM_NAME="Doesn't exists a room with that name %s";

    // Hour Service
    public static final String MESSAGE_NOT_FOUND_HOUR="Hour doesn' exist with that name %s";

    //Day Service
    public static final String MESSAGE_NOT_FOUND_DAY="Day doesn't exist with that name %s";


    // Schedule Service
    public static final String MESSAGE_BAD_REQUEST_CREATE_SCHEDULE="\"That schedule already exists. Time %s and Day %s";
}
