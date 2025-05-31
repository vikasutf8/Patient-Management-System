package com.pm.patient_service.mapper;

import java.time.LocalDate;

import com.pm.patient_service.DTO.PatientRequestDTO;
import com.pm.patient_service.DTO.PatientResponseDTO;
import com.pm.patient_service.modal.Patient;

public class PatientMapper {

     public static PatientResponseDTO toDTO(Patient patient){
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDateOfBirth(patient.getDataOfBirth().toString());

        return patientDTO;
     }

     public static Patient toModal(PatientRequestDTO patientRequestDTO){
         Patient patient = new Patient();
         patient.setName(patientRequestDTO.getName());
         patient.setEmail(patientRequestDTO.getEmail());
         patient.setAddress(patientRequestDTO.getAddress());
         patient.setDataOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
         patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
         return patient;
     }

}
