package com.app.dojo.services.implementation;

import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.models.Hour;
import com.app.dojo.services.Interfaces.HourService;
import org.springframework.stereotype.Service;

@Service
public class HourServiceImp implements HourService {

    @Override
    public Hour findByName(String hour) throws NotFoundException {
        return null;
    }
}
