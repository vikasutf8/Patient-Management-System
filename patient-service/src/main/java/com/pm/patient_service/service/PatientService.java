package com.pm.patient_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pm.patient_service.DTO.PatientRequestDTO;
import com.pm.patient_service.DTO.PatientResponseDTO;
import com.pm.patient_service.exception.EmailAlreadyExistsException;
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

}
