package br.com.zup.proposta.cadastrocarteiras;

import br.com.zup.proposta.novaproposta.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Enumerated(EnumType.STRING)
    private CarteiraEnum carteira;

    @NotNull
    private String email;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira(){}

    public Carteira(String email, CarteiraEnum carteira, Cartao cartao) {
        this.email = email;
        this.carteira = carteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carteira carteira1 = (Carteira) o;
        return Objects.equals(id, carteira1.id) && carteira == carteira1.carteira && Objects.equals(email, carteira1.email) && Objects.equals(cartao, carteira1.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carteira, email, cartao);
    }
}
