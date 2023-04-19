package com.example.diploma.service.act;

import com.example.diploma.dto.ActDto;
import com.example.diploma.dto.StationDto;
import com.example.diploma.entity.Station;
import org.springframework.stereotype.Component;

@Component
public class StationConverter {

    public Station fromDtoToStation(StationDto stationDto) {
        Station station = new Station();

        station.setId(stationDto.getId());
        station.setEsr(stationDto.getEsr());
        station.setName(stationDto.getName());
        station.setNod_id(stationDto.getNod_id());
        station.setSt_id(stationDto.getSt_id());

        return station;
    }

    public StationDto fromStationToDto(Station station) {
        StationDto stationDto = new StationDto();

        stationDto.setId(station.getId());
        stationDto.setEsr(station.getEsr());
        stationDto.setName(station.getName());
        stationDto.setNod_id(station.getNod_id());
        stationDto.setSt_id(station.getSt_id());

        return  stationDto;
    }

}
