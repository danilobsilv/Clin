package clin.dan.api.Features.DoctorFeatures;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorModel, Long> {
    Page<DoctorModel> findAllByAtivoTrue(Pageable paginacao);
}
