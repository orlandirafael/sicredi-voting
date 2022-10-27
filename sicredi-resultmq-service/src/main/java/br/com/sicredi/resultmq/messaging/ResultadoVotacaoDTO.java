package br.com.sicredi.resultmq.messaging;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
