package br.com.zup.proposta.bloqueiocartao;

import javax.validation.constraints.NotBlank;

public class BloqueiaCartaoRequest {

    @NotBlank
    private String sistemaResponsavel;

    public BloqueiaCartaoRequest(@NotBlank String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
