package ufpb.dsc.lab2.dtos;

public class DisciplinaDtoNota {
	
	private Long id;
	private String nome;
	private double nota;
	
	public DisciplinaDtoNota(Long id, String nome, double nota) {
		super();
		this.id = id;
		this.nome = nome;
		this.nota = nota;
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

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}
	

}
