package it.brandonmorques.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.brandonmorques.model.Postazione;
import it.brandonmorques.model.TipoPostazione;


@Component
public interface PostazioneRepository extends JpaRepository<Postazione, Long>{

	@Query("SELECT post FROM Postazione post WHERE post.edificio.citta.nome=:citta AND post.tipo=:tipo ")
	public List<Postazione> findAvailableByTypeAndCity(String citta, TipoPostazione tipo);


	@Query("SELECT c FROM Postazione c WHERE c.codice=:codice")
	public Postazione findByCodice(String codice);
}


