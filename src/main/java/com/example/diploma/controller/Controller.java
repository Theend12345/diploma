package com.example.diploma.controller;

import com.example.diploma.dto.ActDto;
import com.example.diploma.dto.StationDto;
import com.example.diploma.service.act.ActService;
import com.example.diploma.service.act.StationService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/act2")
@AllArgsConstructor
@Log
public class Controller {

    private final ActService actService;
    private final StationService stationService;


    @GetMapping("/getFrequentFailure")
    public String getFrequentFailure(@RequestParam String[] data){
        return actService.getFrequentFailure(data);
    }

    @GetMapping("/getDefects")
    public List<String> getDefects(@RequestParam String sheet){
        return actService.getDefects(sheet);
    }

    @GetMapping("/updateStation")
    public void updateStation(@RequestParam int id,@RequestParam String name){
        stationService.updateStation(id,name);
    }

    @GetMapping("/findAll")
    public List<ActDto> findAllActs() {
        return actService.findAll();
    }

    @GetMapping("/findAllStation")
    public List<StationDto> findAllStation() {
        return stationService.findAll();
    }
    @GetMapping("/findByName")
    public List<StationDto> findByName(@RequestParam String name) {
        return stationService.findByName(name);
    }

    @GetMapping("/findByDateBetween")
    public List<ActDto> findByDateBetween(@RequestParam String v1, @RequestParam String v2) {
        return actService.findByDateBetween(v1,v2);
    }
    @GetMapping("/findByObject")
    public List<ActDto> findByObject(@RequestParam String obj) {
        return actService.findByObject(obj);
    }

    @GetMapping("/findByStation")
    public List<ActDto> findByStation(@RequestParam String st) {
        return actService.findByStation(st);
    }

    @GetMapping("/findByLimit")
    public List<ActDto> findByLimit(@RequestParam String lim) {
        return actService.findByLimit(lim);
    }

    @GetMapping("/findByObjectAndDateBetween")
    public List<ActDto> findByObjectAndDateBetween(@RequestParam String obj,@RequestParam String v1, @RequestParam String v2) {
        return actService.findByObjectAndDateBetween(obj, v1, v2);
    }

    @GetMapping("/findByObjectAndDefectAndDateBetween")
    public List<ActDto> findByObjectAndDefectAndDateBetween(@RequestParam String obj,@RequestParam String v3,@RequestParam String v1,@RequestParam String v2) {
        return actService.findByObjectAndDefectAndDateBetween(obj, v3.trim(), v1, v2);
    }


    @GetMapping("/findByDate")
    public List<ActDto> findByDate(@RequestParam String v1) {
        return actService.findByDate(v1);
    }

    @GetMapping("/findByDefect")
    public List<ActDto> findByDefect(@RequestParam String def) {
        return actService.findByDefect(def);
    }

    @GetMapping("/findByKmo")
    public List<ActDto> findByKmo(@RequestParam Integer id_kmo) {
        return actService.findByKmo(id_kmo);
    }

    @PostMapping("/getDataStatus")
    public List<String> getDataStatus(@RequestParam List<String> data){
        return actService.getDataStatus(data);
    }

    @PostMapping("/getDataDefects")
    public Map<String, Long> getDataDefects(@RequestParam List<String> data){
        return actService.getDataDefects(data);
    }
}
