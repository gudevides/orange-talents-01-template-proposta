package br.com.zup.proposta.novaproposta;

import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/propostas")
public class NovaPropostaController {

    private final PropostaRepository propostaRepository;
    private final ApiStatus apiStatus;
    private final Tracer tracer;


    public NovaPropostaController(PropostaRepository propostaRepository, ApiStatus apiStatus, Tracer tracer) {
        this.propostaRepository = propostaRepository;
        this.apiStatus = apiStatus;
        this.tracer = tracer;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uriBuilder){
        if (propostaRepository.existsByDocumento(request.getDocumento())){
            return ResponseEntity.unprocessableEntity().body("{\"Mensagem\": \"Documento já cadastrado\"}");
        }

        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);

        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("user.email", proposta.getEmail());
        activeSpan.setBaggageItem("user.email", proposta.getEmail());
        activeSpan.log("Log criação proposta");

        try {
            apiStatus.consulta(new ApiStatus.ConsultaStatusRequest(proposta));
            proposta.atualizaStatus(StatusEnum.ELEGIVEL);
        } catch (FeignException.UnprocessableEntity e) {
            proposta.atualizaStatus(StatusEnum.NAO_ELEGIVEL);
        }

        propostaRepository.save(proposta);

        URI location = uriBuilder.path("/api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
