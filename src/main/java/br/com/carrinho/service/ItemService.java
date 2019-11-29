package br.com.carrinho.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carrinho.exceptions.ValorInvalidoException;
import br.com.carrinho.model.Item;
import br.com.carrinho.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;
	
	public Iterable<Item> obterTodos(){
		return repository.findAll();
	}
	
	public void salvar(Item item) throws ValorInvalidoException{
		if (item.getValor() <= 0){
			throw new ValorInvalidoException("Valor do Item deve ser maior que zero");
		}
		repository.save(item);
	}
	
	public Item pesquisaPorNome(String nome){
		return repository.findByNome(nome);
	}
	
	public void deletar(Item item){
		repository.delete(item);
	}

	public Item pesquisaPorId(String id) {
		Optional<Item> result = repository.findById(id);
		return result.isPresent()?result.get():null;
		
	}
	
}
