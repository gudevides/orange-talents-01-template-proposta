package br.com.zup.proposta.bloqueiocartao;

import br.com.zup.proposta.cadastrabiometria.CartaoRepository;
import br.com.zup.proposta.apicompartilhada.ApiCartao;
import br.com.zup.proposta.novaproposta.criacartao.Cartao;
import br.com.zup.proposta.novaproposta.criacartao.StatusCartaoEnum;
import feign.FeignException;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/api/propostas/cartoes")
public class BloqueioCartaoController {

    private final CartaoRepository cartaoRepository;
    private final ApiCartao apiCartao;

    public BloqueioCartaoController(CartaoRepository cartaoRepository, ApiCartao apiCartao) {
        this.cartaoRepository = cartaoRepository;
        this.apiCartao = apiCartao;
    }

    @PostMapping("/{id}/bloqueio")
    public ResponseEntity<?> bloqueia(@PathVariable @NotNull @NumberFormat Long id, HttpServletRequest httpServletRequest){
        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if (cartao.isPresent()){
            if (cartao.get().getStatus().equals(StatusCartaoEnum.BLOQUEADO)){
                return ResponseEntity.unprocessableEntity()
                        .body("{\"Mensagem\": \"Cartao já bloqueado\"}");
            }
            try {
                apiCartao.bloqueio(cartao.get().getNumero(), new BloqueiaCartaoRequest("proposta"));
                Bloqueio bloqueio = new Bloqueio(httpServletRequest.getRemoteAddr(), httpServletRequest.getHeader("User-Agent"), cartao.get());
                cartao.get().atualizaBloqueio(bloqueio);
                cartaoRepository.save(cartao.get());
                return ResponseEntity.ok().build();
            }catch (FeignException.UnprocessableEntity ue){
                return ResponseEntity.unprocessableEntity()
                        .body("{\"Mensagem\": \"Cartao já bloqueado\"}");
            } catch (FeignException.InternalServerError ise){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("{\"Mensagem\": \"Ocorreu um erro interno do sistema de bloqueios.\"}");
            }
        }

        return ResponseEntity.notFound().build();
    }
}
