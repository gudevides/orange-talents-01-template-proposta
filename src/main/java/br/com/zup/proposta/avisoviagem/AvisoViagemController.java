package br.com.zup.proposta.avisoviagem;

import br.com.zup.proposta.cadastrabiometria.CartaoRepository;
import br.com.zup.proposta.apicompartilhada.ApiCartao;
import br.com.zup.proposta.novaproposta.criacartao.Cartao;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/api/propostas/cartoes")
public class AvisoViagemController {

    private final CartaoRepository cartaoRepository;
    private final ApiCartao apiCartao;

    public AvisoViagemController(CartaoRepository cartaoRepository, ApiCartao apiCartao) {
        this.cartaoRepository = cartaoRepository;
        this.apiCartao = apiCartao;
    }

    @PostMapping("/{id}/viagem")
    private ResponseEntity<?> cadastra(@PathVariable @NotNull Long id, @RequestBody @Valid AvisoViagemRequest request, HttpServletRequest httpServletRequest){

        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if (cartao.isPresent()){
            try {
                apiCartao.avisoViagem(cartao.get().getNumero(), new ApiAvisoCartaoRequest(request));
                AvisoViagem avisoViagem = request.toModel(httpServletRequest, cartao.get());
                cartao.get().atualizaAvisoViagem(avisoViagem);
                cartaoRepository.save(cartao.get());
                return ResponseEntity.ok().build();
            } catch (FeignException e) {
                return ResponseEntity.status(e.status()).build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}
