package br.com.sicredi.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.voting.model.Sessao;
import br.com.sicredi.voting.service.SessaoService;

@RestController
@RequestMapping("/sessao")
public class SessaoController {

  @Autowired SessaoService service;

  @PostMapping("/v1")
  public Sessao abrirSessao(@RequestBody final Sessao sessao) {
    return service.abrirSessao(sessao);
  }
}
