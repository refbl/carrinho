package br.com.carrinho.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioTest {
	
	@Test
	public void validaGettersSetters(){
		Usuario usuario = new Usuario();
		
		usuario.setId("12345");
		usuario.setEmail("teste@teste.com");
		usuario.setNome("Teste de Sistemas");
		
		assertEquals(usuario.getId(), "12345");
		assertEquals(usuario.getNome(), "Teste de Sistemas");
		assertEquals(usuario.getEmail(), "teste@teste.com");

	}
	
	@Test
	public void validaEquals(){
		Usuario usuario = new Usuario();
		
		usuario.setId("12345");
		usuario.setEmail("teste@teste.com");
		usuario.setNome("Teste de Sistemas");
		
		Usuario usuario2 = new Usuario();
		
		usuario2.setId("12345");
		usuario2.setEmail("teste@teste.com");
		usuario2.setNome("Teste de Sistemas");

		assertEquals(usuario.equals(usuario2), true);

	}

}
