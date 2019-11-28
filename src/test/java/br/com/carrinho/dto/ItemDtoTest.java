package br.com.carrinho.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemDtoTest {
	
	@Test
	public void validaGettersSetters(){
		ItemDto itemDto = new ItemDto();
		
		itemDto.setId("12345");
		itemDto.setNome("Teste de Sistemas");
		itemDto.setValor(13.98);
		itemDto.setMensagem("Teste");
		
		assertEquals(itemDto.getId(), "12345");
		assertEquals(itemDto.getNome(), "Teste de Sistemas");
		assertEquals(13.98, itemDto.getValor(), 0.000001);
		assertEquals(itemDto.getMensagem(), "Teste");

	}

}