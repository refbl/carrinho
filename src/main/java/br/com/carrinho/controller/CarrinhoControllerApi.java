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

import br.com.carrinho.dto.CarrinhoCompraDto;
import br.com.carrinho.dto.ItemCompradoDto;
import br.com.carrinho.model.Carrinho;
import br.com.carrinho.model.Item;
import br.com.carrinho.model.Usuario;
import br.com.carrinho.service.CarrinhoService;
import br.com.carrinho.service.ItemService;
import br.com.carrinho.service.UsuarioService;

@RestController
public class CarrinhoControllerApi {
	@Autowired
	private CarrinhoService carrinhoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ItemService itemService;
	
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
	
	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/carrinho/{idCarrinho}/fecharcompra" , method = RequestMethod.GET)
    public Iterable<CarrinhoCompraDto> fecharCompra(@PathVariable("idCarrinho") String idCarrinho) {
		Carrinho carrinho = carrinhoService.pesquisaPorId(idCarrinho);
		
		List<ItemCompradoDto> itemCompradoDtoList = carrinhoService.fecharCompra(carrinho);
		
		List<CarrinhoCompraDto> carrinhoCompraDtoList = new ArrayList<>();
		
        CarrinhoCompraDto carrinhoCompraDto = new CarrinhoCompraDto();
        carrinhoCompraDto.setCarrinho(carrinho);
        carrinhoCompraDto.setItensComprados(itemCompradoDtoList);
        carrinhoCompraDtoList.add(carrinhoCompraDto);

    	return carrinhoCompraDtoList;
    }
	
	
	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/carrinho", method = RequestMethod.POST)
    public ResponseEntity<Carrinho> incluiCarrinho(@RequestBody Usuario usuario) {
	
    	Carrinho carrinho = carrinhoService.salvar(usuario);

    	return new ResponseEntity<Carrinho>(carrinho, HttpStatus.CREATED);
    }
	
	/*
	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/carrinho", method = RequestMethod.POST)
    public ResponseEntity<Carrinho> incluirCarrinho(@PathVariable("idCarrinho") String idCarrinho,@PathVariable("idItem") String idItem) {
		System.out.println("-- Incluir Item--");
		System.out.println(idCarrinho);
		System.out.println(idItem);
		
		Carrinho carrinho = carrinhoService.pesquisaPorId(idCarrinho);
		if (carrinho == null){
	  		System.out.println("Nao Achou Carrinho");
	  		//usuarioDto.setMensagem("Usuário não Encontrado");
	  		return new ResponseEntity<Carrinho>(carrinho, HttpStatus.NOT_FOUND);
			
		}
		
		Item item = itemService.pesquisaPorId(idItem);
		if (item == null){
	  		System.out.println("Nao Achou Item");
	  		//usuarioDto.setMensagem("Usuário não Encontrado");
	  		return new ResponseEntity<Carrinho>(carrinho, HttpStatus.NOT_FOUND);
		}
		// Atualiza Carrinho
		carrinhoService.adicionaItem(carrinho, item);
		return new ResponseEntity<Carrinho>(carrinho, HttpStatus.OK);
    }
	*/
	
	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/carrinho/{idCarrinho}/item/{idItem}", method = RequestMethod.POST)
    public ResponseEntity<Carrinho> incluirItem(@PathVariable("idCarrinho") String idCarrinho,@PathVariable("idItem") String idItem) {
		Carrinho carrinho = carrinhoService.pesquisaPorId(idCarrinho);
		if (carrinho == null){
	  		return new ResponseEntity<Carrinho>(carrinho, HttpStatus.NOT_FOUND);
			
		}
		
		Item item = itemService.pesquisaPorId(idItem);
		if (item == null){
	  		return new ResponseEntity<Carrinho>(carrinho, HttpStatus.NOT_FOUND);
		}
		// Atualiza Carrinho
		carrinhoService.adicionaItem(carrinho, item);
		return new ResponseEntity<Carrinho>(carrinho, HttpStatus.OK);
    }


	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/carrinho/{idCarrinho}/item/{idItem}", method = RequestMethod.DELETE)
    public ResponseEntity<Carrinho> deletarItem(@PathVariable("idCarrinho") String idCarrinho,@PathVariable("idItem") String idItem) {
		Carrinho carrinho = carrinhoService.pesquisaPorId(idCarrinho);
		if (carrinho == null){
	  		return new ResponseEntity<Carrinho>(carrinho, HttpStatus.NOT_FOUND);
			
		}
		
		Item item = itemService.pesquisaPorId(idItem);
		if (item == null){
	  		return new ResponseEntity<Carrinho>(carrinho, HttpStatus.NOT_FOUND);
		}
		// Atualiza Carrinho
		carrinhoService.removeItem(carrinho, item);
		return new ResponseEntity<Carrinho>(carrinho, HttpStatus.OK);
    }
}
