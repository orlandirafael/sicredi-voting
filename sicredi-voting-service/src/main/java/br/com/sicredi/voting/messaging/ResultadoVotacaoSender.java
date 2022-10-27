package br.com.sicredi.voting.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sicredi.voting.dto.ResultadoVotacaoDTO;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultadoVotacaoSender {

  @Autowired RabbitTemplate rabbitTemplate;

  @Autowired private Queue resultadoVotacaoQueue;

  public void send(ResultadoVotacaoDTO resultadoVotacaoDTO) {
    this.rabbitTemplate.convertAndSend(this.resultadoVotacaoQueue.getName(), resultadoVotacaoDTO);
  }
}
