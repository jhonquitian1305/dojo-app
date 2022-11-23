package com.app.dojo.controllers;

import com.app.dojo.constants.EndPointsConstants;
import com.app.dojo.services.Interfaces.GroupClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPointsConstants.ENDPOINT_GROUP_CLASSES)
public class GroupClassController {

    @Autowired
    private GroupClassService groupClassService;
}
