package clin.dan.api.Features.Consultation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultationRepository extends JpaRepository<ConsultationModel, Long> {

    @Query("""
            delete from Consultation c
            where c.date = :date
            """)
    void deleteConsultation(LocalDateTime date);
}
