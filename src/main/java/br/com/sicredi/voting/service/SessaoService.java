package br.com.sicredi.voting.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.sicredi.voting.dto.ResultadoVotacaoDTO;
import br.com.sicredi.voting.model.Sessao;
import br.com.sicredi.voting.repository.SessaoRepository;

@Service
@EnableScheduling
public class SessaoService {

  @Autowired SessaoRepository repository;

  @Autowired VotoService votoService;

  @Scheduled(fixedDelay = 60000)
  public void analisaSessoes() {
    final List<Sessao> sessoes = repository.recuperaSessoesEncerradas();

    if (sessoes != null && sessoes.size() > 0) {
      sessoes.forEach(
          sessao -> {
            sessao.setEncerrada(true);
            repository.save(sessao);
            final ResultadoVotacaoDTO resultadoVotacao = votoService.obterResultadoVotacao(sessao);
            System.out.println(resultadoVotacao.toString());
          });
    }
  }

  public Sessao abrirSessao(final Sessao sessao) {
    sessao.setHoraEncerramento(LocalDateTime.now().plusMinutes(sessao.getTempoDuracao()));
    return repository.save(sessao);
  }
}
