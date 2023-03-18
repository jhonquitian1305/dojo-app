package com.app.dojo.controllers;

import com.app.dojo.constants.PaginationRequest;
import com.app.dojo.dtos.ScheduleDTO;
import com.app.dojo.dtos.ScheduleResponse;
import com.app.dojo.mappers.MapperSchedule;
import com.app.dojo.services.Interfaces.ScheduleServcie;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.app.dojo.constants.EndPointsConstants.*;

@RestController
@RequestMapping(ENDPOINT_SCHEDULE)
public class SheduleController {

    @Autowired
    private ScheduleServcie scheduleServcie;
    @Autowired
    private MapperSchedule mapperSchedule;
    @PostMapping
    public ResponseEntity<ScheduleDTO> create(@Valid @RequestBody ScheduleDTO scheduleDTO ){
        ScheduleDTO scheduleSaved=mapperSchedule.mapperScheduleDTO(this.scheduleServcie.save(scheduleDTO));
        return  new ResponseEntity<>(scheduleSaved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ScheduleResponse> getAll(
            @RequestParam(value = "numberPage", defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int numberPage,
            @RequestParam(value = "pageSize",defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE, required = false)int pageSize
    ){
        return ResponseEntity.ok(this.scheduleServcie.findAll(numberPage,pageSize));
    }

    @GetMapping(ENDPOINT_ID)
    public ResponseEntity<ScheduleDTO> getOne(@PathVariable("id") Long id){
        ScheduleDTO scheduleDTO=mapperSchedule.mapperScheduleDTO(this.scheduleServcie.findOne(id));
        return ResponseEntity.ok(scheduleDTO);
    }

    @DeleteMapping(ENDPOINT_ID)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.scheduleServcie.delete(id);
        return ResponseEntity.noContent().build();
    }
}
