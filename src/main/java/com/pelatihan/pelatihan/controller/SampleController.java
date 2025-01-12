package com.pelatihan.pelatihan.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pelatihan.pelatihan.dto.GenericResponse;
import com.pelatihan.pelatihan.dto.SampleDto;
import com.pelatihan.pelatihan.dto.UpdateSampleDto;
import com.pelatihan.pelatihan.service.SampleService;

@RestController
@RequestMapping("/sample")
public class SampleController {
    private final SampleService sampleService;

    @Autowired
    SampleController(SampleService sampleService){
        this.sampleService = sampleService;
    }
    
    @GetMapping("/find-All")
    public ResponseEntity<GenericResponse<Object>> findAll(@RequestParam int page,
                                                            @RequestParam int size,
                                                            @RequestParam String name){
        Pageable pageable = PageRequest.of(page,size);
                                                                
        return ResponseEntity.ok().body(GenericResponse.builder()
                        .success(true)
                        .message("Data berhasil di ambil")
                        .data(sampleService.findAll(name,pageable))
                        .build());
    }

    @GetMapping("/find-All-with-filter")
    public ResponseEntity<GenericResponse<Object>> findAllWithFilter(@RequestParam int page,
                                                            @RequestParam int size,
                                                            @RequestParam(required = false,defaultValue = "") String name,
                                                            @RequestParam(required = false) String code,
                                                            @RequestParam(required = false) Boolean status){
        Pageable pageable = PageRequest.of(page,size);
                                                                
        return ResponseEntity.ok().body(GenericResponse.builder()
                        .success(true)
                        .message("Data berhasil di ambil")
                        .data(sampleService.findAllWithFilter(name, code, status, pageable))
                        .build());
    }

    @PostMapping("/create")
    public ResponseEntity<GenericResponse<Object>> create(@RequestBody SampleDto dto){
        sampleService.create(dto);
        return ResponseEntity.ok().body(GenericResponse.builder()
                            .success(true)
                            .message("Data berhasil di tambahkan")
                            .data(null)
                            .build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GenericResponse<Object>> update(@PathVariable int id ,
                                         @RequestBody UpdateSampleDto dto){
        sampleService.update(id, dto);
        return ResponseEntity.ok().body(GenericResponse.builder()
                            .success(true)
                            .message("Data berhasil di update")
                            .data(null)
                            .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse<Object>> delete(@PathVariable int id){
        sampleService.delete(id);
        return ResponseEntity.ok().body(GenericResponse.builder()
                            .success(true)
                            .message("Data berhasil di delete")
                            .data(null)
                            .build());
    }

}
