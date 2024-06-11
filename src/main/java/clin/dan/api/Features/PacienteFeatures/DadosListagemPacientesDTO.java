package clin.dan.api.Features.PacienteFeatures;

public record DadosListagemPacientesDTO(
        String nome,
        String email,
        String telefone,
        String cpf
) {

    public DadosListagemPacientesDTO(PacienteModel pacienteModel){
        this(pacienteModel.getNome(), pacienteModel.getEmail(), pacienteModel.getTelefone(), pacienteModel.getCpf());
    }

}
