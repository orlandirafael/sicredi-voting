package br.com.sicredi.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.voting.model.Pauta;
import br.com.sicredi.voting.repository.PautaRepository;

@Service
public class PautaService {

  @Autowired PautaRepository repository;

  public Pauta novaPauta(final Pauta pauta) {
    return repository.save(pauta);
  }
}
