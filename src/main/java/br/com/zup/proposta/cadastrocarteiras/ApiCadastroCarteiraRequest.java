package br.com.zup.proposta.cadastrocarteiras;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ApiCadastroCarteiraRequest {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String carteira;

    public ApiCadastroCarteiraRequest(@NotBlank CadastroCarteirasRequest request) {
        this.email = request.getEmail();
        this.carteira = request.getCarteira().toString();
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
