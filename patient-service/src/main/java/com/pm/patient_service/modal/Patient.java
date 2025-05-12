package com.pm.patient_service.modal;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;



import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id ;

    @NotNull
    private String name;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String address;


    @NotNull
    private LocalDate dataOfBirth;

    @NotNull
    private LocalDate registeredDate;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDataOfBirth() {
        return dataOfBirth;
    }

    public void setDataOfBirth(LocalDate dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

}



