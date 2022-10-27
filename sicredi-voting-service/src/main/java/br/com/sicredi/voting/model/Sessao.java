package br.com.sicredi.voting.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table
public class Sessao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull @ManyToOne private Pauta pauta;

  @OneToMany(mappedBy = "sessao")
  private List<Voto> votos;

  @NotNull private LocalDateTime horaEncerramento;

  private boolean encerrada;
}
