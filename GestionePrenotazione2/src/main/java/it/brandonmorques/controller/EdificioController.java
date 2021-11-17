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

import it.brandonmorques.model.Edificio;
import it.brandonmorques.service.CittaService;
import it.brandonmorques.service.EdificioService;

@RestController
@RequestMapping("/edificiocontroller")
public class EdificioController {

	@Autowired
	EdificioService edificioService;
	@Autowired
	CittaService cittaService;

	@GetMapping("/save")
	public void save(@RequestParam String nome, String indirizzo, String citta, String codice) {
		edificioService.save(new Edificio(nome, indirizzo, cittaService.findByName(citta), codice));
	}

	@GetMapping("/listaedifici")
	@ResponseBody
	public List<Edificio> getAll() {
		return edificioService.getAll();

	}

	@GetMapping("/ricercapernome")
	@ResponseBody
	public Edificio findByName(@RequestParam String nome) {
		return edificioService.findByName(nome);

	}

	// Paginazione
	@GetMapping(value = "/mygetalledificipage", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Edificio>> myGetAllEdificiPage(Pageable pageable) {
		Page<Edificio> findAll = edificioService.myFindAllEdificiPageable(pageable);
		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping(value = "/mygetalledificipagesize", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> myGetAllEdificiPageSize(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		Page<Edificio> prenotazione = edificioService.myFindAllEdificiPageSize(page, size);
		Map<String, Object> myResponse = new HashMap<>();
		myResponse.put("edificio", prenotazione);
		return myResponse;
	}

	@GetMapping(value = "/mygetalledificipagesizesort", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Edificio>> myGetAllEdificiPageSizeSort(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
		List<Edificio> list = edificioService.myFindAllEdificiPageSizeSort(page, size, sort);
		return new ResponseEntity<List<Edificio>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(value = "/mygetalledificibynome", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Edificio> myGetAllEdificiSortByCitta() {
		return edificioService.myFindAllEdificiSorted();
	}

	@GetMapping(value = "/attivaedificio")
	public String attivaEdificio(@RequestParam String edificio, String codice) {
		Edificio e = edificioService.findByName(edificio);
		if (e.getCodice().equals(codice)) {
			e.setActive(true);
			return "Edificio " + e.getNome() + " attivato";
		}
		return "Impossibile attivare l'edificio " + e.getNome();
	}
}