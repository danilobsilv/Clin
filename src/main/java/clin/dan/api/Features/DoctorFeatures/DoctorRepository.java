package clin.dan.api.Features.DoctorFeatures;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<DoctorModel, Long> {

    @Query("""
            select m.active from Doctor m
            order by m.name asc
            """)
    Page<DoctorModel> findAllByActiveTrue(Pageable pageable);

    @Query("""
            select m.active from Doctor m
            where m.id = :doctorId
            """)
    Boolean findActiveDoctorById(Long doctorId);

    @Query("""
            select m from Doctor m
            where m.active = true
            and
            m.specialty = :specialty
            and
            m.id not in(
                select c.doctor_id.id from Consultation c
                where
                c.date = :date
            )
            order by rand()
            limit 1
            """)
    DoctorModel chooseRandomFreeDoctorInTheDate(Specialty specialty, LocalDateTime date);
}
