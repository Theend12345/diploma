package com.example.diploma.service.act;

import com.example.diploma.dto.StationDto;


import java.util.List;

public interface StationService {

    List<StationDto> findAll();
    List<StationDto> findByName(String name);
    void updateStation(int id, String name);

}
