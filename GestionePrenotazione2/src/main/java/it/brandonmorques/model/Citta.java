package it.brandonmorques.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "citta")
public class Citta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	public Citta(String nome) {
		this.nome = nome;

	}

	@Override
	public String toString() {
		return String.format("Citta [id=%s, nome=%s]", id, nome);
	}

}
