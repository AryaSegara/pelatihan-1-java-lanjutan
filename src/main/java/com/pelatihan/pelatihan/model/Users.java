package com.pelatihan.pelatihan.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "password", length = 500)
    private String password;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_date", columnDefinition = "DATE")
    private LocalDate createdDate;

    @Column(name = "update_date", columnDefinition = "DATE")
    private LocalDate updateDate;
}
