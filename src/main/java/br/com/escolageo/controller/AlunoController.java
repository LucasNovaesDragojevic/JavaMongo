package br.com.escolageo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.escolageo.model.Aluno;

@Controller
@RequestMapping("alunos")
public class AlunoController {

	@GetMapping("cadastrar")
	public String cadastrar(Aluno aluno) {
		return "aluno/cadastrar";
	}
	
	@PostMapping("salvar")
	public String salvar(Aluno aluno) {
		System.out.println(aluno);
		return "redirect:/";
	}
}
