package com.app.dojo.controllers;

import com.app.dojo.constants.EndPointsConstants;
import com.app.dojo.constants.PaginationRequest;
import com.app.dojo.dtos.GroupClassDTO;
import com.app.dojo.dtos.GroupClassDTOResponse;
import com.app.dojo.dtos.GroupClassResponse;
import com.app.dojo.mappers.MapperGroupClass;
import com.app.dojo.models.GroupClass;
import com.app.dojo.services.Interfaces.GroupClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(EndPointsConstants.ENDPOINT_GROUP_CLASSES)
public class GroupClassController {

    @Autowired
    private GroupClassService groupClassService;
    @Autowired
    private MapperGroupClass mapperGroupClass;

    @PostMapping()
    public ResponseEntity<GroupClassDTOResponse> create(@Valid @RequestBody()GroupClassDTO groupClassDTO) throws Exception {
        GroupClass groupSaved=this.groupClassService.create(groupClassDTO);
        return new ResponseEntity<>(mapperGroupClass.mapperGroupClassDTOResponse(groupSaved), HttpStatus.CREATED);
    }

    @GetMapping()
    public  ResponseEntity<GroupClassResponse> getAll(
            @RequestParam(value = "numberPage", defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE, required = false) int numberPage,
            @RequestParam(value = "pageSize",defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE, required = false)int pageSize,
            @RequestParam(value="id",defaultValue = PaginationRequest.DEFAULT_ID,required = false) Long id,
            @RequestParam(value="model",defaultValue =PaginationRequest.DEFAULT_MODEL,required = false) String model
    ) throws Exception {
        return ResponseEntity.ok(this.groupClassService.getAll(numberPage,pageSize,id,model));
    }
    @GetMapping(EndPointsConstants.ENDPOINT_ID)
    public ResponseEntity<GroupClassDTOResponse> getOne(@PathVariable("id") Long id){
        GroupClass groupClassFound=this.groupClassService.getOne(id);
        return ResponseEntity.ok(mapperGroupClass.mapperGroupClassDTOResponse(groupClassFound));
    }

    @DeleteMapping(EndPointsConstants.ENDPOINT_ID)
    public  ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.groupClassService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
