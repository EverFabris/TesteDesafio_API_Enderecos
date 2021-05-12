package com.usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuarios.model.Endereco;
import com.usuarios.model.Usuario;
import com.usuarios.repository.EnderecoRepositorio;
import com.usuarios.repository.UsuarioRepositorio;
import com.usuarios.service.ViaCEPClient;



@RestController
@RequestMapping("/enderecos")
public class EnderecoControlador {
	
	@Autowired	private EnderecoRepositorio enderecoRepository;
	@Autowired 	private UsuarioRepositorio usuarioRepository;	
	@Autowired ViaCEPClient viaCep;
	
	@GetMapping("/viacep/{cep}/{user}")
	public ResponseEntity<?> viaCep(@PathVariable String cep, @PathVariable Integer user){
		Usuario usuario = usuarioRepository.findById(user).orElse(null);
		Endereco endereco = viaCep.buscaEnderecoPor(cep);
		endereco.setUsuario(usuario);
		return ResponseEntity.created(null)
					.body(enderecoRepository.save(endereco));
	}
	
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody Endereco EnderecoNovo){		
		Endereco endereco = enderecoRepository.save(EnderecoNovo);
		return ResponseEntity.created(null).body(endereco);
	}
}