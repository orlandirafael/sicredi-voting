package br.com.sicredi.voting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sicredi.voting.model.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

  @Query(value = "FROM Sessao WHERE encerrada = FALSE AND horaEncerramento < CURRENT_TIMESTAMP")
  public List<Sessao> recuperaSessoesEncerradas();
}
