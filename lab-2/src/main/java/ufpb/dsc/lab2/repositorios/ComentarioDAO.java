package ufpb.dsc.lab2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ufpb.dsc.lab2.entidades.Comentario;
import ufpb.dsc.lab2.entidades.Disciplina;
@Repository
public interface ComentarioDAO extends JpaRepository<Comentario, Long>{
	
	List<Comentario> findByDisciplina(Disciplina disciplina);
		
	

}
