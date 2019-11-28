package br.com.carrinho.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.carrinho.model.Carrinho;
import br.com.carrinho.model.Item;
import br.com.carrinho.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrinhoRepositoryTest {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Test
	public void insereValidaDadosEntrada(){
		Usuario usuario = new Usuario("Alice", "alice@teste.com");
		this.usuarioRepository.save(usuario);
	
		assertThat(usuario.getId(), true);
		assertEquals(usuario.getNome(), "Alice");
		assertEquals(usuario.getEmail(), "alice@teste.com");
		
		Item item = new Item("Garfo", 20.0);
		itemRepository.save(item);
		
		assertThat(item.getId(), true);
		assertEquals(item.getNome(), "Garfo");
		assertEquals(20.0, item.getValor(), 0.00001);
		
        Carrinho carrinho = new Carrinho(usuario ,item);
        carrinho.addItem(item);
        carrinhoRepository.save(carrinho);
		
		assertThat(carrinho.getId(), true);
		assertEquals(carrinho.getItens().get(0).getNome(), "Garfo");
		assertEquals(20.0, carrinho.getItens().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void validaAlteracaoItensCarrinho(){
		Usuario usuario = new Usuario("Alice", "alice@teste.com");
		this.usuarioRepository.save(usuario);
	
		assertThat(usuario.getId(), true);
		assertEquals(usuario.getNome(), "Alice");
		assertEquals(usuario.getEmail(), "alice@teste.com");
		
		Item item = new Item("Garfo", 20.0);
		itemRepository.save(item);
		
		assertThat(item.getId(), true);
		assertEquals(item.getNome(), "Garfo");
		assertEquals(20.0, item.getValor(), 0.00001);
		
        Carrinho carrinho = new Carrinho(usuario ,item);
        carrinhoRepository.save(carrinho);
		
		assertThat(carrinho.getId(), true);
		assertEquals(carrinho.getItens().get(0).getNome(), "Garfo");
		assertEquals(20.0, carrinho.getItens().get(0).getValor(), 0.00001);
		
		Item item2 = new Item("Desodorante", 13.0);
		itemRepository.save(item2);	
		
		carrinho.addItem(item2);
		assertEquals(carrinho.getItens().get(1).getNome(), "Desodorante");
		assertEquals(13.0, carrinho.getItens().get(1).getValor(), 0.00001);
		
		assertEquals(2, carrinho.getItens().size());
		
		carrinho.removeItem(item2);
		assertEquals(1, carrinho.getItens().size());
		
	}	
	
	
	
	

}
