package br.com.zup.edu.saintgermain.controller;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.edu.saintgermain.leito.Leito;
import br.com.zup.edu.saintgermain.leito.LeitoRepository;

@RestController
@RequestMapping("/leitos")
public class LeitoController {
	
	private final LeitoRepository repository;

	public LeitoController(LeitoRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	@PatchMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable("id") Long idLeito){
		
		Leito leito = repository.findById(idLeito).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Leito não encontrado"));
		
		leito.reservar();
		
		return ResponseEntity.noContent().build();
	}
	
}
