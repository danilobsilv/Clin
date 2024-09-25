package clin.dan.api.Features.Consultation;

import clin.dan.api.Features.Consultation.ConsultationDTOs.CancelScheduledConsultationDTO;
import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationDataDetailsDTO;
import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import clin.dan.api.Features.Consultation.ConsultationDTOs.ScheduledConsultationDetailDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    ConsultationService consultationService;

    @PostMapping
    @Transactional
    public ResponseEntity<ConsultationDataDetailsDTO> scheduleConsultation(@RequestBody @Valid ConsultationScheduleDataDTO dataDetailsDTO){
        return consultationService.scheduleConsultation(dataDetailsDTO);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<CancelScheduledConsultationDTO> cancelScheduledConsultation(@RequestBody @Valid CancelScheduledConsultationDTO data){
        return consultationService.cancelScheduledConsultation(data);
    }

    @GetMapping
    public ResponseEntity<Page<ScheduledConsultationDetailDTO>> getAllScheduledConsultations(@PageableDefault Pageable pageable){
        return consultationService.getAllScheduledConsultations(pageable);
    }
}
