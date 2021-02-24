package br.com.zup.proposta.novaproposta;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
public class Endereco {

    @NotNull
    private String cep;
    @NotNull
    private String rua;
    @NotNull
    private String numero;
    @NotNull
    private String bairro;

    @Deprecated
    public Endereco(){}

    public Endereco(@NotBlank EnderecoRequest endereco) {
        this.cep = endereco.getCep();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
    }

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }
}
