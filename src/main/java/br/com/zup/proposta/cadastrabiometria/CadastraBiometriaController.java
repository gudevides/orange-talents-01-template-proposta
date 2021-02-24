package br.com.zup.proposta.cadastrabiometria;

import br.com.zup.proposta.novaproposta.Cartao;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/propostas")
public class CadastraBiometriaController {

    private CartaoRepository cartaoRepository;
    private BiometriaRepository biometriaRepository;

    public CadastraBiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
    }

    @PostMapping("/cartoes/{id}/biometrias")
    @Transactional
    public ResponseEntity<?> cadastra(@PathVariable @NotNull Long id, @RequestBody @Valid CadastraBiometriaRequest request, UriComponentsBuilder builder){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isPresent()){

            if (!request.validaBase64()){
                return ResponseEntity.badRequest().body("{\"Mensagem\": \"Biometria não está em Base64!\"}");
            }

            Biometria biometria = request.toModel(cartao.get());
            biometriaRepository.save(biometria);

            URI location = builder.path("/cartoes/{idCartao}/biometrias/{idBiometria}")
                    .buildAndExpand(cartao.get().getId(), biometria.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }

       return ResponseEntity.notFound().build();
    }

}
