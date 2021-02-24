package br.com.zup.proposta.detalhaproposta;

import br.com.zup.proposta.novaproposta.Cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DetalhaCartaoResponse {

    private String numero;
    private String titular;
    private BigDecimal limite;
    private LocalDateTime emitidoEm;

    public DetalhaCartaoResponse(Cartao cartao) {
        this.numero = cartao.getNumero();
        this.titular = cartao.getTitular();
        this.limite = cartao.getLimite();
        this.emitidoEm = cartao.getEmitidoEm();
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }
}
