package br.com.zup.proposta.cadastrabiometria;

import br.com.zup.proposta.novaproposta.criacartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String biometria;
    @NotNull
    private LocalDateTime dataCriacao;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Cartao cartao;

    @Deprecated
    public Biometria(){}

    public Biometria(String biometria, Cartao cartao) {
        this.biometria = biometria;
        this.dataCriacao = LocalDateTime.now();
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biometria biometria1 = (Biometria) o;
        return Objects.equals(id, biometria1.id) && Objects.equals(biometria, biometria1.biometria) && Objects.equals(dataCriacao, biometria1.dataCriacao) && Objects.equals(cartao, biometria1.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, biometria, dataCriacao, cartao);
    }

    public Long getId() {
        return this.id;
    }
}
