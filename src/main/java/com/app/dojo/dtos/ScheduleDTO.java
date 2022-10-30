package com.app.dojo.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ScheduleDTO {
    private Long id;
    @NotEmpty(message = "El día no puede ser vacío")
    @NotNull(message = "El día no puede ser nulo")
    @Size(max =9 )
    private String dayName;
    @NotEmpty(message = "Las horas de clase no puede ser vacío")
    @NotNull(message = "Las horas de clase no no puede ser nulo")
    @Size(max = 11)
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
