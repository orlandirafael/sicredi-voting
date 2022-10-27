package br.com.sicredi.voting.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.sicredi.voting.dto.ResultadoVotacaoDTO;
import br.com.sicredi.voting.dto.RetornoValidacaoCpfDTO;
import br.com.sicredi.voting.dto.VotoDTO;
import br.com.sicredi.voting.exception.OpcaoInvalidaVotoException;
import br.com.sicredi.voting.exception.SessaoEncerradaException;
import br.com.sicredi.voting.exception.SessaoInvalidaException;
import br.com.sicredi.voting.exception.VotoAssociadoNaoAutorizadoException;
import br.com.sicredi.voting.exception.VotoRealizadoException;
import br.com.sicredi.voting.model.Sessao;
import br.com.sicredi.voting.model.Voto;
import br.com.sicredi.voting.repository.SessaoRepository;
import br.com.sicredi.voting.repository.VotoRepository;

@Service
public class VotoService {

  static final String SIM = "SIM";
  static final String NAO = "NÃO";
  static final String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";
  static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

  @Value("${uri.validacao.cpf}")
  public String uriValidacaoCpf;

  @Autowired SessaoRepository sessaoRepository;

  @Autowired VotoRepository repository;

  public VotoDTO realizarVoto(final VotoDTO votoDto)
      throws OpcaoInvalidaVotoException, RestClientException, URISyntaxException {
    final String escolha = votoDto.getEscolha().toUpperCase();
    final String cpfAssociado = votoDto.getCpf().replaceAll("\\D+", "");

    if (!escolha.equals(SIM) && !escolha.equals(NAO)) throw new OpcaoInvalidaVotoException();

    RetornoValidacaoCpfDTO validacao =
        new RestTemplate()
            .getForObject(new URI(uriValidacaoCpf + cpfAssociado), RetornoValidacaoCpfDTO.class);

    if (validacao.getStatus().equals(UNABLE_TO_VOTE)) {
      throw new VotoAssociadoNaoAutorizadoException();
    }

    final Optional<Sessao> sessaoOpt = sessaoRepository.findById(votoDto.getIdSessao());
    if (sessaoOpt.isPresent()) {
      final Sessao sessao = sessaoOpt.get();
      if (sessaoOpt.get().getHoraEncerramento().isBefore(LocalDateTime.now())) {
        throw new SessaoEncerradaException();
      }
      final Voto voto = new Voto();
      voto.setEscolha(escolha.equals(SIM));
      voto.setIdAssociado(votoDto.getIdAssociado());
      voto.setSessao(sessao);
      try {
        final Voto savedVoto = repository.save(voto);
        votoDto.setId(savedVoto.getId());
        return votoDto;
      } catch (DataIntegrityViolationException e) {
        throw new VotoRealizadoException();
      }
    } else {
      throw new SessaoInvalidaException();
    }
  }

  public ResultadoVotacaoDTO obterResultadoVotacao(final Sessao sessao) {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    final ResultadoVotacaoDTO resultado = repository.obterResultadoVotacao(sessao);
    resultado.setPauta(sessao.getPauta().getDescricao());
    resultado.setHorarioEncerramentoSessao(sessao.getHoraEncerramento().format(formatter));
    return resultado;
  }
}
