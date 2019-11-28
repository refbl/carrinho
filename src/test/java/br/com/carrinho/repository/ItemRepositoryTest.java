package br.com.carrinho.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.carrinho.model.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemRepositoryTest {
	@Autowired
	private ItemRepository itemRepository;
	
	@Test
	public void insereValidaDadosEntrada(){
		
		Item item = new Item("Garfo", 20.0);
		itemRepository.save(item);
		
		assertThat(item.getId(), true);
		assertEquals(item.getNome(), "Garfo");
		assertEquals(20.0, item.getValor(), 0.00001);
	}
	
	@Test
	public void alteraValidaDados(){
		
		Item item = new Item("Garfo", 20.0);
		itemRepository.save(item);
		
		item.setNome("Garfo Duplo");
		item.setValor(35.0);
		itemRepository.save(item);
		
		Item item2 = itemRepository.findByNome("Garfo Duplo");
		
		assertThat(item2.getId(), true);
		assertEquals(item2.getNome(), "Garfo Duplo");
		assertEquals(35.0, item2.getValor(), 0.00001);
		
	}
	
	@Test
	public void excluiValidaDados(){
		Item item = new Item("Mochila", 20.0);
		itemRepository.save(item);

		assertThat(item.getId(), true);
		
		itemRepository.delete(item);
		
		item = itemRepository.findByNome("Mochila");
		assertNull(item); 
	}
}
