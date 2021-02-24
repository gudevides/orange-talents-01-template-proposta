package br.com.zup.proposta.avisoviagem;

import br.com.zup.proposta.novaproposta.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotBlank
    private String destino;
    @NotNull @Future @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataFim;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoViagemRequest(@NotBlank String destino, @NotNull @Future LocalDate dataFim) {
        this.destino = destino;
        this.dataFim = dataFim;
    }

    public AvisoViagem toModel(HttpServletRequest httpServletRequest, Cartao cartao) {
        return new AvisoViagem(destino, dataFim, httpServletRequest, cartao);
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }
}
