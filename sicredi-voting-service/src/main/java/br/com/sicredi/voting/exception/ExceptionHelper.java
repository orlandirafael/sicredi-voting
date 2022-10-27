package br.com.sicredi.voting.exception;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHelper {

  @ExceptionHandler(value = {ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException ex) {
    log.error("Constraint Violation Exception: ", ex.getMessage());
    Set<String> messages = new HashSet<>();
    messages.addAll(
        ex.getConstraintViolations()
            .stream()
            .map(
                constraintViolation -> {
                  StringBuilder builder = new StringBuilder();
                  builder.append(constraintViolation.getPropertyPath() + " ");
                  builder.append(
                      Optional.ofNullable(constraintViolation.getInvalidValue()).orElse(" "));
                  builder.append(constraintViolation.getMessage());
                  return builder.toString();
                })
            .collect(Collectors.toList()));
    return new ResponseEntity<Object>(messages, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {OpcaoInvalidaVotoException.class})
  public ResponseEntity<Object> handleOpcaoInvalidaVotoException(OpcaoInvalidaVotoException ex) {
    log.error("Opção Inválida Voto Exception: ", ex.getMessage());
    return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {SessaoEncerradaException.class})
  public ResponseEntity<Object> handleSessaoEncerradaException(SessaoEncerradaException ex) {
    log.error("Sessão Encerrada Exception: ", ex.getMessage());
    return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {SessaoInvalidaException.class})
  public ResponseEntity<Object> handleSessaoInvalidaException(SessaoInvalidaException ex) {
    log.error("Sessão Inválida Exception: ", ex.getMessage());
    return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {VotoAssociadoNaoAutorizadoException.class})
  public ResponseEntity<Object> handleVotoAssociadoNaoAutorizadoException(
      VotoAssociadoNaoAutorizadoException ex) {
    log.error("Voto Associado Não Autorizado Exception: ", ex.getMessage());
    return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {VotoRealizadoException.class})
  public ResponseEntity<Object> handleVotoRealizadoException(VotoRealizadoException ex) {
    log.error("Voto Realizado Exception: ", ex.getMessage());
    return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
