package br.com.sicredi.voting.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import br.com.sicredi.voting.dto.VotoDTO;
import br.com.sicredi.voting.exception.OpcaoInvalidaVotoException;
import br.com.sicredi.voting.service.VotoService;

@RestController
@RequestMapping("/voto")
public class VotoController {

  @Autowired VotoService service;

  @PostMapping("/v1")
  public VotoDTO realizarVoto(@RequestBody final VotoDTO voto)
      throws RestClientException, OpcaoInvalidaVotoException, URISyntaxException {
    return service.realizarVoto(voto);
  }
}
