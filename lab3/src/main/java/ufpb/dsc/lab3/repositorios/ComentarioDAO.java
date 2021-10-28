package ufpb.dsc.lab3.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufpb.dsc.lab3.entidades.Comentario;
import ufpb.dsc.lab3.entidades.Disciplina;
@Repository
public interface ComentarioDAO extends JpaRepository<Comentario, Long>{
	
	List<Comentario> findByDisciplina(Disciplina disciplina);
		
	

}
