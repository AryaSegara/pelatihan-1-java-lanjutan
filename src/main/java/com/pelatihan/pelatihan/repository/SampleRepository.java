package com.pelatihan.pelatihan.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pelatihan.pelatihan.model.Sample;

public interface SampleRepository extends JpaRepository<Sample, Integer> {

    Page<Sample> findAll(Specification<Sample> spec, Pageable pageable);
    
    @Query("select s from Sample s where s.name like %:name%")
    List<Sample> findAll(String name,Pageable pageable);

}
