package clin.dan.api.Features.PacienteFeatures;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPacienteDTO dados){
        repository.save(new PacienteModel(dados));
        System.out.println("paciente inserido com sucesso");
    }

    @GetMapping("/pacient-list")
    @Transactional
    public Page<DadosListagemPacientesDTO> listarPacientes(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemPacientesDTO::new);
    }
}
