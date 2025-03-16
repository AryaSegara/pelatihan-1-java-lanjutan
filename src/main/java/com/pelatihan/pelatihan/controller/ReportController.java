package com.pelatihan.pelatihan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pelatihan.pelatihan.dto.GenericResponse;
import com.pelatihan.pelatihan.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
    
    @Autowired
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    
     @GetMapping("/generateExcel")
    public ResponseEntity<Object> generateExcel(){
        try {
            return ResponseEntity.ok()
                                    .body(reportService.generateExcel());

        } catch (ResponseStatusException rse) {
            return ResponseEntity.status(rse.getStatusCode())
                                        .body(GenericResponse.builder()
                                        .success(false)
                                        .message(rse.getReason())
                                        .data(null)
                                        .build());
        }catch(Exception e){
            // e.printStackTrace();
            return ResponseEntity.internalServerError()
                                        .body(GenericResponse.builder()
                                        .success(false)
                                        .message("Internal Server Error")
                                        .data(null)
                                        .build());
        }
    }
}
