package br.com.sicredi.voting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class VotoRealizadoException extends RuntimeException {

  private static final long serialVersionUID = 9017619699114787779L;

  public VotoRealizadoException() {
    super();
  }

  public VotoRealizadoException(String message) {
    super(message);
  }
}
