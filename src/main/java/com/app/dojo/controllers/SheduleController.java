package com.app.dojo.controllers;

import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.app.dojo.constants.EndPointsConstants.*;

@RestController
@RequestMapping(ENDPOINT_SCHEDULE)
public class SheduleController {

    @Autowired
    private ScheduleServcie scheduleServcie;
    @PostMapping
    public ResponseEntity<ScheduleDTO> create(@RequestBody ScheduleDTO scheduleDTO){
        ScheduleDTO scheduleSaved=this.scheduleServcie.save(scheduleDTO);
        return  new ResponseEntity<>(scheduleSaved, HttpStatus.CREATED);
    }
}
