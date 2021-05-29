package ufpb.dsc.lab2.dtos;

import java.util.List;

import ufpb.dsc.lab2.entidades.Comentario;

public class DisciplinaDtoComentario {
	
	private Long id;
	private String nome;
	private List<Comentario> comentarios;
	
	public DisciplinaDtoComentario(Long id, String nome, List<Comentario> comentarios) {
		super();
		this.id = id;
		this.nome = nome;
		this.comentarios = comentarios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	

}
