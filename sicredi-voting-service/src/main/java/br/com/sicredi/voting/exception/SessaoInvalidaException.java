package br.com.sicredi.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SessaoInvalidaException extends RuntimeException {

  private static final long serialVersionUID = 3333700799679654459L;

  public SessaoInvalidaException() {
    super();
  }

  public SessaoInvalidaException(String message) {
    super(message);
  }
}
