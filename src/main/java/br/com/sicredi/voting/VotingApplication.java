package br.com.sicredi.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("br.com.sicredi.voting.repository")
public class VotingApplication {

  public static void main(String[] args) {
    SpringApplication.run(VotingApplication.class, args);
  }
}
