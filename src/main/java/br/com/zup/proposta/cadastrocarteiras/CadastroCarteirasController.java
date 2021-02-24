package br.com.zup.proposta.cadastrocarteiras;

import br.com.zup.proposta.cadastrabiometria.CartaoRepository;
import br.com.zup.proposta.apicompartilhada.ApiCartao;
import br.com.zup.proposta.novaproposta.criacartao.Cartao;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/propostas/cartoes")
public class CadastroCarteirasController {

    private final CartaoRepository cartaoRepository;
    private final CarteiraRepository carteiraRepository;
    private final ApiCartao apiCartao;

    public CadastroCarteirasController(CartaoRepository cartaoRepository, CarteiraRepository carteiraRepository, ApiCartao apiCartao) {
        this.cartaoRepository = cartaoRepository;
        this.carteiraRepository = carteiraRepository;
        this.apiCartao = apiCartao;
    }

    @PostMapping("/{id}/carteiras")
    public ResponseEntity<?> cadastra (@PathVariable @NotNull Long id, @RequestBody @Valid CadastroCarteirasRequest request, UriComponentsBuilder uriComponentsBuilder){

        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if (cartao.isPresent()){
            try {
                apiCartao.associaCarteira(cartao.get().getNumero(), new ApiCadastroCarteiraRequest(request));
                Carteira carteira = request.toModel(cartao.get());
                carteiraRepository.save(carteira);
                URI location = uriComponentsBuilder
                        .path("/api/propostas/cartoes/{idCartao}/carteiras/{idCarteira}")
                        .buildAndExpand(cartao.get().getId(), carteira.getId()).toUri();
                return ResponseEntity.created(location).build();
            } catch (FeignException.UnprocessableEntity ue){
                return ResponseEntity.unprocessableEntity().body("{\"Mensagem\": \"Cartão já associado com esta carteira!\"}");
            } catch (FeignException.InternalServerError ise){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("{\"Mensagem\": \"Ocorreu um erro interno do sistema de bloqueios.\"}");
            }
        }
        return ResponseEntity.notFound().build();
    }
}
