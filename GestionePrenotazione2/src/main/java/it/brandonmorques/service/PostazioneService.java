package it.brandonmorques.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.brandonmorques.model.Postazione;
import it.brandonmorques.model.TipoPostazione;
import it.brandonmorques.pageablerepository.PostazionePageable;
import it.brandonmorques.repository.PostazioneRepository;

@Service
public class PostazioneService {

	@Autowired
	PostazioneRepository postazioneRepository;
	@Autowired
	PostazionePageable myPageable;

	public Postazione getById(long id) {
		return postazioneRepository.getById(id);
	}

	public List<Postazione> myFindAvailableByTypeAndCity(String citta, TipoPostazione tipo) {
		return postazioneRepository.findAvailableByTypeAndCity(citta, tipo);
	}

	public List<Postazione> getAll() {
		return postazioneRepository.findAll();
	}

	public Postazione findByCodice(String codice) {
		return postazioneRepository.findByCodice(codice);
	}

	public void save(Postazione e) {
		postazioneRepository.save(e);

	}

	public Page<Postazione> myFindAllPostazioniPageable(Pageable pageable) {
		return myPageable.findAll(pageable);
	}

	public Page<Postazione> myFindAllPostazioniPageSize(Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Postazione> pagedResult = myPageable.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult;
		} else {
			return null;
		}
	}

	// Paginazione e Ordinamento
	public List<Postazione> myFindAllPostazioniPageSizeSort(Integer page, Integer size, String sort) {
		Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		Page<Postazione> pagedResult = myPageable.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Postazione>();
		}
	}

	public List<Postazione> myFindAllPostazioniSorted() {
		return myPageable.findByOrderByCodiceAsc();
	}

}
