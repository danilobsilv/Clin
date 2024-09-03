package clin.dan.api.Features.Consultation;

import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationDataDetailsDTO;
import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ConsultationService {

    @Autowired
    ConsultationRules consultationRules;

    public ResponseEntity<ConsultationDataDetailsDTO> scheduleConsultation(@RequestBody @Valid ConsultationScheduleDataDTO dataDetailsDTO){
        return ResponseEntity.ok(new ConsultationDataDetailsDTO(null, null, null, null ));
     }

}
