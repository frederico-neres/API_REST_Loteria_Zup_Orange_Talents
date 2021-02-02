package br.com.zup.orange_talents.frederico_neres.loteria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @CPF
    @Column(unique = true)
    private String cpf;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "pessoa")
    @JsonBackReference
    private List<Aposta> apostas;

    @Deprecated
    public Pessoa() {
    }

    public Pessoa(@NotBlank String nome, @NotBlank @CPF String cpf, @NotBlank @Email String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public List<Aposta> getApostas() {
        return apostas;
    }
}
