package br.com.escolageo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Curso {

	public Curso(String nome) {
		this.nome = nome;
	}

	private String nome;
}
