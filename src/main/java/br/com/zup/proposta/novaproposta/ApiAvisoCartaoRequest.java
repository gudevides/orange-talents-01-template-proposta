package br.com.zup.proposta.novaproposta;

import br.com.zup.proposta.avisoviagem.AvisoViagemRequest;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ApiAvisoCartaoRequest {

    @NotBlank
    private String destino;
    @NotNull
    @Future
    private LocalDate validoAte;

    public ApiAvisoCartaoRequest(AvisoViagemRequest request) {
        this.destino = request.getDestino();
        this.validoAte = request.getDataFim();
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
