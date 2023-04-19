package com.example.diploma.repository;

import com.example.diploma.dto.StationDto;
import com.example.diploma.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Integer> {
    List<Station> findByName(String name);

    @Transactional
    @Modifying
    @Query("update Station u set u.name = :name where u.id = :id")
    void updateStation(@Param(value = "id") int id, @Param(value = "name") String name);
}
