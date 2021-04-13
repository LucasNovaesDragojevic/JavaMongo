package br.com.escolageo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.escolageo.model.Aluno;
import br.com.escolageo.repository.AlunoRepository;

@Controller
@RequestMapping("alunos")
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepository;

	@GetMapping("cadastrar")
	public String cadastrar(Aluno aluno) {
		return "aluno/cadastrar";
	}
	
	@PostMapping("salvar")
	public String salvar(Aluno aluno) {
		System.out.println(aluno);
		alunoRepository.salvar(aluno);
		return "redirect:/";
	}
	
	@GetMapping("listar")
	public ModelAndView cadastrar() {
		List<Aluno> alunos = alunoRepository.listar();
		return new ModelAndView("aluno/listar", "alunos", alunos);
	}
}
