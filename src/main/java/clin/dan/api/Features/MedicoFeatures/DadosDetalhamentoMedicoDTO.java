package clin.dan.api.Features.MedicoFeatures;

import clin.dan.api.Endereco;

public record DadosDetalhamentoMedicoDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco) {

    public DadosDetalhamentoMedicoDTO(MedicoModel medicoModel){
        this(medicoModel.getId(), medicoModel.getNome(), medicoModel.getEmail(), medicoModel.getCrm(), medicoModel.getTelefone(), medicoModel.getEspecialidade(), medicoModel.getEndereco());
    }

}
