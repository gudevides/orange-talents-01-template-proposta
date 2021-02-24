package br.com.zup.proposta.cadastrocarteiras;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
}
