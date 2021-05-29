package ufpb.dsc.lab2.dtos;

public class DisciplinaDtoLikes {
	
	private Long id;
	private String nome;
	private int likes;
	
	public DisciplinaDtoLikes(Long id, String nome, int likes) {
		super();
		this.id = id;
		this.nome = nome;
		this.likes = likes;
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

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	

}
