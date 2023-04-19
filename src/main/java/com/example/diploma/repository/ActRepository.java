package com.example.diploma.repository;

import com.example.diploma.dto.ActDto;
import com.example.diploma.entity.Act;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ActRepository extends JpaRepository<Act, Integer> {

    List<Act> findByDateBetween(String v1, String v2);
    List<Act> findByObject(String obj);
    List<Act> findByObjectAndDateBetween(String obj,String v1, String v2);
    List<Act> findByObjectAndDefectAndDateBetween(String obj,String v3, String v1, String v2);
    List<Act> findByKmo(Integer id_kmo);
    List<Act> findByDate(String v1);
    List<Act> findByDefect(String def);
    List<Act> findByLimit(String lim);
    List<Act> findByStation(String st);
}
