package com.pm.patient_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pm.patient_service.DTO.PatientRequestDTO;
import com.pm.patient_service.DTO.PatientResponseDTO;
import com.pm.patient_service.exception.EmailAlreadyExistsException;
import com.pm.patient_service.exception.PatientNotFoundException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.modal.Patient;
import com.pm.patient_service.repository.PatientRepository;

import jakarta.validation.constraints.Email;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    //why do it: dependency  injection :Service recive dependency :principel of inversion control
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatient(){
        List<Patient> patients = patientRepository.findAll();
        //convert list<patient> to list<PatientResponseDTO> object
        List<PatientResponseDTO> patientResponseDTOs = patients.stream().map(PatientMapper::toDTO).toList();

        return patientResponseDTOs;


    }


    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){

       if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
        throw new  EmailAlreadyExistsException("A patient with this email already exists" + patientRequestDTO.getEmail());
       } 
       Patient newPatient = patientRepository.save(PatientMapper.toModal(patientRequestDTO));

        //an email must be unique




        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id ,PatientRequestDTO patientRequestDTO){

        
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found" +id));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)){
        throw new  EmailAlreadyExistsException("A patient with this email already exists" + patientRequestDTO.getEmail());
       } 

       patient.setName(patientRequestDTO.getName());
       patient.setAddress(patientRequestDTO.getAddress());
       patient.setDataOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
       patient.setEmail(patientRequestDTO.getEmail());
    //    patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        
        return PatientMapper.toDTO(patientRepository.save(patient));
        
        
    }


    public void deletePatient(UUID id){
         patientRepository.deleteById(id);
    }

}
