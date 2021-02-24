package br.com.zup.proposta.novaproposta;

import br.com.zup.proposta.config.validacao.CPFouCNPJ;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank @CPFouCNPJ
    private String documento;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String nome;
    @NotNull @Valid
    private EnderecoRequest endereco;
    @NotNull @Positive
    private BigDecimal salario;

    public NovaPropostaRequest(@NotBlank String documento, @NotBlank @Email String email,
                               @NotBlank String nome, @NotBlank EnderecoRequest endereco,
                               @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getDocumento() {
        return documento;
    }

    public Proposta toModel() {
        return new Proposta(this.documento, this.email, this.nome, new Endereco(this.endereco), this.salario);
    }
}
