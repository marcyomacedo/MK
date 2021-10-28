package ufpb.dsc.lab3.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ufpb.dsc.lab3.dtos.ComentarioDto;
import ufpb.dsc.lab3.dtos.DisciplinaDto;
import ufpb.dsc.lab3.dtos.DisciplinaDtoComentario;
import ufpb.dsc.lab3.dtos.DisciplinaDtoLikes;
import ufpb.dsc.lab3.dtos.DisciplinaDtoNota;
import ufpb.dsc.lab3.dtos.NotaDto;
import ufpb.dsc.lab3.entidades.Disciplina;
import ufpb.dsc.lab3.servicos.ServicosDisciplina;

@RestController
public class ControladorDeDisciplinas {
	
	@Autowired
	private ServicosDisciplina servicos;

	@GetMapping("/v3/api/disciplinas")
    public ResponseEntity<List<DisciplinaDto>> listaDisciplinas(){
        return new ResponseEntity<List<DisciplinaDto>>(servicos.listarDisciplinas(), HttpStatus.OK);
    }
	
	@GetMapping("/v3/api/disciplinas/{id}")
	public ResponseEntity <Disciplina> listaDisciplinasPorId(@PathVariable Long id){
        try {
            return new ResponseEntity<>(servicos.listarDisciplinaPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PatchMapping("/v3/api/disciplinas/likes/{id}")
    public ResponseEntity<DisciplinaDtoLikes> adicionaLikes(@PathVariable Long id,
    		@RequestHeader("Authorization") String header) throws Exception {
        try {
            return new ResponseEntity<>(servicos.adicionaLikeDisciplina(id, header), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PatchMapping("/v3/api/disciplinas/nota/{id}")
    public ResponseEntity<DisciplinaDtoNota> adicionaNota(@PathVariable Long id, @RequestBody NotaDto nota,
    		@RequestHeader("Authorization") String header) throws Exception {
        try {
            return new ResponseEntity<>(servicos.adicionaNotaDisciplina(id, nota, header), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PatchMapping("/v3/api/disciplinas/comentarios/{id}")
    public ResponseEntity<DisciplinaDtoComentario> adicionaComentario(@PathVariable Long id, @RequestBody ComentarioDto comentario,
    		@RequestHeader("Authorization") String header) throws Exception {
        try {

            return new ResponseEntity<>(servicos.adicionaComentarioDisciplina(id, comentario, header), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@GetMapping("/v3/api/disciplinas/ranking/likes")
    public ResponseEntity<List<Disciplina>> rankingLikes() {
        return new ResponseEntity<>(servicos.rankingPorLikes(), HttpStatus.OK);
    }
	
	
    @GetMapping("/v3/api/disciplinas/ranking/notas")
    public ResponseEntity<List<Disciplina>> rankingNotas() {
        return new ResponseEntity<>(servicos.rankingPorNota(), HttpStatus.OK);
    }	
	
	
	
	
	
	public ControladorDeDisciplinas(ServicosDisciplina servicos) {
		super();
		this.servicos = servicos;
	}
	
	
	

}
