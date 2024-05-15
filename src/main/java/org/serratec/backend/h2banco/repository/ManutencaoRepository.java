package org.serratec.backend.h2banco.repository;

import java.util.List;

import org.serratec.backend.h2banco.model.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

	List<Manutencao> findByObs(String obs);
	
	@Query("select m from Manutencao m where m.obs = :observacao")
	List<Manutencao> buscarPorObservacao(String observacao);
	
}
