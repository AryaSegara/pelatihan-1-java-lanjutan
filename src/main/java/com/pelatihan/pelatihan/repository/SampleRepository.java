package com.pelatihan.pelatihan.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pelatihan.pelatihan.model.Sample;

public interface SampleRepository extends JpaRepository<Sample, Integer> {
    
    @Query("select s from Sample s where s.name like %:name%")
    List<Sample> findAll(String name,Pageable pageable);

}
