package br.com.zup.orange_talents.frederico_neres.loteria.dto;

import br.com.zup.orange_talents.frederico_neres.loteria.model.Pessoa;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PessoaSalvarInputDTO {
    @NotBlank
    private String nome;
    @NotBlank
    @CPF
    private String cpf;
    @NotBlank
    @Email
    private String email;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public Pessoa criarPessoa() {
        Pessoa pessoa = new Pessoa(nome, cpf, email);
        return pessoa;
    }
}
