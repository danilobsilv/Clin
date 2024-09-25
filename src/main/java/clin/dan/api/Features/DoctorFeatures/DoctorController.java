package clin.dan.api.Features.DoctorFeatures;

import clin.dan.api.Features.DoctorFeatures.DoctorDTOs.DoctorDetailsDTO;
import clin.dan.api.Features.DoctorFeatures.DoctorDTOs.DoctorListingDataDTO;
import clin.dan.api.Features.DoctorFeatures.DoctorDTOs.InsertDoctorDTO;
import clin.dan.api.Features.DoctorFeatures.DoctorDTOs.UpdateDoctorDTO;
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
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDetailsDTO> registerDoctor(@RequestBody @Valid InsertDoctorDTO data, UriComponentsBuilder uriBuilder){
        return doctorService.registerDoctor(data, uriBuilder);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<DoctorListingDataDTO>> listDoctors(@PageableDefault(size=10, sort={"nome"}) Pageable pageable){
        return doctorService.listDoctors(pageable);
    }

    @GetMapping("/list/active")
    public ResponseEntity<Page<DoctorListingDataDTO>> listActiveDoctors(@PageableDefault(size=10, sort={"nome"}) Pageable pageable){
        return doctorService.listActiveDoctors(pageable);
    }

    @GetMapping("/doctorId/{doctorId}")
    public ResponseEntity<DoctorDetailsDTO> listDoctorsById(@PathVariable Long doctorId){
        return doctorService.listDoctorsById(doctorId);
    }


    @PutMapping("update")
    @Transactional
    public ResponseEntity<DoctorDetailsDTO> updateDoctor(@RequestBody @Valid UpdateDoctorDTO data){
        return doctorService.updateDoctor(data);
    }

    @DeleteMapping("/delete/doctorId/{doctorId}")
    @Transactional
    public ResponseEntity<DoctorModel> deleteDoctor(@PathVariable Long doctorId){
        return doctorService.deleteDoctor(doctorId);
    }

    @DeleteMapping("/delete/doctorId/{doctorId}/logical")
    @Transactional
    public ResponseEntity<DoctorModel> doctorLogicalDelete(@PathVariable long doctorId){
        return doctorService.doctorLogicalDelete(doctorId);
    }
}
