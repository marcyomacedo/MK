package ufpb.dsc.lab2.servicos;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;

import ufpb.dsc.lab2.dtos.ComentarioDto;
import ufpb.dsc.lab2.dtos.DisciplinaDto;
import ufpb.dsc.lab2.dtos.DisciplinaDtoComentario;
import ufpb.dsc.lab2.entidades.Comentario;
import ufpb.dsc.lab2.entidades.Disciplina;
import ufpb.dsc.lab2.repositorios.ComentarioDAO;
import ufpb.dsc.lab2.repositorios.DisciplinaDAO;
import ufpb.dsc.lab2.dtos.DisciplinaDtoLikes;
import ufpb.dsc.lab2.dtos.DisciplinaDtoNota;
import ufpb.dsc.lab2.dtos.NotaDto;

@Service
public class ServicosDisciplina {
	
	private DisciplinaDAO repositorioDeDisciplinas;
	private ComentarioDAO repositorioDeComentarios;
	
	public ServicosDisciplina(DisciplinaDAO repositorioDeDisciplinas, ComentarioDAO repositorioDeComentarios) {
		super();
		this.repositorioDeDisciplinas = repositorioDeDisciplinas;
		this.repositorioDeComentarios = repositorioDeComentarios;
	}
	
	public List<DisciplinaDto> listarDisciplinas(){
		List<Disciplina> disciplinas = repositorioDeDisciplinas.findAll();
		List<DisciplinaDto> disciplinasDto = new ArrayList<DisciplinaDto>();
		for(Disciplina disciplina : disciplinas) {
			
			DisciplinaDto disciplinaA = new DisciplinaDto(null, null);
			disciplinaA.setId(disciplina.getId());
			disciplinaA.setNome(disciplina.getNome());
			
			disciplinasDto.add(disciplinaA);
			
		}
		
		return disciplinasDto;
		
	}
	
	public Disciplina listarDisciplinaPorId(Long id) {
		
		if (repositorioDeDisciplinas.findById(id).isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		return repositorioDeDisciplinas.getById(id);
	}
	
	public DisciplinaDtoLikes adicionaLikeDisciplina(Long id) {
		
		if (repositorioDeDisciplinas.findById(id).isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		
		Disciplina disciplina = repositorioDeDisciplinas.getById(id);
		disciplina.setLike();
		repositorioDeDisciplinas.save(disciplina);
		DisciplinaDtoLikes disciplinaL = new DisciplinaDtoLikes(disciplina.getId(),
				disciplina.getNome(), disciplina.getLike());
		
		return disciplinaL;
		
		
		
	}
	
	public DisciplinaDtoNota adicionaNotaDisciplina(Long id, NotaDto nota1){
		double nota = nota1.getNota();
		if(repositorioDeDisciplinas.findById(id).isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);			
		}
		
		Disciplina disciplina = repositorioDeDisciplinas.getById(id);
		
		if(disciplina.getNota() == 0) {
			disciplina.setNota(nota);
			repositorioDeDisciplinas.save(disciplina);
			DisciplinaDtoNota disciplinaN = new DisciplinaDtoNota(disciplina.getId(),
					disciplina.getNome(),disciplina.getNota());
			return disciplinaN;
		}
		
		double notaA = disciplina.getNota();
		double notaMedia = nota + notaA / 2;
		
		disciplina.setNota(notaMedia);
		repositorioDeDisciplinas.save(disciplina);
		
		DisciplinaDtoNota disciplinaN = new DisciplinaDtoNota(disciplina.getId(),
				disciplina.getNome(),disciplina.getNota());
		return disciplinaN;		
		
	}
	
	public DisciplinaDtoComentario adicionaComentarioDisciplina(Long id, ComentarioDto comentarioDto) {

		if(repositorioDeDisciplinas.findById(id).isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);			
		}
		repositorioDeComentarios.save(comentario);
		Disciplina disciplina = repositorioDeDisciplinas.getById(id);
		DisciplinaDtoComentario disciplinaC = new DisciplinaDtoComentario(disciplina.getId(),
				disciplina.getNome(), repositorioDeComentarios.findByDisciplina(disciplina));
		
		return disciplinaC;
	}
	
	public List<Disciplina> rankingPorNota(){
		List<Disciplina> disciplinas = repositorioDeDisciplinas.findByOrderByNotaDesc();
		return disciplinas;
	}
	
	public List<Disciplina> rankingPorLikes(){
		List<Disciplina> disciplinas = repositorioDeDisciplinas.findByOrderByLikesDesc();
		return disciplinas;
	}
	

	

}
