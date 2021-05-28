package ufpb.dsc.lab1.comparator;

import java.util.Comparator;

import ufpb.dsc.lab1.entidades.Disciplina;



public class DisciplinaComparator implements Comparator<Disciplina>{

	@Override
	public int compare(Disciplina disciplina1, Disciplina disciplina2) {
        return disciplina1.getNota().compareTo(disciplina2.getNota());
    }
	
	

}