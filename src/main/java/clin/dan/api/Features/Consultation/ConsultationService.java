package clin.dan.api.Features.Consultation;

import clin.dan.api.Features.Consultation.ConsultationDTOs.CancelScheduledConsultationDTO;
import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationDataDetailsDTO;
import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import clin.dan.api.Features.Consultation.ConsultationDTOs.ScheduledConsultationDetailDTO;
import jakarta.validation.Valid;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    ScheduleConsultation scheduleConsultation;

    @Autowired
    ConsultationRepository consultationRepository;

    public ResponseEntity<ConsultationDataDetailsDTO> scheduleConsultation(@RequestBody @Valid ConsultationScheduleDataDTO dataDetailsDTO){
        var dto = scheduleConsultation.scheduleConsultation(dataDetailsDTO);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<CancelScheduledConsultationDTO> cancelScheduledConsultation(@RequestBody @Valid CancelScheduledConsultationDTO data){
        scheduleConsultation.cancelScheduledConsultation(data);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Page<ScheduledConsultationDetailDTO>> getAllScheduledConsultations(@PageableDefault Pageable pageable){
        var consultations = consultationRepository.findAll(pageable).map(ScheduledConsultationDetailDTO::new);

        return ResponseEntity.ok(consultations);
    }
}
