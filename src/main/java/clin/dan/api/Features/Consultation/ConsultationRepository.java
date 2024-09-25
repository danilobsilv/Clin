package clin.dan.api.Features.Consultation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<ConsultationModel, Long> {

    @Query("""
            delete from Consultation c
            where c.date = :date
            """)
    void deleteConsultation(LocalDateTime date);

    @Query("""
        select case when count(c) > 0 then true else false end
        from Consultation c
        where c.doctor_id.id = :doctorId
        and c.date = :date
        """)
    Boolean existsByDoctorIdAndDate(Long doctorId, LocalDateTime date);

//    @Query("""
//    select case when count(c) > 0 then true else false end
//    from Consultation c
//    where c.patient.id = :patientId
//    and c.date between :startDateTime and :endDateTime
//    """)
    @Query("""
    select case when count(c) > 0 then true else false end
    from Consultation c
    where c.patient_id.id = :patientId
    """)
    Boolean existsByPatientAndDateBetween(Long patientId);

    @Query("""
            select c from Consultation c
            """)
    List<ConsultationModel> findAllConsultations();
}
