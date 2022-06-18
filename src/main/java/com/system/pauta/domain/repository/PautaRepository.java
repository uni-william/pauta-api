package com.system.pauta.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.pauta.domain.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
