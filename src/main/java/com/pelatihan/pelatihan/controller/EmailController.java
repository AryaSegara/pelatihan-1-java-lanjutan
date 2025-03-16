package com.pelatihan.pelatihan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pelatihan.pelatihan.dto.EmailDto;
import com.pelatihan.pelatihan.dto.GenericResponse;
import com.pelatihan.pelatihan.dto.LoginDto;
import com.pelatihan.pelatihan.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    
    @PostMapping("/send")
    public ResponseEntity<Object> sendEmail(@RequestBody EmailDto dto){
        try {
            emailService.sendEmail(dto.getTo(), dto.getSubject(), dto.getBody());
            return ResponseEntity.ok().body(GenericResponse.builder()
                                        .success(true)
                                        .message("Successfully send Email")
                                        .data(null)
                                        .build());

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
