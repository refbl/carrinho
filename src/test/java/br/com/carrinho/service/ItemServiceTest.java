package br.com.carrinho.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.carrinho.exceptions.ValorInvalidoException;
import br.com.carrinho.model.Item;
import br.com.carrinho.repository.ItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {
	
	@Autowired
	private ItemService service;
	
	@Autowired
	private ItemRepository repositoryItem;
	
	@Test
	public void validaSalvarSucesso(){
		Item item = new Item();
		
		item.setId("12345");
		item.setNome("Teste de Sistemas");
		item.setValor(20.5);
		
		try {
			service.salvar(item);
		} catch (ValorInvalidoException e) {
			e.printStackTrace();
		}
		
		Item itemRet = service.pesquisaPorId(item.getId());
		
		assertEquals(itemRet.getId(), "12345");
		assertEquals(itemRet.getNome(), "Teste de Sistemas");
		assertEquals(20.5, itemRet.getValor(), 0.000001);

	}
	
	@Test
	public void validaSalvarErroValor(){
		Item item = new Item();
		
		item.setId("12345");
		item.setNome("Teste de Sistemas");
		item.setValor(0d);
		
		try {
			service.salvar(item);
		} catch (ValorInvalidoException e) {
			e.printStackTrace();
			assertThat(e.toString(), true);
		}
		
	}
	
	@Test
	public void validaObterTodos(){

		repositoryItem.deleteAll();
		
		Item item = new Item();
		
		item.setNome("Teste de Sistemas");
		item.setValor(20.5);
		try {
			service.salvar(item);
		} catch (ValorInvalidoException e) {
			e.printStackTrace();
		}
		
		Item item2 = new Item();
		item2.setNome("Teste2");
		item2.setValor(33.60);
		try {
			service.salvar(item2);
		} catch (ValorInvalidoException e) {
			e.printStackTrace();
		}
		
		
		Iterable<Item> itensIterable = service.obterTodos();
		
		int i=0;
		for (Item itemAux : itensIterable) {
			if (i==0){
				assertEquals(itemAux.getNome(), "Teste de Sistemas");
				assertEquals(20.5, itemAux.getValor(), 0.000001);
			} else {
				assertEquals(itemAux.getNome(), "Teste2");
				assertEquals(33.60, itemAux.getValor(), 0.000001);
			}
			i++;
		}
		
	}

}