package br.com.zup.proposta.novaproposta.criacartao;

import br.com.zup.proposta.apicompartilhada.ApiCartao;
import br.com.zup.proposta.novaproposta.Proposta;
import br.com.zup.proposta.novaproposta.PropostaRepository;
import br.com.zup.proposta.novaproposta.StatusEnum;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultaCartaoTarefa {

    private ApiCartao apiCartao;

    private PropostaRepository propostaRepository;

    public ConsultaCartaoTarefa(ApiCartao apiCartao, PropostaRepository propostaRepository) {
        this.apiCartao = apiCartao;
        this.propostaRepository = propostaRepository;
    }

    @Scheduled(fixedDelayString = "${consulta.cartao.intervalo}")
    void consultaCartao () {
        List<Proposta> listaPropostas = propostaRepository.findByStatusAndCartaoNull(StatusEnum.ELEGIVEL);
        for (Proposta proposta : listaPropostas) {
            try {
                ConsultaCartaoResponse cartaoResponse = apiCartao.consulta(proposta.getId().toString());
                if (cartaoResponse.getId() != null) {
                    proposta.atualizaCartao(cartaoResponse);
                    propostaRepository.save(proposta);
                }

            } catch (FeignException.InternalServerError e) {
                System.out.println("Não existe cartão para a proposta" + proposta.getId());
            }
        }
    }
}
