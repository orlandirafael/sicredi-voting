package br.com.sicredi.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.voting.model.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {}
