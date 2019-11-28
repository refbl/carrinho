package br.com.carrinho.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTest {
	
	@Test
	public void validaGettersSetters(){
		Item item = new Item();
		
		item.setId("12345");
		item.setNome("Teste de Sistemas");
		item.setValor(20.5);
		
		assertEquals(item.getId(), "12345");
		assertEquals(item.getNome(), "Teste de Sistemas");
		assertEquals(20.5, item.getValor(), 0.000001);

	}

}