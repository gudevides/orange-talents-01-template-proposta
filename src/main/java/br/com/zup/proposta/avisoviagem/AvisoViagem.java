package br.com.zup.proposta.avisoviagem;

import br.com.zup.proposta.novaproposta.Cartao;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String destino;
    @NotNull
    private LocalDate dataFim;
    @NotNull
    private LocalDateTime instanteCriacao;
    @NotNull
    private String ipSolicitacao;
    @NotNull
    private String agenteSolicitante;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem(){}

    public AvisoViagem(@NotBlank String destino, @NotNull @Future LocalDate dataFim, HttpServletRequest httpServletRequest, Cartao cartao) {
        this.destino = destino;
        this.dataFim = dataFim;
        this.instanteCriacao = LocalDateTime.now();
        this.ipSolicitacao = httpServletRequest.getRemoteAddr();
        this.agenteSolicitante = httpServletRequest.getHeader("User-Agent");
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisoViagem that = (AvisoViagem) o;
        return Objects.equals(id, that.id) && Objects.equals(destino, that.destino) && Objects.equals(dataFim, that.dataFim) && Objects.equals(instanteCriacao, that.instanteCriacao) && Objects.equals(ipSolicitacao, that.ipSolicitacao) && Objects.equals(agenteSolicitante, that.agenteSolicitante) && Objects.equals(cartao, that.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destino, dataFim, instanteCriacao, ipSolicitacao, agenteSolicitante, cartao);
    }
}
