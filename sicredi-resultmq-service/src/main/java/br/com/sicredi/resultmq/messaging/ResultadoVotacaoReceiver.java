package br.com.sicredi.resultmq.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ResultadoVotacaoReceiver {

  @RabbitListener(queues = {"${queue.resultado.votacao}"})
  public void receive(@Payload ResultadoVotacaoDTO resultadoVotacao) {
    log.info("Resultado da votação da sessão para decisão da pauta " + resultadoVotacao.getPauta());
    log.info("Total de votos SIM: " + resultadoVotacao.getVotosSim());
    log.info("Total de votos NÃO: " + resultadoVotacao.getVotosNao());
  }
}
