package ufpb.dsc.lab3.servicos;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import ufpb.dsc.lab3.dtos.RespostaDeLogin;
import ufpb.dsc.lab3.entidades.Usuario;
import ufpb.dsc.lab3.dtos.UsuarioLoginDto;
import ufpb.dsc.lab3.filtros.FiltroDeJWT;

@Service
public class ServicoJWT {
	@Autowired
	private ServicosUsuario servicoUsuarios;
	public static final String TOKEN_KEY = "wdsjfhkwbfdgwuierhweij";

	public RespostaDeLogin autentica(UsuarioLoginDto usuario) {

		if (!servicoUsuarios.validaUsuarioSenha(usuario)) {
			return new RespostaDeLogin("Usuario ou senha invalidos.");
		}

		String token = geraToken(usuario.getEmail());
		return new RespostaDeLogin(token);
	}

	private String geraToken(String email) {
		return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(email)
				.signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)).compact();// 3 min
	}

	public String getSujeitoDoToken(String authorizationHeader) {
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new SecurityException("Token inexistente ou mal formatado!");
		}

		// Extraindo apenas o token do cabecalho.
		String token = authorizationHeader.substring(FiltroDeJWT.TOKEN_INDEX);

		String subject = null;
		try {
			subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new SecurityException("Token invalido ou expirado!");
		}
		return subject;
	}
}


