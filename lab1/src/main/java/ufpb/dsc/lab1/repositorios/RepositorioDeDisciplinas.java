package ufpb.dsc.lab1.repositorios;

import java.util.ArrayList;
import java.util.List;

import ufpb.dsc.lab1.dtos.DisciplinaDTO;
import ufpb.dsc.lab1.entidades.Disciplina;

public class RepositorioDeDisciplinas {
	
	private List<Disciplina> disciplinas;
	static private int idDisciplina = 1;
	
	
	public RepositorioDeDisciplinas() {
		super();
		disciplinas = new ArrayList<Disciplina>();
	}

	public Disciplina adicionaDisciplina(DisciplinaDTO disciplina) {
		
		Disciplina novaDisciplina = new Disciplina(idDisciplina++, disciplina.getNome(), disciplina.getNota());
		disciplinas.add(novaDisciplina);
		return novaDisciplina;
	}
	
	
	public Disciplina getDisciplina(int idDisciplina) {
		for (Disciplina disciplina : disciplinas) {
			if (disciplina.getId() == idDisciplina)
				return disciplina;
		}
		return null;
	}

	public List<Disciplina> getDisciplinas() {
			return disciplinas;
			
		}
	public Disciplina removeDisciplina(int id) {
		Disciplina disciplina = getDisciplina(id);
		disciplina.setApagada(true);
		return disciplina;
	}
	
	public boolean existeDisciplina(int idDisciplina) {
		for (Disciplina disciplina:disciplinas) {
			if (disciplina.getId()==idDisciplina)
				return true;
		}
		return false;
	}
	
	public Disciplina atualizaNota(int id, double nota) {
		Disciplina disciplina = disciplinas.get(id);
		disciplina.setNota(nota);
		return disciplina;
	}

}
