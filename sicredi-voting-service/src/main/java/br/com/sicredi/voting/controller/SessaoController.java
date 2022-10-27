package br.com.sicredi.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.voting.dto.SessaoDTO;
import br.com.sicredi.voting.service.SessaoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/sessao/v1")
public class SessaoController {

  @Autowired SessaoService service;

  @PostMapping
  public SessaoDTO abrirSessao(@RequestBody final SessaoDTO sessaoDto) {
    final SessaoDTO sessaoCriada = service.abrirSessao(sessaoDto);
    log.info("Sessao aberta com id {}", sessaoCriada.getId());
    return sessaoCriada;
  }
}
