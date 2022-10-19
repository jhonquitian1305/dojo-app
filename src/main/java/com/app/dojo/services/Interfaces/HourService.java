package com.app.dojo.services.Interfaces;


import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.models.Hour;

public interface HourService {
    Hour findByName(String hour) throws NotFoundException;
}
