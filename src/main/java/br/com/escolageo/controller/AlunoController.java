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
import br.com.escolageo.repository.AlunoRepository;
import br.com.escolageo.service.GeolocalizacaoService;

@Controller
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private GeolocalizacaoService geolocalizacaoService;

	@GetMapping("/aluno/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("aluno", new Aluno());
		return "aluno/cadastrar";
	}

	@GetMapping("/aluno/listar")
	public String listar(Model model) {
		List<Aluno> alunos = alunoRepository.obterTodosAlunos();
		model.addAttribute("alunos", alunos);
		return "aluno/listar";
	}

	@GetMapping("/aluno/visualizar/{id}")
	public String visualizar(@PathVariable String id, Model model) {
		Aluno aluno = alunoRepository.obterAlunoPor(id);
		model.addAttribute("aluno", aluno);
		return "aluno/visualizar";
	}

	@GetMapping("/aluno/pesquisarnome")
	public String pesquisarNome() {
		return "aluno/pesquisarnome";
	}

	@GetMapping("/aluno/pesquisar")
	public String pesquisar(@RequestParam("nome") String nome, Model model) {
		List<Aluno> alunos = alunoRepository.pesquisarPor(nome);
		model.addAttribute("alunos", alunos);
		return "aluno/pesquisarnome";
	}

	@PostMapping("/aluno/salvar")
	public String salvar(@ModelAttribute Aluno aluno) {
		try {
			List<Double> latELong = geolocalizacaoService.obterLatELongPor(aluno.getContato());
			aluno.getContato().setCoordinates(latELong);
			alunoRepository.salvar(aluno);
		} catch (Exception e) {
			System.out.println("Endereco nao localizado");
			e.printStackTrace();
		}

		System.out.println(aluno);
		return "redirect:/";
	}
}
