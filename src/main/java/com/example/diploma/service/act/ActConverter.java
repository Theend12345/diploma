package com.example.diploma.service.act;

import com.example.diploma.dto.ActDto;
import com.example.diploma.entity.Act;
import org.springframework.stereotype.Component;

@Component
public class ActConverter {

    public Act fromDtoToAct(ActDto actDto) {
        Act act = new Act();

        act.setId(actDto.getId());
        act.setLimit(actDto.getLimit());
        act.setCommit(actDto.getCommit());
        act.setDate(actDto.getDate());
        act.setDefect(actDto.getDefect());
        act.setStation(actDto.getStation());
        act.setElement(actDto.getElement());
        act.setKmo(actDto.getKmo());
        act.setObject(actDto.getObject());
        act.setResolve_date(actDto.getResolve_date());
        act.setStatus(actDto.getStatus());
        act.setValue(actDto.getValue());

        return act;
    }

    public ActDto fromActToDto(Act act) {
        ActDto actDto = new ActDto();

        actDto.setId(act.getId());
        actDto.setLimit(act.getLimit());
        actDto.setCommit(act.getCommit());
        actDto.setDate(act.getDate());
        actDto.setDefect(act.getDefect());
        actDto.setStation(act.getStation());
        actDto.setElement(act.getElement());
        actDto.setKmo(act.getKmo());
        actDto.setObject(act.getObject());
        actDto.setResolve_date(act.getResolve_date());
        actDto.setStatus(act.getStatus());
        actDto.setValue(act.getValue());
        return  actDto;
    }

}
