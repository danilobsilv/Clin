package clin.dan.api.Features.MedicoFeatures;

import clin.dan.api.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco dadosEndereco) {
}
