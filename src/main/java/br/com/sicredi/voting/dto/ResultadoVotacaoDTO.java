package br.com.sicredi.voting.dto;

import lombok.Data;

@Data
public class ResultadoVotacaoDTO {

  private String pauta;

  private String horarioEncerramentoSessao;

  private Long votosSim = 0l;

  private Long votosNao = 0l;

  public ResultadoVotacaoDTO(Long votosSim, Long votosNao) {
    this.votosSim = votosSim;
    this.votosNao = votosNao;
  }
}
