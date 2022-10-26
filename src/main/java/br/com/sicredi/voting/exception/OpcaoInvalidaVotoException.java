package br.com.sicredi.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OpcaoInvalidaVotoException extends RuntimeException {

  private static final long serialVersionUID = -1443769069726647144L;

  public OpcaoInvalidaVotoException() {
    super();
  }

  public OpcaoInvalidaVotoException(String message) {
    super(message);
  }
}
