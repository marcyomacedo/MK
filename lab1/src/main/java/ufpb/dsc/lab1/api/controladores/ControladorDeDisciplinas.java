package ufpb.dsc.lab1.api.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import ufpb.dsc.lab1.dtos.DisciplinaDTO;
import ufpb.dsc.lab1.entidades.Disciplina;
import ufpb.dsc.lab1.servicos.*;

@RestController
public class ControladorDeDisciplinas {
	
	@Autowired
	private ServicosDisciplina ServicosDisciplina;

	public ControladorDeDisciplinas(ServicosDisciplina servicosDisciplina) {
		super();
		this.ServicosDisciplina = servicosDisciplina;
	}

	@PostMapping("/v1/api/disciplinas")
	private ResponseEntity<Disciplina> adicionaDisciplina(@RequestBody DisciplinaDTO disciplina) {
		return new ResponseEntity<Disciplina>(ServicosDisciplina.adicionaDisciplina(disciplina), HttpStatus.CREATED);
	}

	@GetMapping("/v1/api/disciplinas")
	private ResponseEntity<List<Disciplina>> getDisciplinas(
			@RequestParam(value = "nome", defaultValue = "") String nome) {
		return new ResponseEntity<List<Disciplina>>(ServicosDisciplina.getDisciplinas(), HttpStatus.OK);
	}

	@GetMapping("/v1/api/disciplinas/{id}")
	private ResponseEntity<Disciplina> getDisciplinas(@PathVariable Integer id) {
		try {
			return new ResponseEntity<Disciplina>(ServicosDisciplina.getDisciplina(id), HttpStatus.OK);
		} catch (HttpClientErrorException cee) {
			return new ResponseEntity<Disciplina>(cee.getStatusCode());
		}
	}
	
	@PatchMapping("/v1/api/disciplinas/{id}/nota")
	public ResponseEntity<Disciplina> atualizarNota(@PathVariable Integer id,@RequestBody DisciplinaDTO disciplina) {
		try {
			return new ResponseEntity<Disciplina>(ServicosDisciplina.atualizarNota(id, disciplina), HttpStatus.OK);
		} catch (HttpClientErrorException hce) {
			return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
		}
	}


	@DeleteMapping("/v1/api/disciplinas/{id}")
	private ResponseEntity<Disciplina> deletaDisciplina(@PathVariable Integer id) {
		try {
			return new ResponseEntity<Disciplina>(ServicosDisciplina.deletaDisciplina(id), HttpStatus.OK);
		} catch (HttpClientErrorException cee) {
			return new ResponseEntity<Disciplina>(cee.getStatusCode());
		}
	}
	
	@GetMapping("/v1/api/disciplinas/ranking")
	public ResponseEntity<List<Disciplina>> ranquearDisciplinasNota(){
		return new ResponseEntity<List<Disciplina>>(ServicosDisciplina.ranquearDisciplinas(), HttpStatus.OK);
	}
	
	
	
	

}
