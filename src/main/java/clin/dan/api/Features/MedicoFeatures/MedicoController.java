package clin.dan.api.Features.MedicoFeatures;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("medico")
public class MedicoController{

    @Autowired
    private MedicoRepository repository;


    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedicoDTO dados){
        repository.save(new MedicoModel(dados));
        System.out.println("Médico inserido!");
    }

    @GetMapping("/doctor-list")
    public Page<DadosListagemMedicosDTO> listarMedicos(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemMedicosDTO::new);
    }

    @PutMapping()
    @Transactional
    public void atualizarMedico(@RequestBody @Valid DadosAtualizacaoMedicoDTO dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }
}
