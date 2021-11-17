package it.brandonmorques.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.brandonmorques.eccezioni.BusinessLogicException;
import it.brandonmorques.model.Prenotazione;
import it.brandonmorques.pageablerepository.PrenotazionePageable;
import it.brandonmorques.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {
	@Value("${exception.lessthantwodays")
	private String lessThanTwoDays;

	@Autowired
	PrenotazioneRepository preRepository;
	@Autowired
	PrenotazionePageable myPageable;

	public Optional<Prenotazione> getById(long id) {
		return preRepository.findById(id);
	}

	public void save(Prenotazione p) {
		preRepository.save(p);
	}
	
	public List<Prenotazione> getAll() {
		return preRepository.findAll();
	}

	public boolean diffInDaysLessThan(int numDays, LocalDate firstDate, LocalDate secondDate) {
		LocalDate numDaysBefore = secondDate.minusDays(numDays);
		return firstDate.isAfter(numDaysBefore);
	}

	public void applicaRegoleBusiness(LocalDate data_prenotata) throws BusinessLogicException {
		if (diffInDaysLessThan(2, LocalDate.now(), data_prenotata)) {
			throw new BusinessLogicException(lessThanTwoDays);
		}

	}

	public boolean userHasOtherReservationForDay(Long idUser, LocalDate date) {
		List<Prenotazione> result = preRepository.findByUtenteAndDataUtilizzo(idUser, date);
		if (result.isEmpty()) {
			return false;
		}
		return true;
	}
		public Page<Prenotazione> myFindAllPrenotazioniPageable(Pageable pageable) {
			return myPageable.findAll(pageable);
		}
		public Page<Prenotazione> myFindAllPrenotazioniPageSize(Integer page, Integer size) {
			Pageable paging = PageRequest.of(page, size);
			Page<Prenotazione> pagedResult = myPageable.findAll(paging);
			if (pagedResult.hasContent()) {
				return pagedResult;
			} else {
				return null;
			}
		}

		// Paginazione e Ordinamento
		public List<Prenotazione> myFindAllPrenotazioniPageSizeSort(Integer page, Integer size, String sort) {
			Pageable paging = PageRequest.of(page, size, Sort.by(sort));
			Page<Prenotazione> pagedResult = myPageable.findAll(paging);
			if (pagedResult.hasContent()) {
				return pagedResult.getContent();
			} else {
				return new ArrayList<Prenotazione>();
			}
		}
			public List<Prenotazione> myFindAllPrenotazioniSorted() {
			return myPageable.findByOrderByDataPrenotataAsc();
	}

}