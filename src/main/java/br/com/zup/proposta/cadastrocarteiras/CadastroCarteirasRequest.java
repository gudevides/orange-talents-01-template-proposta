package br.com.zup.proposta.cadastrocarteiras;

import br.com.zup.proposta.novaproposta.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CadastroCarteirasRequest {

    @NotBlank @Email
    private String email;
    @NotNull
    private CarteiraEnum carteira;

    public CadastroCarteirasRequest(@NotBlank @Email String email, @NotBlank CarteiraEnum carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public CarteiraEnum getCarteira() {
        return carteira;
    }

    public Carteira toModel(Cartao cartao) {
        return new Carteira(this.email, this.carteira, cartao);
    }
}
