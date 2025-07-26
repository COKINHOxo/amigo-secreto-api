package com.projeto.amigo.secreto.repositories;

import com.projeto.amigo.secreto.entities.Sorteio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Long> {
}
