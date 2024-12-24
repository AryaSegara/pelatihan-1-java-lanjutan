package com.pelatihan.pelatihan.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pelatihan.pelatihan.dto.SampleDto;
import com.pelatihan.pelatihan.dto.UpdateSampleDto;
import com.pelatihan.pelatihan.model.Sample;

@Service
public class SampleServiceImpl implements SampleService {

    List<Sample> samples = new ArrayList<>();

    @Override
    public List<SampleDto> findAll() {
        return samples.stream()
                .map(this::mapToSampleDto)
                .toList();
    }

    @Override
    public void create(SampleDto dto) {
        samples.add(mapToSampleDto(dto));

    }

    @Override
    public void update(int id, UpdateSampleDto dto) {
        samples = samples.stream()
                .filter(sample -> sample.getId() == id)
                .map(sample -> {
                    Sample  newSample = new Sample();
                    newSample.setId(id);
                    newSample.setName(dto.getName());
                    newSample.setCode(dto.getCode());
                    newSample.setDescription(dto.getDescription());
                    newSample.setStatus(dto.getStatus());
                    newSample.setUpdateDate(LocalDate.now());
                    return newSample;
                })
                .toList();

    }

    @Override
    public void delete(int id) {
        Optional<Sample> sample = samples.stream()
                .filter(sample1 -> sample1.getId() == id)
                .findFirst();
                
        sample.ifPresent(value -> samples.remove(value));


    }

    // find-all
    public SampleDto mapToSampleDto(Sample sample) {
        return SampleDto.builder()
                .id(sample.getId())
                .name(sample.getName())
                .code(sample.getCode())
                .description(sample.getDescription()) 
                .status(sample.getStatus())
                .build();
    }

    // create
    public Sample mapToSampleDto(SampleDto dto) {
        return Sample.builder()
                .id(dto.getId())
                .name(dto.getName())
                .code(dto.getCode())
                .description(dto.getDescription()) 
                .status(dto.getStatus())
                .createdDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .build();
    }
    
}
