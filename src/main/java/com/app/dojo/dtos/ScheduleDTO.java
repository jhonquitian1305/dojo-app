package com.app.dojo.dtos;

import com.app.dojo.models.Day;
import com.app.dojo.models.Hour;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDTO {
    private Long id;
    @NotEmpty(message = "Muts to have minimun one hour")
    @NotNull(message = "Hours are mandatory ")
    @Size(min=1)
    private List<Hour> hours;
    @NotNull(message = "Days are mandatory")
    @NotEmpty(message = "Muts to have minimun one day ")
    @Size(min=1)
    private List<Day> days;

    public ScheduleDTO(Long id, List<Hour> hours, List<Day> days) {
        this.id = id;
        this.hours = hours;
        this.days = days;
    }

    public ScheduleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
