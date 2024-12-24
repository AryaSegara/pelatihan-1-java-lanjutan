package com.pelatihan.pelatihan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pelatihan.pelatihan.dto.SampleDto;
import com.pelatihan.pelatihan.dto.UpdateSampleDto;
import com.pelatihan.pelatihan.service.SampleService;

@RestController
@RequestMapping("/sample")
public class SampleController {
    private final SampleService sampleService;

    
    SampleController(SampleService sampleService){
        this.sampleService = sampleService;
    }
    
    @GetMapping("/find-All")
    public ResponseEntity<List<SampleDto>> findAll(){
        return ResponseEntity.ok().body(sampleService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody SampleDto dto){
        sampleService.create(dto);
        return ResponseEntity.ok().body("Data berhasil di tambahkan");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable int id ,
                                         @RequestBody UpdateSampleDto dto){
        sampleService.update(id, dto);
        return ResponseEntity.ok().body("Data berhasil di update");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        sampleService.delete(id);
        return ResponseEntity.ok().body("Data berhasil di delete");
    }

}
