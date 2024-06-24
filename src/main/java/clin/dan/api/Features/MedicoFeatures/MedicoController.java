package clin.dan.api.Features.MedicoFeatures;

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
@RequestMapping("medico")
public class MedicoController{

    @Autowired
    private MedicoRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDTO> cadastrarMedico(@RequestBody @Valid DadosCadastroMedicoDTO dados, UriComponentsBuilder uriBuilder){
        var medico = new MedicoModel(dados);

        repository.save(medico);

        var uri = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medico));
    }

    @GetMapping("/all-list")
    public ResponseEntity<Page<DadosListagemMedicosDTO>> listarMedicos(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemMedicosDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/active-doctors-list")
    public ResponseEntity<Page<DadosListagemMedicosDTO>> listarMedicosAtivos(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
       var list = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicosDTO::new);
       return ResponseEntity.ok(list);
    //     existe um padrão de nomenclatura do spring data que se eu criar um método com um determinado padrão de nomenclatura,
    //     ele consegue montar a query da maneira que eu desejar, daí não vai ser necessário alterar o dto como eu fiz
    }

    @GetMapping("/{medicoId}")
    public ResponseEntity<DadosDetalhamentoMedicoDTO> listarMedicosPorId(@PathVariable Long medicoId){
        var medico = repository.getReferenceById(medicoId);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }


    @PutMapping()
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDTO> atualizarMedico(@RequestBody @Valid DadosAtualizacaoMedicoDTO dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }

    @DeleteMapping("/del/{medicoId}")
    @Transactional
    public ResponseEntity<MedicoModel> exclusaoMedico(@PathVariable Long medicoId){  // esse aqui vai apagar do banco, de modo literal
        repository.deleteById(medicoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/exclusaoLogica/{medicoId}")
    @Transactional
    public ResponseEntity<MedicoModel> exclusaoLogicaMedico(@PathVariable long medicoId){
        var medico = repository.getReferenceById(medicoId);
        medico.exclusaoLogica();
        return ResponseEntity.noContent().build();
    }

}
