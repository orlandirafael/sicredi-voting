package br.com.sicredi.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sicredi.voting.dto.ResultadoVotacaoDTO;
import br.com.sicredi.voting.model.Sessao;
import br.com.sicredi.voting.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
  @Query(
      value =
          "SELECT new br.com.sicredi.voting.dto.ResultadoVotacaoDTO("
              + "SUM(CASE WHEN escolha = TRUE THEN 1 ELSE 0 END), "
              + "SUM(CASE WHEN escolha = FALSE THEN 1 ELSE 0 END)) "
              + "FROM Voto voto "
              + "WHERE sessao = :sessao ")
  public ResultadoVotacaoDTO obterResultadoVotacao(final Sessao sessao);
}
