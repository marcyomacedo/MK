package ufpb.dsc.lab3.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;

import ufpb.dsc.lab3.dtos.ComentarioDto;
import ufpb.dsc.lab3.dtos.DisciplinaDto;
import ufpb.dsc.lab3.dtos.DisciplinaDtoComentario;
import ufpb.dsc.lab3.dtos.DisciplinaDtoLikes;
import ufpb.dsc.lab3.dtos.DisciplinaDtoNota;
import ufpb.dsc.lab3.dtos.NotaDto;
import ufpb.dsc.lab3.entidades.Comentario;
import ufpb.dsc.lab3.entidades.Disciplina;
import ufpb.dsc.lab3.repositorios.ComentarioDAO;
import ufpb.dsc.lab3.repositorios.DisciplinaDAO;
import ufpb.dsc.lab3.entidades.Usuario;
import ufpb.dsc.lab3.servicos.ServicoJWT;
import ufpb.dsc.lab3.repositorios.UsuarioDAO;

@Service
public class ServicosDisciplina {
	
	@Autowired
	private DisciplinaDAO repositorioDeDisciplinas;
	@Autowired
	private ComentarioDAO repositorioDeComentarios;
	@Autowired
	private ServicoJWT servicoJWT;
	@Autowired
	private UsuarioDAO usuarioDAO;
	
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
	
	public DisciplinaDtoLikes adicionaLikeDisciplina(Long id, String header) {
		
	
		String emailSujeito = servicoJWT.getSujeitoDoToken(header);
		
		if(!emailSujeito.isEmpty()) {
			
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
		
		throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		
		
		
		
	}
	
	public DisciplinaDtoNota adicionaNotaDisciplina(Long id, NotaDto nota1, String header){
		
		String emailSujeito = servicoJWT.getSujeitoDoToken(header);
		
		if(!emailSujeito.isEmpty()) {
			
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
		
		throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		
				
		
	}
	
	public DisciplinaDtoComentario adicionaComentarioDisciplina(Long id, ComentarioDto comentarioDto, String header) {

		String emailSujeito = servicoJWT.getSujeitoDoToken(header);
		
		if(!emailSujeito.isEmpty()) {
			
			if(repositorioDeDisciplinas.findById(id).isEmpty()) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);			
			}
			Comentario comentario = new Comentario();
			comentario.setTexto(comentarioDto.getTexto());
			repositorioDeComentarios.save(comentario);
			Disciplina disciplina = repositorioDeDisciplinas.getById(id);
			DisciplinaDtoComentario disciplinaC = new DisciplinaDtoComentario(disciplina.getId(),
					disciplina.getNome(), repositorioDeComentarios.findByDisciplina(disciplina));
			
			return disciplinaC;
			
		}
		
		throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		
		
		
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
