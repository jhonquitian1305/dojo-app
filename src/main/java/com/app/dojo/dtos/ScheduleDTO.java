package com.app.dojo.dtos;

import com.app.dojo.constants.Message;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ScheduleDTO {
    private Long id;
    @NotEmpty(message = Message.FIELD_EMPTY)
    @NotNull(message = Message.FIELD_NULL)
    @Size(max =9 )
    private String dayName;
    @NotEmpty(message = Message.FIELD_EMPTY)
    @NotNull(message = Message.FIELD_NULL)
    @Size(max = 11)
    @Pattern(
            regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]-([01]?[0-9]|2[0-3]):[0-5][0-9]$",
            message =Message.SCHEDULE_HOUR_FORMAT
    )
    private String hoursClass;

    public ScheduleDTO() {
    }

    public ScheduleDTO(Long id, String dayName, String hoursClass) {
        this.id = id;
        this.dayName = dayName;
        this.hoursClass = hoursClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getHoursClass() {
        return hoursClass;
    }

    public void setHoursClass(String hoursClass) {
        this.hoursClass = hoursClass;
    }
}
