package br.com.zup.proposta.detalhaproposta;

import br.com.zup.proposta.novaproposta.Proposta;
import br.com.zup.proposta.novaproposta.PropostaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/propostas")
public class DetalhaPropostaController {
    
    private final PropostaRepository propostaRepository;

    public DetalhaPropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhaPropostaResponse> detalha(@PathVariable Long id){
        Optional<Proposta> proposta = propostaRepository.findById(id);
        return proposta.map(value -> ResponseEntity.ok().body(new DetalhaPropostaResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
