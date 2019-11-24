package br.com.carrinho.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.carrinho.model.Usuario;
import br.com.carrinho.service.UsuarioService;

@RestController
public class UsuarioControllerApi {
	
	@Autowired
	private UsuarioService service;
	
	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/usuario" , method = RequestMethod.GET)
    public Iterable<Usuario> getUsuarios(@RequestParam(value="email",required = false) String email) {
		Iterable<Usuario> usuarios;
		
		if (email != null){
			Usuario usuario = service.pesquisaPorEmail(email);
			List<Usuario> usuarioList = new ArrayList<Usuario>();
			usuarioList.add(usuario);
			
			usuarios = usuarioList;
	    				
		} else {
			usuarios = service.obterTodos();
		}

    	return usuarios;

    }
    
	
	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/usuario", method = RequestMethod.POST)
    public ResponseEntity<UsuarioDto> incluiUsuario(@RequestBody UsuarioDto usuarioDto) {
		System.out.println("-- Incluir Usuario--");
		System.out.println(usuarioDto.getNome());
		System.out.println(usuarioDto.getEmail());

		String email = usuarioDto.getEmail() != null?usuarioDto.getEmail().trim():usuarioDto.getEmail();
	  	Usuario usuario = service.pesquisaPorEmail(email);
	  	
	  	if (usuario != null){
	  		usuarioDto.setMensagem("Usuário já Cadastrado com E-mail Informado");
			return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.UNAUTHORIZED);
	  	}
		
		
    	usuario = new Usuario(usuarioDto.getNome(), usuarioDto.getEmail());
    	service.salvar(usuario);
    	usuarioDto.setId(usuario.getId());
    	return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.CREATED);
    }
	
    
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/usuario/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<UsuarioDto> alterarUsuario(@PathVariable("id") String id, @RequestBody UsuarioDto usuarioDto) {
    
		System.out.println("-- Alterar Usuario - PATH");
		System.out.println(usuarioDto.getNome());
		System.out.println(usuarioDto.getEmail());
		
		String email = usuarioDto.getEmail() != null?usuarioDto.getEmail().trim():usuarioDto.getEmail();
	  	Usuario usuario = service.pesquisaPorEmail(email);

	  	if (usuario != null){
	  		
		  	// ID informado não é o mesmo da URI
		  	if (!usuario.getId().equalsIgnoreCase(id)){
		  		usuarioDto.setMensagem("Alteração Não Permitida");
		  		return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.UNAUTHORIZED);
		  	}
	  		
	  		System.out.println("Achou");
	  		usuario.setNome(usuarioDto.getNome());
	  		service.salvar(usuario);
	  		usuarioDto.setId(usuario.getId());
	      	return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.OK);

	  	} else {
	  		System.out.println("Nao Achou");
	  		usuarioDto.setMensagem("Usuário não Encontrado");
	  		return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.NOT_FOUND);
	  	}
    }
	
    
	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/usuario/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UsuarioDto> deletarUsuario(@PathVariable("id") String id) {
        System.out.println("Entrou no DELETE----");
		
	  	Usuario usuario = service.pesquisaPorId(id);
	  	UsuarioDto usuarioDto = new UsuarioDto();
        
    	if (usuario != null){
    		System.out.println("Achou");
    		
    		service.deletar(usuario);
        	return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.OK);
    	} else {
    		
    		System.out.println("Nao Achou");
    		usuarioDto.setMensagem("Usuario Não Encontrado");
    		return new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.NOT_FOUND);
    	}
    	
    }
}
