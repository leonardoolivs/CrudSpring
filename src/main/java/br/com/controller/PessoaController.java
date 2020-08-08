package br.com.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.entity.Pessoa;
import br.com.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository repository;

	@PostMapping
	public ResponseEntity<Pessoa> cadastrar(@Valid @RequestBody Pessoa pessoa) {
		Pessoa p = repository.save(pessoa);
		if (p == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(p, HttpStatus.CREATED);
		}
	}

	@GetMapping
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> p = repository.findAll();
		if (p.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(p, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscarPorId(@PathParam("id") Long id) {
		Optional<Pessoa> p = repository.findById(id);
		if (p.isPresent()) {
			return new ResponseEntity<>(p.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping
	public ResponseEntity<Pessoa> atualizar(@Valid @RequestBody Pessoa pessoa) {
		Pessoa p = repository.buscarPorId(pessoa.getId());
		p.setCpf(pessoa.getCpf());
		p.setIdade(pessoa.getIdade());
		p.setCpf(pessoa.getCpf());
		p.setPeso(pessoa.getPeso());
		Pessoa pp = repository.save(p);
		if (pp == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(pp, HttpStatus.OK);
		}
	}
}
