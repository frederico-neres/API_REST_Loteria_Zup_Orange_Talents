package br.com.zup.orange_talents.frederico_neres.loteria.validators;

import br.com.zup.orange_talents.frederico_neres.loteria.dto.PessoaSalvarInputDTO;
import br.com.zup.orange_talents.frederico_neres.loteria.model.Pessoa;
import br.com.zup.orange_talents.frederico_neres.loteria.repository.PessoaRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SemEmailDuplicadoValidator implements Validator {
    private  PessoaRepository pessoaRepository;

    public SemEmailDuplicadoValidator(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PessoaSalvarInputDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PessoaSalvarInputDTO pessoaInputDTO = (PessoaSalvarInputDTO) target;
        Pessoa pessoa = pessoaRepository.findByEmail(pessoaInputDTO.getEmail()).orElse(null);

        if(pessoa != null) {
            errors.rejectValue("email", null, "Já existe uma pessoa cadastrada com esse e-mail");
        }
    }
}
