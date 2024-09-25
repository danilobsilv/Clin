package clin.dan.api.Features.PatientFeatures;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientModel, Long> {
    Boolean findActiveById(Long aLong);
}
