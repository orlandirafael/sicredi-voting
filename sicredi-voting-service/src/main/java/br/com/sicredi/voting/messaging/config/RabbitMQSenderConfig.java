package br.com.sicredi.voting.messaging.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RabbitMQSenderConfig {

  @Value("${queue.resultado.votacao}")
  String resultadoVotacaoQueue;

  @Bean
  public Queue queue() {
    return new Queue(this.resultadoVotacaoQueue, true);
  }
}
