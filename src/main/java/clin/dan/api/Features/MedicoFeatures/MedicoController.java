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

    @GetMapping("/all-list")
    public Page<DadosListagemMedicosDTO> listarMedicos(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemMedicosDTO::new);
    }

    @GetMapping("/active-doctors-list")
    public Page<DadosListagemMedicosDTO> listarMedicosAtivos(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
       return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicosDTO::new);
    //     existe um padrão de nomenclatura do spring data que se eu criar um método com um determinado padrão de nomenclatura,
    //     ele consegue montar a query da maneira que eu desejar, daí não vai ser necessário alterar o dto como eu fiz
    }

    @PutMapping()
    @Transactional
    public void atualizarMedico(@RequestBody @Valid DadosAtualizacaoMedicoDTO dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/del/{medicoId}")
    @Transactional
    public void exclusaoMedico(@PathVariable Long medicoId){  // esse aqui vai apagar do banco, de modo literal
        repository.deleteById(medicoId);
    }

    @DeleteMapping("exclusaoLogica/{medicoId}")
    @Transactional
    public void exclusaoLogicaMedico(@PathVariable long medicoId){
        var medico = repository.getReferenceById(medicoId);
        medico.exclusaoLogica();
    }

}
