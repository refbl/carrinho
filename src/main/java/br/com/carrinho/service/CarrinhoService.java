package br.com.carrinho.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carrinho.dto.CarrinhoDto;
import br.com.carrinho.dto.ItemCompradoDto;
import br.com.carrinho.model.Carrinho;
import br.com.carrinho.model.Item;
import br.com.carrinho.model.Usuario;
import br.com.carrinho.repository.CarrinhoRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository repository;
	
	public Iterable<Carrinho> obterTodos(){
		return repository.findAll();
	}
		
	
	public Iterable<CarrinhoDto> obterListaCarrinhos(){
		
		List<Carrinho> carrinhoList = repository.findAll();
		List<CarrinhoDto> carrinhoDtoList = new ArrayList<>();
 		
		for (Carrinho carrinho : carrinhoList) {
			CarrinhoDto carrinhoDto = new CarrinhoDto();
			
			carrinhoDto.setCarrinho(carrinho);
			
			List<Item> itens = carrinho.getItens();
			
    		//Sum
			carrinhoDto.setValorTotal(itens.stream().mapToDouble(f -> f.getValor()).sum());
			carrinhoDtoList.add(carrinhoDto);
		}
		
		// Faz Ordenacao pelo Valor do Carrinho
		Comparator<CarrinhoDto> compareByValor = (CarrinhoDto carrinho1, CarrinhoDto carrinho2) -> carrinho1.getValorTotal().compareTo( carrinho2.getValorTotal());
        Collections.sort(carrinhoDtoList, compareByValor);
		
		
		return carrinhoDtoList;
		
	}
	
	public void salvar(Carrinho carrinho){
		repository.save(carrinho);
	}
	
	public Carrinho salvar(Usuario usuario){
		Carrinho carrinho = new Carrinho(usuario,null);
		repository.save(carrinho);
		return carrinho;
	}
	
	public Carrinho pesquisaPorUsuario(Usuario usuario){
		return repository.findByUsuario(usuario);
	}
	
	public void deletar(Carrinho carrinho){
		repository.delete(carrinho);
	}

	public Carrinho pesquisaPorId(String id) {
		Optional<Carrinho> result = repository.findById(id);
		return result.isPresent()?result.get():null;
		
	}
	
	public void adicionaItem(Carrinho carrinho, Item item){
		carrinho.addItem(item);
		repository.save(carrinho);
	}
	
	public void removeItem(Carrinho carrinho, Item item){
		carrinho.removeItem(item);
		repository.save(carrinho);
	}
	
	public List<ItemCompradoDto> fecharCompra(Carrinho carrinho){
		List<ItemCompradoDto> itensCarrinho = new ArrayList<>();
		
		List<Item> itens = carrinho.getItens();
        List<Item> itensSemRepeticao = itens.stream().distinct().collect(Collectors.toList());
        
        // Realiza Ordenação 
        Comparator<Item> compareByName = (Item item1, Item item2) -> item1.getNome().compareTo( item2.getNome());
        Collections.sort(itensSemRepeticao, compareByName);
        
        System.out.println("Itens sem Repeticao:" + itensSemRepeticao);
        
        for (Item itemCompra : itensSemRepeticao) {
        	
    		ItemCompradoDto itemCompradoDto = new ItemCompradoDto();
        	itemCompradoDto.setItem(itemCompra);
        	
    		// Count
        	itens.stream()
        	  .filter(item -> item.getNome().equals(itemCompra.getNome()))
    		  .collect(Collectors.groupingBy(item -> item.nome, Collectors.counting()))
    		  .forEach((nome,count)->itemCompradoDto.setQuantidade(count.intValue()));
    		
    		
    		//Sum
        	itens.stream()
        	  .filter(item -> item.getNome().equals(itemCompra.getNome()))
    		  .collect(Collectors.groupingBy(item -> item.nome,
    		                                    Collectors.summingDouble(item-> itemCompra.valor)))
    		  .forEach((nome,sumValor) ->itemCompradoDto.setValorTotal(sumValor));
        	
			//Adiciona no List
    		itensCarrinho.add(itemCompradoDto);
		}
        
         
		return itensCarrinho;
	}

}
