package br.com.zup.proposta.cadastrabiometria;

import br.com.zup.proposta.novaproposta.criacartao.Cartao;

import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.apache.commons.codec.binary.Base64.*;

public class CadastraBiometriaRequest {

    @NotBlank
    private String biometria;

    public String getBiometria() {
        return biometria;
    }

    public Biometria toModel(Cartao cartao) {
        String biometria = Base64.getEncoder().encodeToString(this.biometria.getBytes(StandardCharsets.UTF_8));
        return new Biometria(biometria, cartao);
    }

    public boolean validaBase64() {
        return isBase64(this.biometria);
    }
}
