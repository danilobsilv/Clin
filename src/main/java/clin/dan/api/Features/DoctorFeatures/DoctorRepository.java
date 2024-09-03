package clin.dan.api.Features.DoctorFeatures;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<DoctorModel, Long> {
    Page<DoctorModel> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select m from Medico m
            where m.ativo = true
            and
            m.specialty = :specialty
            and
            m.id not in(
                select c.doctor.id from Consultation c
                where
                c.date = :date
            )
            order by rand()
            limit 1
            """)
    DoctorModel chooseRandomFreeDoctorInTheDate(Specialty specialty, LocalDateTime date);
}
