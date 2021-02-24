package br.com.zup.proposta.novaproposta;

import br.com.zup.proposta.avisoviagem.AvisoViagem;
import br.com.zup.proposta.bloqueiocartao.Bloqueio;
import br.com.zup.proposta.cadastrabiometria.Biometria;
import br.com.zup.proposta.cadastrocarteiras.Carteira;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String numero;
    @NotNull
    private LocalDateTime emitidoEm;
    @NotNull
    private String titular;
    @NotNull
    private BigDecimal limite;
    @OneToOne
    private Proposta proposta;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusCartaoEnum status;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Biometria> biometrias = new ArrayList<>();

    @OneToOne(mappedBy = "cartao", cascade = CascadeType.ALL)
    private Bloqueio bloqueio;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<AvisoViagem> avisoViagem = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Carteira> carteiras = new ArrayList<>();

    @Deprecated
    public Cartao(){}

    public Cartao(ConsultaCartaoResponse cartaoResponse, Proposta proposta) {
        this.numero = cartaoResponse.getId();
        this.emitidoEm = cartaoResponse.getEmitidoEm();
        this.titular = cartaoResponse.getTitular();
        this.limite = cartaoResponse.getLimite();
        this.proposta = proposta;
        this.status = StatusCartaoEnum.CRIADO;
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public StatusCartaoEnum getStatus() {
        return status;
    }

    public void atualizaBloqueio(Bloqueio bloqueio) {
        this.bloqueio = bloqueio;
        this.status = StatusCartaoEnum.BLOQUEADO;
    }

    public void atualizaAvisoViagem(AvisoViagem avisoViagem) {
        this.avisoViagem.add(avisoViagem);
    }

    public void adicionaCarteira(Carteira carteira) {
        this.carteiras.add(carteira);
    }
}
