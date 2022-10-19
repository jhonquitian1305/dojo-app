package com.app.dojo.services.implementation;

import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.models.Hour;
import com.app.dojo.repositories.HourRepository;
import com.app.dojo.services.Interfaces.HourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.app.dojo.constants.Message.*;

@Service
public class HourServiceImp implements HourService {

    @Autowired
    private HourRepository hourRepository;
    @Override
    public Hour findByName(String hour) throws NotFoundException {
        Hour hourFound=this.hourRepository.findByHour(hour);
        if(hourFound==null){
            throw  new NotFoundException(MESSAGE_NOT_FOUND_HOUR.formatted(hour));
        }
        return  hourFound;
    }
}
