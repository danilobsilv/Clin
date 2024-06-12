package clin.dan.api.Features.MedicoFeatures;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {
    Page<MedicoModel> findAllByAtivoTrue(Pageable paginacao);
}
