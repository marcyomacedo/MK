package ufpb.dsc.lab3.controladores;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ufpb.dsc.lab3.dtos.UsuarioDto;
import ufpb.dsc.lab3.entidades.Usuario;
import ufpb.dsc.lab3.servicos.ServicosUsuario;

@RestController
public class ControladorDeUsuarios {

	@Autowired
	private ServicosUsuario usuariosService;

	public ControladorDeUsuarios(ServicosUsuario usuariosService) {
		super();
		this.usuariosService = usuariosService;
	}

	@PostMapping("/v3/api/usuarios")
	public ResponseEntity<UsuarioDto> adicionaUsuario(@RequestBody UsuarioDto usuario) {
		return new ResponseEntity<UsuarioDto>(this.usuariosService.adicionaUsuario(usuario), HttpStatus.CREATED);
	}
	
	@GetMapping("/v3/api/auth/usuarios/{email}")
	public ResponseEntity<Usuario> buscarUsuario(@PathVariable String email) {
		try {
			return new ResponseEntity<Usuario>(usuariosService.getUsuario(email), HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/v3/api/auth/usuarios/{email}")
	public ResponseEntity<Usuario> removeUsuario(@PathVariable String email,
			@RequestHeader("Authorization") String header) {
		try {
			return new ResponseEntity<Usuario>(usuariosService.removeUsuario(email, header), HttpStatus.OK);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		} catch (ServletException e) {
			return new ResponseEntity<Usuario>(HttpStatus.FORBIDDEN);
		}
	}

}
