package clin.dan.api.Features.PacienteFeatures;

public record DadosListagemPacientesDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf
) {

    public DadosListagemPacientesDTO(PacienteModel pacienteModel){
        this(pacienteModel.getId(), pacienteModel.getNome(), pacienteModel.getEmail(), pacienteModel.getTelefone(), pacienteModel.getCpf());
    }

}
