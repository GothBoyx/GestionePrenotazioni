package it.brandonmorques.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "prenotazione")

public class Prenotazione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@NotNull
	private User user;
	@ManyToOne
	@NotNull
	private Postazione postazione;
	@NotNull
	LocalDate dataPrenotata;
	LocalDate dataPrenotazione;

	@Override
	public String toString() {
		return String.format("Prenotazione [id=%s, user=%s, postazione=%s, dataPrenotata=%s, dataPrenotazione=%s]", id,
				user, postazione, dataPrenotata, dataPrenotazione);
	}

	public Prenotazione(User user, Postazione postazione, LocalDate dataPrenotata) {
		this.user = user;
		this.postazione = postazione;
		this.dataPrenotata = dataPrenotata;
		this.dataPrenotazione = LocalDate.now();

	}

}
