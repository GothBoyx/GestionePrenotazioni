package it.brandonmorques.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.brandonmorques.model.Edificio;
import it.brandonmorques.pageablerepository.EdificioPageable;
import it.brandonmorques.repository.EdificioRepository;

@Service
public class EdificioService {
@Autowired
EdificioRepository edificioRepository;
@Autowired
EdificioPageable myPageable;

public List<Edificio> getAll(){
	return edificioRepository.findAll();
}
public Edificio findByName(String nome) {
	return edificioRepository.findByName(nome);
}
public void save(Edificio e) {
	edificioRepository.save(e);
}
public Page<Edificio> myFindAllEdificiPageable(Pageable pageable) {
	return myPageable.findAll(pageable);
}
public Page<Edificio> myFindAllEdificiPageSize(Integer page, Integer size) {
	Pageable paging = PageRequest.of(page, size);
	Page<Edificio> pagedResult = myPageable.findAll(paging);
	if (pagedResult.hasContent()) {
		return pagedResult;
	} else {
		return null;
	}
}

// Paginazione e Ordinamento
public List<Edificio> myFindAllEdificiPageSizeSort(Integer page, Integer size, String sort) {
	Pageable paging = PageRequest.of(page, size, Sort.by(sort));
	Page<Edificio> pagedResult = myPageable.findAll(paging);
	if (pagedResult.hasContent()) {
		return pagedResult.getContent();
	} else {
		return new ArrayList<Edificio>();
	}
}
	public List<Edificio> myFindAllEdificiSorted() {
	return myPageable.findByOrderByCittaAsc();
}
}
