package clin.dan.api.Features.DoctorFeatures;

import clin.dan.api.Features.DoctorFeatures.DoctorDTOs.DoctorDetailsDTO;
import clin.dan.api.Features.DoctorFeatures.DoctorDTOs.DoctorListingDataDTO;
import clin.dan.api.Features.DoctorFeatures.DoctorDTOs.InsertDoctorDTO;
import clin.dan.api.Features.DoctorFeatures.DoctorDTOs.UpdateDoctorDTO;
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
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public ResponseEntity<DoctorDetailsDTO> registerDoctor(@RequestBody @Valid InsertDoctorDTO data, UriComponentsBuilder uriBuilder){
        var medico = new DoctorModel(data);
        repository.save(medico);
        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDetailsDTO(medico));
    }

    public ResponseEntity<Page<DoctorListingDataDTO>> listDoctors(@PageableDefault(size=10, sort={"nome"}) Pageable pageable){
        var page = repository.findAll(pageable).map(DoctorListingDataDTO::new);

        return ResponseEntity.ok(page);
    }

     public ResponseEntity<Page<DoctorListingDataDTO>> listActiveDoctors(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
        var list = repository.findAllByAtivoTrue(paginacao).map(DoctorListingDataDTO::new);

        return ResponseEntity.ok(list);
    }

    public ResponseEntity<DoctorDetailsDTO> listDoctorsById(@PathVariable Long doctorId){
        var medico = repository.getReferenceById(doctorId);

        return ResponseEntity.ok(new DoctorDetailsDTO(medico));
    }

    public ResponseEntity<DoctorDetailsDTO> updateDoctor(@RequestBody @Valid UpdateDoctorDTO data){
        var doctor = repository.getReferenceById(data.id());
        doctor.atualizarInformacoes(data);

        return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
    }

     public ResponseEntity<DoctorModel> deleteDoctor(@PathVariable Long doctorId) {
         repository.deleteById(doctorId);

         return ResponseEntity.noContent().build();
     }

     public ResponseEntity<DoctorModel> doctorLogicalDelete(@PathVariable long doctorId){
        var medico = repository.getReferenceById(doctorId);
        medico.exclusaoLogica();

        return ResponseEntity.noContent().build();
    }
}
