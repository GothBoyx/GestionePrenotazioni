package it.brandonmorques.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.brandonmorques.model.Citta;
@Component
public interface CittaRepository extends JpaRepository<Citta, Long>{
	@Query("SELECT c FROM Citta c WHERE c.nome=:nome")
		public Citta findByName(String nome);
	
}
