package br.com.zup.orange_talents.frederico_neres.loteria.dto;

import java.time.OffsetDateTime;

public class PessoaNumerosApostaOutputDTO {

    private Long id;
    private String numerosAposta;
    private Long pessoaId;
    private String pessoaNome;
    private OffsetDateTime createAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumerosAposta() {
        return numerosAposta;
    }

    public void setNumerosAposta(String numerosAposta) {
        this.numerosAposta = numerosAposta;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getPessoaNome() {
        return pessoaNome;
    }

    public void setPessoaNome(String pessoaNome) {
        this.pessoaNome = pessoaNome;
    }

    public void setCreateAt(OffsetDateTime createAt) {
        this.createAt = createAt;
    }

    public OffsetDateTime getCreateAt() {
        return createAt;
    }
}
