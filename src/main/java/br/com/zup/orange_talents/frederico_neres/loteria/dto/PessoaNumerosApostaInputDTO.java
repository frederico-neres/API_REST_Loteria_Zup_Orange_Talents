package br.com.zup.orange_talents.frederico_neres.loteria.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PessoaNumerosApostaInputDTO {
    @NotBlank
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
