package br.com.zup.proposta.novaproposta;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {

    @NotBlank
    private String cep;
    @NotBlank
    private String rua;
    @NotBlank
    private String numero;
    @NotBlank
    private String bairro;

    public EnderecoRequest(@NotBlank String cep, @NotBlank String rua, @NotBlank String numero, @NotBlank String bairro) {
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
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
