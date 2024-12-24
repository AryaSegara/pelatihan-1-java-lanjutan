package com.pelatihan.pelatihan.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Sample {
    private int id;
    private String name;
    private String code;
    private String description;
    private Boolean status;

    private LocalDate createdDate;
    private LocalDate updateDate;
}
