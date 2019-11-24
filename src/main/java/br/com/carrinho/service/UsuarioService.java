package br.com.carrinho.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carrinho.model.Usuario;
import br.com.carrinho.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Iterable<Usuario> obterTodos(){
		return repository.findAll();
	}
	
	public void salvar(Usuario usuario){
		repository.save(usuario);
	}
	
	public Usuario pesquisaPorEmail(String email){
		return repository.findByEmail(email);
	}
	
	public void deletar(Usuario usuario){
		repository.delete(usuario);
	}

	public Usuario pesquisaPorId(String id) {

		Optional<Usuario> result = repository.findById(id);
		return result.isPresent()?result.get():null;

		
	}
	

}
