package com.example.diploma.service.act;

import com.example.diploma.dto.StationDto;
import com.example.diploma.repository.StationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultStationService implements StationService{
    private final StationRepository stationRepository;
    private final StationConverter stationConverter;


    @Override
    public List<StationDto> findAll() {
        return stationRepository.findAll().stream()
                .map(stationConverter::fromStationToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StationDto> findByName(String name) {
        return stationRepository.findByName(name).stream()
                .map(stationConverter::fromStationToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStation(int id, String name) {
        stationRepository.updateStation(id,name);
    }


}
