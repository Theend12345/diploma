package com.example.diploma.service.act;

import com.example.diploma.dto.ActDto;

import java.util.List;
import java.util.Map;


public interface ActService {
    List<ActDto> findByDateBetween(String v1, String v2);
    List<ActDto> findByObject(String obj);
    List<ActDto> findByDefect(String def);
    List<ActDto> findByLimit(String lim);
    List<ActDto> findByKmo(Integer id_kmo);
    List<String> getDataStatus(List<String> data);
    Map<String,Long> getDataDefects(List<String> data);
    List<ActDto> findAll();
    List<ActDto> findByObjectAndDateBetween(String obj, String v1, String v2);
    List<ActDto> findByObjectAndDefectAndDateBetween(String obj,String v3, String v1, String v2);
    List<ActDto> findByDate(String v1);
    List<ActDto> findByStation(String st);
    List<String> getDefects(String v1);
    String getFrequentFailure(String[] data);
}
