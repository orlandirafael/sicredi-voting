package br.com.sicredi.voting.dto;

import lombok.Data;

@Data
public class VotoDTO {

  private Long id;

  private Long idAssociado;

  private String cpf;

  private String escolha;

  private Long idSessao;
}
