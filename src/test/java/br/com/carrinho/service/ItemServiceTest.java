package br.com.carrinho.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;

import br.com.carrinho.exceptions.ValorInvalidoException;
import br.com.carrinho.model.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {
	
	@Autowired
	private ItemService service;
	
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

}