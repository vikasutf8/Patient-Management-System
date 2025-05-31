package com.pm.patient_service.mapper;

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
}
