package br.com.sicredi.voting.dto;

import lombok.Data;

@Data
public class SessaoDTO {

  private Long id;

  private Long idPauta;

  private Integer tempoDuracao = 1;
}
