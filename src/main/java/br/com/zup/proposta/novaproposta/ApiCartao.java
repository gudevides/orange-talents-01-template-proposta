package br.com.zup.proposta.novaproposta;

import br.com.zup.proposta.bloqueiocartao.BloqueiaCartaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@FeignClient(name = "apiCartao", url = "${feign.consulta.cartao.url}")
public interface ApiCartao {

    @GetMapping
    ConsultaCartaoResponse consulta(@RequestParam String idProposta);

    @PostMapping("/{id}/bloqueios")
    void bloqueio(@PathVariable String id, @Valid BloqueiaCartaoRequest request);

    @PostMapping("/{id}/avisos")
    void avisoViagem(@PathVariable String id, @Valid ApiAvisoCartaoRequest request);

    @PostMapping("/{id}/carteiras")
    void associaCarteira(@PathVariable String id, @Valid ApiCadastroCarteiraRequest request);
}
