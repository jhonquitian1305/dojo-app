package com.app.dojo.services.implementation;

import com.app.dojo.exception.errors.NotFoundException;
import com.app.dojo.models.Day;
import com.app.dojo.repositories.DayRespository;
import com.app.dojo.services.Interfaces.DayServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.app.dojo.constants.Message.*;
import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class DayServiceImp implements DayServcie {

    @Autowired
    private DayRespository dayRespository;

    @Override
    public Day findByName(String day) throws NotFoundException {
       Day dayFound=this.dayRespository.findByDay(day);
       if(dayFound==null){
           throw  new NotFoundException(MESSAGE_NOT_FOUND_DAY.formatted(day));
       }
       return  dayFound;
    }
}
