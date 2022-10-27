package br.com.sicredi.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.voting.model.Pauta;
import br.com.sicredi.voting.service.PautaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/pauta/v1")
public class PautaController {

  @Autowired PautaService service;

  @PostMapping
  public Pauta novaPauta(@RequestBody final Pauta pauta) {
    final Pauta pautaCriada = service.novaPauta(pauta);
    log.info("Pauta criada com id {}", pautaCriada.getId());
    return pautaCriada;
  }
}
