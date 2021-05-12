package com.usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuarios.model.Usuario;
import com.usuarios.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
	
	@Autowired	UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<?> criar(@RequestBody Usuario userNovo){
		return usuarioService.salvar(userNovo);
	}
	
	@GetMapping("/{cod}")
	public ResponseEntity<?> listaDeEnderecos(@PathVariable Integer cod){
		return usuarioService.lista(cod);
	}
}