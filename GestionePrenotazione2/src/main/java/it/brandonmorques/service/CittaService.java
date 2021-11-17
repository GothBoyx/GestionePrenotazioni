package it.brandonmorques.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.brandonmorques.model.Citta;
import it.brandonmorques.pageablerepository.CittaPageable;
import it.brandonmorques.repository.CittaRepository;

@Service
public class CittaService {

	@Autowired
	CittaRepository cittaRepository;
	@Autowired
	CittaPageable myPageable;

	public List<Citta> getAll() {
		return cittaRepository.findAll();
	}

	public void save(Citta c) {
		cittaRepository.save(c);
	}

	public Citta findByName(String nome) {
		return cittaRepository.findByName(nome);
	}

	public Page<Citta> myFindAllCittaPageable(Pageable pageable) {
		return myPageable.findAll(pageable);
	}

	public Page<Citta> myFindAllCittaPageSize(Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Citta> pagedResult = myPageable.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult;
		} else {
			return null;
		}
	}

// Paginazione e Ordinamento
	public List<Citta> myFindAllCittaPageSizeSort(Integer page, Integer size, String sort) {
		Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		Page<Citta> pagedResult = myPageable.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Citta>();
		}
	}

	public List<Citta> myFindAllCittaSorted() {
		return myPageable.findByOrderByNomeAsc();
	}

}
