package it.brandonmorques.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import it.brandonmorques.StringAttributeConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "edificio")
public class Edificio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String indirizzo;
	@ManyToOne
	private Citta citta;
	@Convert(converter = StringAttributeConverter.class)
	@Length(max= 8, min= 8)
	private String codice;
	private boolean isActive;

	public Edificio(String nome, String indirizzo, Citta citta, String codice) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.codice=codice;
		this.setActive(false);
	}

	@Override
	public String toString() {
		return String.format("Edificio [id=%s, nome=%s, indirizzo=%s, citta=%s]", id, nome, indirizzo, citta);

	}

}
