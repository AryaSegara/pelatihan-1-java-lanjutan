package com.pelatihan.pelatihan.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sample")
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor

public class Sample {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_date", columnDefinition = "DATE")
    private LocalDate createdDate;

    @Column(name = "update_date", columnDefinition = "DATE")
    private LocalDate updateDate;
}
