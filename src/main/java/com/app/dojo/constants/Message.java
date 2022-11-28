package com.app.dojo.constants;

public final class Message {


    public static final String FIELD_NULL="The field cannot be null";
    public static final String FIELD_EMPTY="The field cannot be empty";
    public static final String POSITIVE_VALUE="Field value must be greater than 0";

    // Room Service
    public static final String MESSAGE_BAD_REQUEST_CREATE_ROOM="Already exists one room with that name";
    public static final String MESSAGE_NOT_FOUND_ROOM_ID="Doesn't exists a room with that id  %s";
    public static final String MESSAGE_NOT_FOUND_ROOM_NAME="Doesn't exists a room with that name %s";

    // Schedule Service
    public static final String MESSAGE_BAD_REQUEST_CREATE_SCHEDULE="This schedule is already created";
    public static final String MESSAGE_NOT_FOUND_SCHEDULE_ID="Doesn't exist a schedule with that id %s";

    //Level Service
    public static  final String MESSAGE_BAD_REQUEST_CREATE_LEVEL="There is already a saved level with that name: %s";
    public static final String MESSAGE_NOT_FOUND_LEVEL_ID="There is no level with this identification %s";

    //Level
    public static final String LEVEL_NAME_SIZE="The name of the level must be at least 5 letters long.";

    //Schedule
    public static final String SCHEDULE_HOUR_FORMAT="Classroom hours should be in the following format 08:00-10:00";

    //Courses
    public static final String COURSES_PRICE="The course fee must be greater than 0";

    //Course Service
    public static final String MESSAGE_BAD_REQUEST_COURSES_DATE="The end date of the course must be after the start date.";
    public static final String MESSAGE_BAD_REQUEST_COURSES_NAME="There is already a course saved under that name";
    public static final String MESSAGE_NOT_FOUND_BY_ID_COURSES="There is no course saved with that id %s";

    // Group classes
    public static final String GROUP_CLASSES_ROOM="The group must be assigned a minimum of one room";
    public static final String GROUP_CLASSES_SCHEDULE="The group must be assigned a minimum of one schedule";
    public static final String GROUP_CLASSES_NAME="The group name must be at least 3 characters long.";
    public static final String GROUP_CLASSES_CODE="The code must be 7 characters in size";

    // GroupClass Service
    public static final String MESSAGE_BAD_REQUEST_CREATE_GROUP_CLASS_NAME="There is already a group saved with that name %s";
    public static final String MESSAGE_BAD_REQUEST_CREATE_GROUP_CLASS_HOURS="Hours per week do not coincide with the hours set in the schedule.";
    public static final String MESSAGE_BAD_REQUEST_CREATE_GROUP_CLASS_TOTAL_HOURS="Total hours do not match weeks entered and hours per week";
    public static final String MESSAGE_BAD_REQUEST_CREATE_GROUP_CLASS_ROOM_SCHEDULE="The %s room has already been assigned the %s schedule  on %s day";
    public static final String MESSAGE_NOT_FOUND_GET_ONE_GROUP_CLASS="There isn't a group saved with that id %s";
    public static final String MESSAGE_NOT_FOUND_GET_All_GROUPS="There aren't groups saved";
}
