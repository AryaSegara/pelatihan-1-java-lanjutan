package com.pelatihan.pelatihan.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pelatihan.pelatihan.dto.PageResponse;
import com.pelatihan.pelatihan.dto.SampleDto;
import com.pelatihan.pelatihan.dto.UpdateSampleDto;
import com.pelatihan.pelatihan.model.Sample;
import com.pelatihan.pelatihan.repository.SampleRepository;
import com.pelatihan.pelatihan.repository.specification.SampleSpecification;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j // libary untuk log (untuk debugging)
public class SampleServiceImpl implements SampleService {

    private final SampleRepository sampleRepository;

    @Autowired
    public SampleServiceImpl(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    List<Sample> samples = new ArrayList<>();


    @Override
    public List<SampleDto> findAll(String name,Pageable pageable) {
        return sampleRepository.findAll(name, pageable)
                                .stream()
                                .map(this::mapToSampleDto)
                                .toList();
    }


    @Override
    public PageResponse<SampleDto>findAllWithFilter(String name, String code, Boolean status, Pageable pageable){
        Specification<Sample> spec = Specification.where(null);

        // name
        spec = spec.and(SampleSpecification.containName(name));

        // code
        if(code != null && !code.isEmpty()){
            spec = spec.and(SampleSpecification.equalCode(code));
        }

        // status
        if(status != null){
            spec = spec.and(SampleSpecification.equalStatus(status)); 
        }

        Page<Sample> samplePage = sampleRepository.findAll(spec,pageable);

        return PageResponse.<SampleDto>builder()
                            .page(pageable.getPageNumber())
                            .size(pageable.getPageSize())
                            .totalPage(samplePage.getTotalPages())
                            .totalItem(samplePage.getTotalElements())
                            .items(samplePage.stream()
                                              .map(this::mapToSampleDto)
                                              .toList())
                            .build();

    }

    @Override
    public void create(SampleDto dto) {
        sampleRepository.save(mapToSampleDto(dto));

    }


    @Override
    public void update(int id, UpdateSampleDto dto) {
        Optional<Sample> sample = sampleRepository.findById(id);

        if(sample.isPresent()){

            Sample sampleToUpdate = sample.get();
                sampleToUpdate.setName(dto.getName());
                sampleToUpdate.setCode(dto.getCode());
                sampleToUpdate.setDescription(dto.getDescription());
                sampleToUpdate.setStatus(dto.getStatus());
                sampleToUpdate.setUpdateDate(LocalDate.now());
                sampleRepository.save(sampleToUpdate);
            
        }else{
            log.debug("Sample id yang di cari : {}", id); // ini muncul ketika di level  nya di ubah ke level debug
            log.info("Sample id yang di cari : {}", id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " sample id tidak ditemukan");
        }

    }


    @Override
    public void delete(int id) {
        Optional<Sample> sample = sampleRepository.findById(id);
        sample.ifPresent(sampleRepository::delete);

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
