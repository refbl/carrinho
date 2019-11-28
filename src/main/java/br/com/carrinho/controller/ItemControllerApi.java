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

import br.com.carrinho.dto.ItemDto;
import br.com.carrinho.exceptions.ValorInvalidoException;
import br.com.carrinho.model.Item;
import br.com.carrinho.service.ItemService;

@RestController
public class ItemControllerApi {
	
	@Autowired
	private ItemService service;
	
	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/item" , method = RequestMethod.GET)
    public Iterable<Item> getItens(@RequestParam(value="nome",required = false) String nome) {
		Iterable<Item> itens;
		
		if (nome != null){
			Item item = service.pesquisaPorNome(nome);
			List<Item> itemList = new ArrayList<Item>();
			itemList.add(item);
			
			itens = itemList;
	    				
		} else {
			itens = service.obterTodos();
		}

    	return itens;

    }
    
	
	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/item", method = RequestMethod.POST)
    public ResponseEntity<ItemDto> incluiItem(@RequestBody ItemDto itemDto) {
		System.out.println("-- Incluir Item--");
		System.out.println(itemDto.getNome());
		System.out.println(itemDto.getValor());

		String nome = itemDto.getNome() != null?itemDto.getNome().trim():itemDto.getNome();
	  	Item item = service.pesquisaPorNome(nome);
	  	
	  	if (item != null){
	  		itemDto.setMensagem("Item já Cadastrado com Nome Informado");
			return new ResponseEntity<ItemDto>(itemDto, HttpStatus.UNAUTHORIZED);
	  	}
		
	  	try{
	    	item = new Item(itemDto.getNome(), itemDto.getValor());
	    	service.salvar(item);
	    	itemDto.setId(item.getId());
	    	return new ResponseEntity<ItemDto>(itemDto, HttpStatus.CREATED);

	  	} catch (ValorInvalidoException e) {
	  		itemDto.setMensagem("Valor Informado é Invalido");
			return new ResponseEntity<ItemDto>(itemDto, HttpStatus.BAD_REQUEST);
	  	}
    }
	
    
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/item/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ItemDto> alterarItem(@PathVariable("id") String id, @RequestBody ItemDto itemDto) {
    
		System.out.println("-- Alterar Item - PATH");
		System.out.println(itemDto.getNome());
		System.out.println(itemDto.getValor());
		
		String nome = itemDto.getNome() != null?itemDto.getNome().trim():itemDto.getNome();
	  	Item item = service.pesquisaPorNome(nome);

	  	if (item != null){
	  		
		  	// ID informado não é o mesmo da URI
		  	if (!item.getId().equalsIgnoreCase(id)){
		  		itemDto.setMensagem("Alteração Não Permitida");
		  		return new ResponseEntity<ItemDto>(itemDto, HttpStatus.UNAUTHORIZED);
		  	}
	  		
		  	try {
		  		System.out.println("Achou");
		  		item.setNome(itemDto.getNome());
		  		item.setValor(itemDto.getValor());
		  		service.salvar(item);
		  		itemDto.setId(item.getId());
		      	return new ResponseEntity<ItemDto>(itemDto, HttpStatus.OK);
		  	} catch (ValorInvalidoException e) {
		  		itemDto.setMensagem("Valor Informado é Invalido");
				return new ResponseEntity<ItemDto>(itemDto, HttpStatus.BAD_REQUEST);
		  	}

	  	} else {
	  		System.out.println("Nao Achou");
	  		itemDto.setMensagem("Item não Encontrado");
	  		return new ResponseEntity<ItemDto>(itemDto, HttpStatus.NOT_FOUND);
	  	}
    }
	
    
	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ItemDto> deletarItem(@PathVariable("id") String id) {
        System.out.println("Entrou no DELETE----");
		
	  	Item item = service.pesquisaPorId(id);
	  	ItemDto itemDto = new ItemDto();
        
    	if (item != null){
    		System.out.println("Achou");
    		
    		service.deletar(item);
        	return new ResponseEntity<ItemDto>(itemDto, HttpStatus.OK);
    	} else {
    		
    		System.out.println("Nao Achou");
    		itemDto.setMensagem("Item Não Encontrado");
    		return new ResponseEntity<ItemDto>(itemDto, HttpStatus.NOT_FOUND);
    	}
    	
    }
}