package br.com.zup.orange_talents.frederico_neres.loteria.repository;

import br.com.zup.orange_talents.frederico_neres.loteria.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByEmail(String email);
    Optional<Pessoa> findByCpf(String cpf);
    Boolean existsByIdAndApostasNumerosAposta(Long id, String numerosAposta);
}
