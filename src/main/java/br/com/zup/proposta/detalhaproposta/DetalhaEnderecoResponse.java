package br.com.zup.proposta.detalhaproposta;

import br.com.zup.proposta.novaproposta.Endereco;

public class DetalhaEnderecoResponse {

    private String cep;
    private String rua;
    private String numero;
    private String bairro;

    public DetalhaEnderecoResponse(Endereco endereco) {
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
