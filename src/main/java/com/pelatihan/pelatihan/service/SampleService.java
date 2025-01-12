package com.pelatihan.pelatihan.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pelatihan.pelatihan.dto.PageResponse;
import com.pelatihan.pelatihan.dto.SampleDto;
import com.pelatihan.pelatihan.dto.UpdateSampleDto;

public interface SampleService {
    List<SampleDto> findAll(String name,Pageable pageable);
    PageResponse<SampleDto> findAllWithFilter(String name,
                                      String code,
                                      Boolean status,  
                                      Pageable pageable);
    void create(SampleDto dto);
    void update(int id, UpdateSampleDto dto);
    void delete(int id);
}
