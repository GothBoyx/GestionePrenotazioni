package it.brandonmorques.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "postazione")
public class Postazione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codice;
	private String descrizione;
	private Integer numeroMassimoOccupati;
	@Enumerated(EnumType.STRING)
	private TipoPostazione tipo;
	@ManyToOne
	private Edificio edificio;

	@Override
	public String toString() {
		return String.format(
				"Postazione [id=%s, codice=%s, descrizione=%s, numeroMassimoOccupati=%s, tipo=%s, edificio=%s]", id,
				codice, descrizione, numeroMassimoOccupati, tipo, edificio);
	}

	public Postazione(String codice, String descrizione, Integer numeroMassimoOccupati, TipoPostazione tipo,
			Edificio edificio) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.numeroMassimoOccupati = numeroMassimoOccupati;
		this.tipo = tipo;
		this.edificio = edificio;
	}

}
