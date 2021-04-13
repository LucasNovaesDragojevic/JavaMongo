package br.com.escolageo.model;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Aluno {

	private ObjectId id;
	private String nome;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	private Curso curso;
	private List<Nota> notas;
	private List<Habilidade> habilidades;
	
	public Aluno criarId() {
		setId(new ObjectId());
		return this;
	}
}
