package it.brandonmorques.pageablerepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.brandonmorques.model.Prenotazione;

public interface PrenotazionePageable  extends PagingAndSortingRepository <Prenotazione, Long>{
	
	//pageable
	public Page<Prenotazione> findAll(Pageable pageable);
	
	 public List<Prenotazione> findByOrderByDataPrenotataAsc();

}
