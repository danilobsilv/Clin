package clin.dan.api.Features.PacienteFeatures;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemPacientesDTO> cadastrarPaciente(@RequestBody @Valid DadosCadastroPacienteDTO dados, UriComponentsBuilder uriComponentsBuilder){
        var paciente = new PacienteModel(dados);
        repository.save(paciente);
        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemPacientesDTO(paciente));
    }

    @GetMapping()
    public ResponseEntity<Page<DadosListagemPacientesDTO>> listarPacientes(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemPacientesDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("{pacienteId}")
    public ResponseEntity<DadosListagemPacientesDTO> listarPacientePorId(@PathVariable Long pacienteId){
        var paciente = repository.getReferenceById(pacienteId);

        return ResponseEntity.ok(new DadosListagemPacientesDTO(paciente));
    }

    @PutMapping("{pacienteId}")
    @Transactional
    public ResponseEntity<DadosListagemPacientesDTO> atualizarPaciente(@PathVariable Long pacienteId, @RequestBody @Valid DadosAtualizacaoPacientesDTO dados){
        var paciente = repository.getReferenceById(pacienteId);
        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosListagemPacientesDTO(paciente));
    }

    @DeleteMapping("{pacienteId}")
    @Transactional
    public void excluirPaciente(@PathVariable Long pacienteId){

        repository.deleteById(pacienteId);
    }
}
