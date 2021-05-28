package ufpb.dsc.lab1.servicos;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ufpb.dsc.lab1.comparator.DisciplinaComparator;
import ufpb.dsc.lab1.dtos.DisciplinaDTO;
import ufpb.dsc.lab1.entidades.Disciplina;
import ufpb.dsc.lab1.repositorios.RepositorioDeDisciplinas;

@Service
public class ServicosDisciplina {
	
	private RepositorioDeDisciplinas repositorioDeDisciplinas;

	public ServicosDisciplina() {
		super();
		this.repositorioDeDisciplinas = new RepositorioDeDisciplinas();
	}

	public Disciplina adicionaDisciplina(DisciplinaDTO disciplina) {
		
		return repositorioDeDisciplinas.adicionaDisciplina(disciplina);
		
	}

	public Disciplina getDisciplina(int nome) {
		return repositorioDeDisciplinas.getDisciplina(nome);
	}


	public Disciplina getDisciplinaId(Integer idDisciplina) {
		if (!repositorioDeDisciplinas.existeDisciplina(idDisciplina)) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}
		return repositorioDeDisciplinas.getDisciplina(idDisciplina);
	}


	public Disciplina deletaDisciplina(int id) {
		repositorioDeDisciplinas.removeDisciplina(id);
		
		return repositorioDeDisciplinas.getDisciplina(id);
	}
	
	
	public List<Disciplina> getDisciplinas() {
		return repositorioDeDisciplinas.getDisciplinas();
	}

	public List<Disciplina> ranquearDisciplinas() {
		List<Disciplina> disciplinas = repositorioDeDisciplinas.getDisciplinas();
		Collections.sort(disciplinas, Collections.reverseOrder(new DisciplinaComparator()));
		return disciplinas;
		
		
	}

	public Disciplina atualizarNota(int id, DisciplinaDTO disciplina) {
		if (!repositorioDeDisciplinas.existeDisciplina(id)) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}
		return repositorioDeDisciplinas.atualizaNota(id, disciplina.getNota());
	}
		
	


}
