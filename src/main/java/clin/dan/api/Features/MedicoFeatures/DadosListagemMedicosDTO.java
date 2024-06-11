package clin.dan.api.Features.MedicoFeatures;


// na aplicação, ele pede pra listar apenas esse atributos, por isso criei esse dto, se eu passasse o médico, ele retornaria tudo


public record DadosListagemMedicosDTO(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {


    public DadosListagemMedicosDTO(MedicoModel medicoModel){
        this(medicoModel.getId(), medicoModel.getNome(), medicoModel.getEmail(), medicoModel.getCrm(), medicoModel.getEspecialidade());
    }
}
