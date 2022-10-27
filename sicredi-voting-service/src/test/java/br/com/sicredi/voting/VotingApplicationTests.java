package br.com.sicredi.voting;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClientException;

import br.com.sicredi.voting.dto.SessaoDTO;
import br.com.sicredi.voting.dto.VotoDTO;
import br.com.sicredi.voting.exception.OpcaoInvalidaVotoException;
import br.com.sicredi.voting.model.Pauta;
import br.com.sicredi.voting.service.PautaService;
import br.com.sicredi.voting.service.SessaoService;
import br.com.sicredi.voting.service.VotoService;

@SpringBootTest
class VotingApplicationTests {

  PautaService pautaService;
  SessaoService sessaoService;
  VotoService votoService;

  @Test
  void criaNovaPauta() {
    final Pauta pauta = new Pauta();
    pauta.setDescricao("Pauta de teste");
    Pauta retorno = pautaService.novaPauta(pauta);
    assertNotNull(retorno);
    assertNotNull(retorno.getId());
  }

  @Test
  void abrirSessao() {
    final SessaoDTO sessaoDto = new SessaoDTO();
    sessaoDto.setIdPauta(1l);
    SessaoDTO retorno = sessaoService.abrirSessao(sessaoDto);
    assertNotNull(retorno);
    assertNotNull(retorno.getId());
  }

  @Test
  void realizarVoto() {
    final VotoDTO votoDto = new VotoDTO();
    votoDto.setEscolha("Sim");
    votoDto.setIdAssociado(1l);
    votoDto.setIdSessao(1l);
    VotoDTO retorno;
    try {
      retorno = votoService.realizarVoto(votoDto);
      assertNotNull(retorno);
      assertNotNull(retorno.getId());
    } catch (RestClientException
        | OpcaoInvalidaVotoException
        | URISyntaxException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
