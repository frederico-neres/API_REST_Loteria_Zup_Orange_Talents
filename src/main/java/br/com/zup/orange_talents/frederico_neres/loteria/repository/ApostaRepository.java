package br.com.zup.orange_talents.frederico_neres.loteria.repository;

import br.com.zup.orange_talents.frederico_neres.loteria.model.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Long> {
}
