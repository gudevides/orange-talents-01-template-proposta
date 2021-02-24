package br.com.zup.proposta.cadastrabiometria;

import br.com.zup.proposta.novaproposta.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

}
