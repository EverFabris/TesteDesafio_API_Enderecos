package com.usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.usuarios.model.Endereco;
import com.usuarios.model.Usuario;
import com.usuarios.repository.EnderecoRepositorio;
import com.usuarios.repository.UsuarioRepositorio;


@Service
public class UsuarioService {
	@Autowired 	private UsuarioRepositorio usuarioRepository;	
	@Autowired 	private EnderecoRepositorio enderecoRepository;
	
	public List<Usuario> lista(){
		return usuarioRepository.findAll();
	}
	
	public ResponseEntity<?> lista(Integer cod){
		List<Endereco> enderecos = enderecoRepository.findByUsuario_Id(cod);
		Usuario user = usuarioRepository.findById(cod).orElse(null);
		if (user == null) {
			return new ResponseEntity<String>("Usuário não encontrado!",HttpStatus.NOT_FOUND);
		}
		user.setEnderecos(enderecos);
		return ResponseEntity.ok(user);
	}
	
	public ResponseEntity<?> salvar(Usuario userNew) {
		for (Usuario userold : lista()) {	
			if (userold.getCpf().equals(userNew.getCpf())){
				return new ResponseEntity<String>
				("Não foi possível inserir usuário, CPF duplicado",HttpStatus.BAD_REQUEST);
			}
			if (userold.getEmail().equals(userNew.getEmail())){
				return new ResponseEntity<String>
				("Não foi possível inserir usuário, E-mail duplicado",HttpStatus.BAD_REQUEST);
			}
		}
		Usuario user = usuarioRepository.save(userNew);
		return ResponseEntity.created(null).body(user);
	}
}

