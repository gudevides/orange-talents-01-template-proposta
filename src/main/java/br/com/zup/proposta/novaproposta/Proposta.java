package br.com.zup.proposta.novaproposta;

import br.com.zup.proposta.config.criptografia.CriptografadorConverter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Column(unique = true)
    @Convert(converter = CriptografadorConverter.class)
    private String documento;
    @NotNull @Email
    private String email;
    @NotNull
    private String nome;
    @NotNull @Embedded @Valid
    private Endereco endereco;
    @NotNull
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @OneToOne(mappedBy = "proposta", cascade = CascadeType.ALL)
    private Cartao cartao;

    public Proposta(@NotBlank String documento, @NotBlank @Email String email,
                    @NotBlank String nome, @NotNull @Valid Endereco endereco,
                    @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    @Deprecated
    public Proposta(){}

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void atualizaStatus(StatusEnum resultadoSolicitacao) {
        this.status = resultadoSolicitacao;
    }

    public void atualizaCartao(ConsultaCartaoResponse cartaoResponse) {
        this.cartao = new Cartao(cartaoResponse, this);
    }
}
