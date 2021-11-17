package it.brandonmorques.pageablerepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.brandonmorques.model.Citta;

public interface CittaPageable extends PagingAndSortingRepository <Citta, Long>{
	
	public Page<Citta> findAll(Pageable pageable);
	
	 public List<Citta> findByOrderByNomeAsc();
	 
}
