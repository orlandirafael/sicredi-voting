package br.com.sicredi.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.voting.dto.PautaDTO;
import br.com.sicredi.voting.model.Pauta;
import br.com.sicredi.voting.repository.PautaRepository;

@Service
public class PautaService {

  @Autowired PautaRepository repository;

  public PautaDTO novaPauta(final PautaDTO pautaDto) {
    Pauta pauta = new Pauta();
    pauta.setTitulo(pautaDto.getTitulo());
    pauta.setDescricao(pautaDto.getDescricao());
    pauta = repository.save(pauta);
    pautaDto.setId(pauta.getId());
    return pautaDto;
  }
}
