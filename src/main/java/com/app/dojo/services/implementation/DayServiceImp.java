package com.app.dojo.services.implementation;

import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.models.Day;
import com.app.dojo.services.Interfaces.DayServcie;
import org.springframework.stereotype.Service;

@Service
public class DayServiceImp implements DayServcie {
    @Override
    public Day findByName(String day) throws NotFoundException {
        return null;
    }
}
