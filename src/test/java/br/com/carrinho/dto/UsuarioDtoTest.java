package br.com.carrinho.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioDtoTest {
	@Test
	public void validaGettersSetters(){
		UsuarioDto usuarioDto = new UsuarioDto();
		
		usuarioDto.setId("12345");
		usuarioDto.setEmail("teste@teste.com");
		usuarioDto.setNome("Teste de Sistemas");
		usuarioDto.setMensagem("Teste");
		
		assertEquals(usuarioDto.getId(), "12345");
		assertEquals(usuarioDto.getNome(), "Teste de Sistemas");
		assertEquals(usuarioDto.getEmail(), "teste@teste.com");
		assertEquals(usuarioDto.getMensagem(), "Teste");

	}
}
