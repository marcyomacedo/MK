package ufpb.dsc.lab3.dtos;


public class UsuarioDto {
	
	
	private String email;	
	private String nome;
	private String senha;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public UsuarioDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UsuarioDto(String email, String nome, String senha) {
		super();
		this.email = email;
		this.nome = nome;
		this.senha = senha;
	}
	
	
	
	


}

