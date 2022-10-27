package br.com.sicredi.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VotingApplication {

  public static void main(String[] args) {
    SpringApplication.run(VotingApplication.class, args);
  }
}
