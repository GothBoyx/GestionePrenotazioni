package it.brandonmorques.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.brandonmorques.model.Postazione;
import it.brandonmorques.model.TipoPostazione;
import it.brandonmorques.service.EdificioService;
import it.brandonmorques.service.PostazioneService;

@RestController
@RequestMapping("/postazione")
public class PostazioneController {

	@Autowired
	PostazioneService postazioneService;
	@Autowired
	EdificioService edificioService;

	@GetMapping("/getpostazione")
	public List<Postazione> maCheNeSo(@RequestParam String citta, @RequestParam TipoPostazione tipo) {
		List<Postazione> myResult = postazioneService.myFindAvailableByTypeAndCity(citta, tipo);
		return myResult;

	}

	@GetMapping("/save")
	public void save(@RequestParam String codice, String descrizione, TipoPostazione tipo, Integer max,
			String edificio) {
		postazioneService.save(new Postazione(codice, descrizione, max, tipo, edificioService.findByName(edificio)));
	}

	@GetMapping("/postazione")
	@ResponseBody
	public List<Postazione> getAll() {
		return postazioneService.getAll();

	}

	@GetMapping("/ricercaperpostazione")
	@ResponseBody
	public Postazione findByName(@RequestParam String codice) {
		return postazioneService.findByCodice(codice);

	}

	// Paginazione
	@GetMapping(value = "/mygetallpostazionipage", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Postazione>> myGetAllPostazioniPage(Pageable pageable) {
		Page<Postazione> findAll = postazioneService.myFindAllPostazioniPageable(pageable);
		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping(value = "/mygetallpostazionipagesize", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> myGetAllPostazioniPageSize(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		Page<Postazione> prenotazione = postazioneService.myFindAllPostazioniPageSize(page, size);
		Map<String, Object> myResponse = new HashMap<>();
		myResponse.put("postazioni", prenotazione);
		return myResponse;
	}

	@GetMapping(value = "/mygetallpostazionipagesizesort", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Postazione>> myGetAllUserPostazioniSizeSort(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
		List<Postazione> list = postazioneService.myFindAllPostazioniPageSizeSort(page, size, sort);
		return new ResponseEntity<List<Postazione>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(value = "/mygetallpostazionibycodice", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Postazione> myGetAllPostazioniSortByCodice() {
		return postazioneService.myFindAllPostazioniSorted();

	}
}