package com.app.dojo.constants;

public final class EndPointsConstants {

    ///// General
    public static final String ENDPOINT_ID = "/{id}";

    // Rooms
    public static final String ENDPOINT_ROOMS="/api/dojo-app/rooms";
    public static final String ENDPOINT_ROOM_BY_NAME="/name/{room}";

    // Students
    public static final String ENDPOINT_STUDENTS="/api/dojo-app/students";
    public static final String ENDPOINT_STUDENT_BY_DNI ="/dni/";

    // Shedule
    public static final String ENDPOINT_SCHEDULE="/api/dojo-app/schedules";

    // Levels
    public static final String ENDPOINT_LEVELS="/api/dojo-app/levels";

    // Courses
    public static final String ENDPOINT_COURSES="/api/dojo-app/courses";
}
