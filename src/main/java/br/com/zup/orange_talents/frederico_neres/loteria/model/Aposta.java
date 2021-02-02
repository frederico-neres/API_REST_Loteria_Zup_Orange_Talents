package br.com.zup.orange_talents.frederico_neres.loteria.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Entity
public class Aposta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String numerosAposta;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    @JsonManagedReference
    private Pessoa pessoa;

    private void setNumerosAposta(String numerosAposta) {
        this.numerosAposta = numerosAposta;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public String getNumerosAposta() {
        return numerosAposta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void gerarNumerosAposta() {
        List<Integer> cartelaDeNumeros = IntStream.range(1, 61).boxed().collect(Collectors.toList());
        Collections.shuffle(cartelaDeNumeros);
        List<Integer> numerosSorteados = cartelaDeNumeros.subList(0, 6);
        Collections.sort(numerosSorteados);
        this.setNumerosAposta(
                numerosSorteados.stream().map(numero->numero.toString()).collect(Collectors.joining(", "))
        );
    }


}
