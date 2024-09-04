package clin.dan.api.Features.Consultation;

import clin.dan.api.Features.Consultation.ConsultationDTOs.CancelScheduledConsultationDTO;
import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationDataDetailsDTO;
import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
