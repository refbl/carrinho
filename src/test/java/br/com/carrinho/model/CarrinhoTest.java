package br.com.carrinho.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrinhoTest {
	
	@Test
	public void validaGettersSetters(){
		Item item = new Item();
		item.setId("12345");
		item.setNome("Teste de Sistemas");
		item.setValor(20.5);
		
		
		List<Item> itens = new ArrayList<>();
		itens.add(item);
		
        item = new Item();
        item.setId("33333");
		item.setNome("Teste Teste");
		item.setValor(15.0);
		itens.add(item);
		
		Usuario usuario = new Usuario();
		usuario.setId("54321");
		usuario.setEmail("teste@teste.com");
		usuario.setNome("Teste Junior");
		
		Carrinho carrinho = new Carrinho();
		carrinho.setId("98765");
		carrinho.setUsuario(usuario);
		carrinho.setItens(itens);
		
		
		assertEquals(carrinho.getId(), "98765");
		assertEquals(carrinho.getItens().get(0).getNome(), "Teste de Sistemas");
		assertEquals(20.5, carrinho.getItens().get(0).getValor(), 0.000001);
		assertEquals(carrinho.getUsuario().getNome(), "Teste Junior");
		assertEquals(carrinho.getUsuario().getEmail(), "teste@teste.com");
		
	}
}
