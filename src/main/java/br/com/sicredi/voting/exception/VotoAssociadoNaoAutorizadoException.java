package br.com.sicredi.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class VotoAssociadoNaoAutorizadoException extends RuntimeException {

  private static final long serialVersionUID = 3347363310696436140L;

  public VotoAssociadoNaoAutorizadoException() {
    super();
  }

  public VotoAssociadoNaoAutorizadoException(String message) {
    super(message);
  }
}
