package ufpb.dsc.lab3.servicos;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufpb.dsc.lab3.dtos.UsuarioDto;
import ufpb.dsc.lab3.dtos.UsuarioLoginDto;
import ufpb.dsc.lab3.entidades.Usuario;
import ufpb.dsc.lab3.servicos.ServicoJWT;
import ufpb.dsc.lab3.repositorios.UsuarioDAO;

@Service
public class ServicosUsuario {
	@Autowired
	private UsuarioDAO usuariosDAO;
	@Autowired
	private ServicoJWT servicoJwt;


	
	public UsuarioDto adicionaUsuario(UsuarioDto usuarioDto) {
       
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setNome(usuarioDto.getNome());
        usuario.setSenha(usuarioDto.getSenha());
        usuariosDAO.save(usuario);
        return usuarioDto;
    }

	public Usuario getUsuario(String email) {
		Optional<Usuario> optUsuario = usuariosDAO.findByEmail(email);
		if (optUsuario.isEmpty())
			throw new IllegalArgumentException();// usuario nao existe
		return optUsuario.get();
	}

	public Usuario removeUsuario(String email, String authHeader) throws ServletException {
		Usuario usuario = getUsuario(email);
		if (usuarioTemPermissao(authHeader, email)) {
			usuariosDAO.delete(usuario);
			return usuario;
		}
		throw new ServletException("Usuario nao tem permissao");
	}

	private boolean usuarioTemPermissao(String authorizationHeader, String email) throws ServletException {
		String subject = servicoJwt.getSujeitoDoToken(authorizationHeader);
		Optional<Usuario> optUsuario = usuariosDAO.findByEmail(subject);
		return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email);
	}

	public boolean validaUsuarioSenha(UsuarioLoginDto usuario) {
		Optional<Usuario> optUsuario = usuariosDAO.findByEmail(usuario.getEmail());
		if (optUsuario.isPresent() && optUsuario.get().getSenha().equals(usuario.getSenha()))
			return true;
		return false;
	}
}
