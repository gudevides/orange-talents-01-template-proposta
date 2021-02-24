package br.com.zup.proposta.bloqueiocartao;

import br.com.zup.proposta.novaproposta.criacartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime dataBloqueio;
    @NotNull
    private String ipSolicitante;
    @NotNull
    private String sistemaResponsavel;
    @OneToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio (){}

    public Bloqueio(@NotNull String ipSolicitante, @NotNull String sistemaResponsavel, Cartao cartao) {
        this.dataBloqueio = LocalDateTime.now();
        this.ipSolicitante = ipSolicitante;
        this.sistemaResponsavel = sistemaResponsavel;
        this.cartao = cartao;
    }
}
