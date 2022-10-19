package com.app.dojo.dtos;

import com.app.dojo.models.Day;
import com.app.dojo.models.Hour;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRequest {
    @NotEmpty(message = "Muts to have minimun one hour")
    @NotNull(message = "Hours are mandatory ")
    @Size(min=1)
    private List<String> hours=new ArrayList<>();
    @NotNull(message = "Days are mandatory")
    @NotEmpty(message = "Muts to have minimun one day ")
    @Size(min=1)
    private List<String> days=new ArrayList<>();

    public ScheduleRequest(List<String> hours, List<String> days) {
        this.hours = hours;
        this.days = days;
    }

    public ScheduleRequest() {
    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }
}
