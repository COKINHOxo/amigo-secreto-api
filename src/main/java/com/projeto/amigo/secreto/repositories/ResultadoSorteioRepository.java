package com.projeto.amigo.secreto.repositories;

import com.projeto.amigo.secreto.entities.ResultadoSorteio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultadoSorteioRepository extends JpaRepository<ResultadoSorteio, Long> {
    Optional<ResultadoSorteio> findBySorteadorIdAndSorteioId(Long sorteadorId, Long sorteioId);
    List<ResultadoSorteio> findAllBySorteioId(Long sorteioId);
}
