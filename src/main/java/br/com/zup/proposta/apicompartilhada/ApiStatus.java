package br.com.zup.proposta.apicompartilhada;

import br.com.zup.proposta.novaproposta.Proposta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "apiStatus",url = "${feign.nova.proposta.url}")
public interface ApiStatus {

    @PostMapping()
    void consulta(@RequestBody ConsultaStatusRequest consultaStatusRequest);

    class ConsultaStatusRequest {

        private String documento;
        private String nome;
        private Long idProposta;

        public ConsultaStatusRequest(Proposta proposta) {
            this.documento = proposta.getDocumento();
            this.nome = proposta.getNome();
            this.idProposta = proposta.getId();
        }

        public String getDocumento() {
            return documento;
        }

        public String getNome() {
            return nome;
        }

        public Long getIdProposta() {
            return idProposta;
        }
    }

}
