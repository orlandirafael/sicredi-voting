package br.com.sicredi.voting;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClientException;

import br.com.sicredi.voting.dto.PautaDTO;
import br.com.sicredi.voting.dto.SessaoDTO;
import br.com.sicredi.voting.dto.VotoDTO;
import br.com.sicredi.voting.exception.OpcaoInvalidaVotoException;
import br.com.sicredi.voting.service.PautaService;
import br.com.sicredi.voting.service.SessaoService;
import br.com.sicredi.voting.service.VotoService;

@SpringBootTest
class VotingApplicationTests {

  @Autowired PautaService pautaService;
  @Autowired SessaoService sessaoService;
  @Autowired VotoService votoService;

  @Test
  void criaNovaPauta() {
    final PautaDTO pautaDto = new PautaDTO();
    pautaDto.setTitulo("Pauta de teste");
    pautaDto.setDescricao("Pauta para definição dos testes do sistema de votação");
    PautaDTO retorno = pautaService.novaPauta(pautaDto);
    assertNotNull(retorno);
    assertNotNull(retorno.getId());
  }

  @Test
  void abrirSessao() {
    PautaDTO pautaDto = new PautaDTO();
    pautaDto.setTitulo("Pauta de teste");
    pautaDto.setDescricao("Pauta para definição dos testes do sistema de votação");
    pautaDto = pautaService.novaPauta(pautaDto);

    final SessaoDTO sessaoDto = new SessaoDTO();
    sessaoDto.setIdPauta(pautaDto.getId());
    SessaoDTO retorno = sessaoService.abrirSessao(sessaoDto);
    assertNotNull(retorno);
    assertNotNull(retorno.getId());
  }

  @Test
  void realizarVoto() {
    PautaDTO pautaDto = new PautaDTO();
    pautaDto.setTitulo("Pauta de teste");
    pautaDto.setDescricao("Pauta para definição dos testes do sistema de votação");
    pautaDto = pautaService.novaPauta(pautaDto);

    SessaoDTO sessaoDto = new SessaoDTO();
    sessaoDto.setIdPauta(pautaDto.getId());
    sessaoDto = sessaoService.abrirSessao(sessaoDto);

    final VotoDTO votoDto = new VotoDTO();
    votoDto.setEscolha("Sim");
    votoDto.setCpf("04671050064");
    votoDto.setIdAssociado(1l);
    votoDto.setIdSessao(sessaoDto.getId());
    VotoDTO retorno;
    try {
      retorno = votoService.realizarVoto(votoDto);
      assertNotNull(retorno);
      assertNotNull(retorno.getId());
    } catch (RestClientException | OpcaoInvalidaVotoException | URISyntaxException e) {
      e.printStackTrace();
    }
  }
}
