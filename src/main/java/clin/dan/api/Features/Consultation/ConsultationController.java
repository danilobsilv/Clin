package clin.dan.api.Features.Consultation;

import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationDataDetailsDTO;
import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
