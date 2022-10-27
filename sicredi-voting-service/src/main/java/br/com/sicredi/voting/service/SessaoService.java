package br.com.sicredi.voting.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.sicredi.voting.dto.ResultadoVotacaoDTO;
import br.com.sicredi.voting.dto.SessaoDTO;
import br.com.sicredi.voting.messaging.ResultadoVotacaoSender;
import br.com.sicredi.voting.model.Pauta;
import br.com.sicredi.voting.model.Sessao;
import br.com.sicredi.voting.repository.SessaoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@EnableScheduling
public class SessaoService {

  @Autowired SessaoRepository repository;

  @Autowired VotoService votoService;

  @Autowired ResultadoVotacaoSender sender;

  @Scheduled(fixedDelay = 30000)
  public void analisaSessoes() {
    final List<Sessao> sessoes = repository.recuperaSessoesEncerradas();

    if (sessoes != null && sessoes.size() > 0) {
      sessoes.forEach(
          sessao -> {
            sessao.setEncerrada(true);
            repository.save(sessao);
            final ResultadoVotacaoDTO resultadoVotacao = votoService.obterResultadoVotacao(sessao);
            sender.send(resultadoVotacao);
          });
    }
  }

  public SessaoDTO abrirSessao(final SessaoDTO sessaoDto) {
    final Sessao sessao = new Sessao();
    final Pauta pauta = new Pauta();
    pauta.setId(sessaoDto.getIdPauta());
    sessao.setPauta(pauta);

    LocalDateTime now = LocalDateTime.now();
    now = now.plusMinutes(sessaoDto.getTempoDuracao());
    sessao.setHoraEncerramento(now);
    final Sessao sessaoSaved = repository.save(sessao);
    sessaoDto.setId(sessaoSaved.getId());
    return sessaoDto;
  }
}
