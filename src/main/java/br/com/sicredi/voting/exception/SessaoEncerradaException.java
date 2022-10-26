package br.com.sicredi.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class SessaoEncerradaException extends RuntimeException {

  private static final long serialVersionUID = -1168004386571672316L;

  public SessaoEncerradaException() {
    super();
  }

  public SessaoEncerradaException(String message) {
    super(message);
  }
}
