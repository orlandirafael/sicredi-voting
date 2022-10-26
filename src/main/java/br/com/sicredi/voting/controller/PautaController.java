package br.com.sicredi.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.voting.model.Pauta;
import br.com.sicredi.voting.service.PautaService;

@RestController
@RequestMapping("/pauta")
public class PautaController {

  @Autowired PautaService service;

  @PostMapping("/v1")
  public Pauta novaPauta(@RequestBody final Pauta pauta) {
    return service.novaPauta(pauta);
  }
}
