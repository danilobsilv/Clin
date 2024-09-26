package clin.dan.api.Features.PatientFeatures;

import clin.dan.api.Features.PatientFeatures.PatientDTO.PatientListingDataDTO;
import clin.dan.api.Features.PatientFeatures.PatientDTO.PatientRegistrationDTO;
import clin.dan.api.Features.PatientFeatures.PatientDTO.UpdatePatientDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientListingDataDTO> registerPatient(@RequestBody @Valid PatientRegistrationDTO data, UriComponentsBuilder uriComponentsBuilder){
        return patientService.registerPatient(data, uriComponentsBuilder);
    }

    @GetMapping()
    public ResponseEntity<Page<PatientListingDataDTO>> listPatients(@PageableDefault(size=10, sort = {"nome"}) Pageable pageable){
        return patientService.listPatients(pageable);
    }

    @GetMapping("patientId/{patientId}")
    public ResponseEntity<PatientListingDataDTO> listPatientById(@PathVariable Long patientId){
        return patientService.listPatientById(patientId);
    }

    @PutMapping("update")
    @Transactional
    public ResponseEntity<PatientListingDataDTO> updatePatient(@RequestBody @Valid UpdatePatientDTO data){
        return patientService.updatePatient(data);
    }

    @DeleteMapping("delete/patientId/{patientId}")
    @Transactional
    public ResponseEntity<PatientModel> deletePatient(@PathVariable Long patientId){
        return patientService.deletePatient(patientId);
    }
}
