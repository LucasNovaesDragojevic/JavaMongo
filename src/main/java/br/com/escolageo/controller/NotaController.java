package br.com.escolageo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.escolageo.model.Aluno;
import br.com.escolageo.model.Nota;
import br.com.escolageo.repository.AlunoRepository;

@Controller
public class NotaController {

	@Autowired
	private AlunoRepository alunoRepository;

	@GetMapping("/nota/salvar/{id}")
	public String cadastrar(@PathVariable String id, Model model) {
		Aluno aluno = alunoRepository.obterAlunoPor(id);
		model.addAttribute("aluno", aluno);
		model.addAttribute("nota", new Nota());
		return "/nota/cadastrar";
	}

	@GetMapping("/nota/iniciarpesquisa")
	public String iniciarPesquisa() {
		return "nota/pesquisar";
	}
	
	@GetMapping("/nota/pesquisar")
	public String pesquisarPor(@RequestParam("classificacao") String classificacao, @RequestParam("notacorte") String notaCorte, Model model) {
		List<Aluno> alunos = alunoRepository.pesquisarPor(classificacao, Double.parseDouble(notaCorte));
		model.addAttribute("alunos", alunos);
		return "nota/pesquisar";
	}

	@PostMapping("/nota/salvar/{id}")
	public String salvar(@PathVariable String id, @ModelAttribute Nota nota) {
		Aluno aluno = alunoRepository.obterAlunoPor(id);
		alunoRepository.salvar(aluno.adicionar(aluno, nota));
		return "redirect:/aluno/listar";
	}

}
