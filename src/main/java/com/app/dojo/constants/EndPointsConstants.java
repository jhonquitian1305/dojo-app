package com.app.dojo.constants;

public final class EndPointsConstants {

    ///// General
    public static final String ENDPOINT_ID = "/{id}";
    public static final String ENDPOINT_DNI ="/dni/";
    public static final String ENDPOINT_EMAIL ="/email/";

    // Rooms
    public static final String ENDPOINT_ROOMS="/api/dojo-app/rooms";
    public static final String ENDPOINT_ROOM_BY_NAME="/name/{room}";

    // Students
    public static final String ENDPOINT_STUDENTS="/api/dojo-app/students";

    // Shedule
    public static final String ENDPOINT_SCHEDULE="/api/dojo-app/schedules";

    // Levels
    public static final String ENDPOINT_LEVELS="/api/dojo-app/levels";

    // Courses
    public static final String ENDPOINT_COURSES="/api/dojo-app/courses";

    // GroupClasses
    public static final String ENDPOINT_GROUP_CLASSES="/api/dojo-app/groups";

    //Teacher
    public static final String ENDPOINT_TEACHERS="/api/dojo-app/teachers";

    //Comments
    public static final String ENDPOINT_COMMENTS="/api/dojo-app/comments";

    //Diplomas
    public static final String ENDPOINT_DIPLOMAS = "/api/dojo-app/diplomas";
    public static final String ENDPOINT_DIPLOMAS_STUDENT = "/students/{id}";
    public static final String ENDPOINT_DIPLOMAS_TEACHER = "/teachers/{id}";
}
