package it.brandonmorques.pageablerepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.brandonmorques.model.User;

public interface UserPageable extends PagingAndSortingRepository <User, Long>{

	//pageable
	public Page<User> findAll(Pageable pageable);

	/* Sort */
    // Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
	public List<User> findByOrderByNomeAsc();
}
