package br.com.zup.proposta.detalhaproposta;

import br.com.zup.proposta.novaproposta.Proposta;
import br.com.zup.proposta.novaproposta.StatusEnum;

import java.math.BigDecimal;

public class DetalhaPropostaResponse {

    private Long id;
    private String documento;
    private String email;
    private String nome;
    private BigDecimal salario;
    private StatusEnum status;
    private DetalhaEnderecoResponse endereco;
    private DetalhaCartaoResponse cartao;

    public DetalhaPropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatus();
        this.endereco = new DetalhaEnderecoResponse(proposta.getEndereco());
        if (proposta.getCartao() != null){
            this.cartao = new DetalhaCartaoResponse(proposta.getCartao());
        }
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public DetalhaEnderecoResponse getEndereco() {
        return endereco;
    }

    public DetalhaCartaoResponse getCartao() {
        return cartao;
    }
}
