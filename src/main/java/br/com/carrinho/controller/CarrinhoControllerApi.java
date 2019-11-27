package br.com.carrinho.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.carrinho.model.Carrinho;
import br.com.carrinho.model.Usuario;
import br.com.carrinho.service.CarrinhoService;
import br.com.carrinho.service.UsuarioService;

@RestController
public class CarrinhoControllerApi {
	@Autowired
	private CarrinhoService carrinhoService;
	@Autowired
	private UsuarioService usuarioService;
	
	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/carrinho" , method = RequestMethod.GET)
    public Iterable<Carrinho> getUsuarios(@RequestParam(value="email",required = false) String email) {
		Iterable<Carrinho> carrinhos=null;
		
		if (email != null){
			Usuario usuario = usuarioService.pesquisaPorEmail(email);

			if (usuario != null){
				List<Carrinho> carrinhoList = new ArrayList<Carrinho>();
				Carrinho carrinho = carrinhoService.pesquisaPorUsuario(usuario);
				carrinhoList.add(carrinho);
				carrinhos = carrinhoList;
				
			} 
		} else {
			// Obtem todos carrinhos
			carrinhos = carrinhoService.obterTodos();
		}

    	return carrinhos;

    }

}
