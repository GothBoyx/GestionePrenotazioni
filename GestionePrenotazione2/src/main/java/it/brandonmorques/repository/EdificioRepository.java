package it.brandonmorques.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.brandonmorques.model.Edificio;
@Component
public interface EdificioRepository extends JpaRepository<Edificio, Long>{
	
	@Query("SELECT c FROM Edificio c WHERE c.nome=:nome")
	public Edificio findByName(String nome);
	

}
