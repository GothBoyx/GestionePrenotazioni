package it.brandonmorques.pageablerepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.brandonmorques.model.Edificio;

public interface EdificioPageable extends PagingAndSortingRepository <Edificio, Long>{
	
	public Page<Edificio> findAll(Pageable pageable);
	
	 public List<Edificio> findByOrderByCittaAsc();
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	
	

}
