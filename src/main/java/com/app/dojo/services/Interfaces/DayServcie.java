package com.app.dojo.services.Interfaces;

import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.models.Day;

public interface DayServcie {
    Day findByName(String day) throws NotFoundException;
}
