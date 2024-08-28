package clin.dan.api.Features.PatientFeatures;

import clin.dan.api.Features.PatientFeatures.PatientDTO.PatientListingDataDTO;
import clin.dan.api.Features.PatientFeatures.PatientDTO.PatientRegistrationDTO;
import clin.dan.api.Features.PatientFeatures.PatientDTO.UpdatePatienteDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public ResponseEntity<PatientListingDataDTO> registerPatient(@RequestBody @Valid PatientRegistrationDTO data, UriComponentsBuilder uriComponentsBuilder){
        var patient = new PatientModel(data);
        repository.save(patient);
        var uri = uriComponentsBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientListingDataDTO(patient));
    }

    public ResponseEntity<Page<PatientListingDataDTO>> listPatients(@PageableDefault(size=10, sort = {"nome"}) Pageable pageable){
        var page = repository.findAll(pageable).map(PatientListingDataDTO::new);

        return ResponseEntity.ok(page);
    }

    public ResponseEntity<PatientListingDataDTO> listPatientById(@PathVariable Long patientId){
        var patient = repository.getReferenceById(patientId);

        return ResponseEntity.ok(new PatientListingDataDTO(patient));
    }

    public ResponseEntity<PatientListingDataDTO> updatePatient(@RequestBody @Valid UpdatePatienteDTO data){
        var patient = repository.getReferenceById(data.id());
        patient.atualizarInformacoes(data);

        return ResponseEntity.ok(new PatientListingDataDTO(patient));
    }

    public ResponseEntity<PatientModel> deletePatient(@PathVariable Long patientId){
        repository.deleteById(patientId);

        return ResponseEntity.noContent().build();
    }
}
