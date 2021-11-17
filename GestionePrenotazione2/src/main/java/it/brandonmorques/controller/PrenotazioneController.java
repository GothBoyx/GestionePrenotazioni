package it.brandonmorques.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.brandonmorques.eccezioni.BusinessLogicException;
import it.brandonmorques.model.Prenotazione;
import it.brandonmorques.service.PostazioneService;
import it.brandonmorques.service.PrenotazioneService;
import it.brandonmorques.service.UserService;

@RestController
@RequestMapping("/prenotazionecontroller")
public class PrenotazioneController {

	@Autowired
	PrenotazioneService ps;
	@Autowired
	UserService us;
	@Autowired
	PostazioneService pst;

	@Value("${lingua.ita}")
	private String rulesIta;

	@Value("${lingua.eng}")
	private String rulesEng;

	@GetMapping("/mygetlanguage")
	public String myGetLanguage(@RequestParam String lingua) {
		if (lingua.equalsIgnoreCase("ita")) {
			return rulesIta;
		}
		if (lingua.equalsIgnoreCase("eng")) {
			return rulesEng;
		}
		return "Lingua non supportata";
	}

	@GetMapping("/creaprenotazione")
	@ResponseBody
	public String prenota(@RequestParam Long idUser, @RequestParam Long idPostazione,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data_prenotata)
			throws BusinessLogicException {

		ps.applicaRegoleBusiness(data_prenotata);
		if (ps.userHasOtherReservationForDay(idUser, data_prenotata)) {
			throw new BusinessLogicException("L'utente ha già prenotato per quel giorno");
		} else {
			ps.save(new Prenotazione(us.getById(idUser), pst.getById(idPostazione), data_prenotata));
		}
		return "successo";
		
		//		return Redirect("/creaprenotazione/{idUser}");
	}

	@GetMapping("/creaprenotazione/{idUser}")
	public ModelAndView myOrder(@PathVariable Long idUser) {
		Optional<Prenotazione> pre = ps.getById(idUser);
		ModelAndView myView = new ModelAndView("successo");
		myView.addObject("pre", pre);
		return myView;
	}

	@GetMapping("/listaprenotazioni")
	@ResponseBody
	public List<Prenotazione> getAll() {
		return ps.getAll();
	}

	@GetMapping("/prenotazioni")
	@ResponseBody
	public Optional<Prenotazione> getById(@RequestParam long id) {
		return ps.getById(id);
	}
	// Paginazione
	@GetMapping(value = "/mygetallprenotazionipage", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Prenotazione>> myGetAllUsersPage(Pageable pageable) {
		Page<Prenotazione> findAll = ps.myFindAllPrenotazioniPageable(pageable);
		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping(value = "/mygetallprenotazionipagesize", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> myGetAllUsersPageSize(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		Page<Prenotazione> prenotazione = ps.myFindAllPrenotazioniPageSize(page, size);
		Map<String, Object> myResponse = new HashMap<>();
		myResponse.put("prenotazione", prenotazione);
		return myResponse;
	}

	@GetMapping(value = "/mygetallprenotazionipagesizesort", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Prenotazione>> myGetAllUserPageSizeSort(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
		List<Prenotazione> list = ps.myFindAllPrenotazioniPageSizeSort(page, size, sort);
		return new ResponseEntity<List<Prenotazione>>(list, new HttpHeaders(), HttpStatus.OK);
}
	@GetMapping(value = "/mygetallprenotazionibydata", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Prenotazione> myGetAllPrenotazioniSortByDataPrenotata() {
		return ps.myFindAllPrenotazioniSorted();
}
}