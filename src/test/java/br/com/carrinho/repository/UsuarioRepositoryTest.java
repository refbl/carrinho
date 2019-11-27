package br.com.carrinho.repository;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.carrinho.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioRepositoryTest {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	public void insereValidaDadosEntrada(){
		Usuario usuario = new Usuario("Alice", "alice@teste.com");
		this.usuarioRepository.save(usuario);
	
		assertThat(usuario.getId(), true);
		assertEquals(usuario.getNome(), "Alice");
		assertEquals(usuario.getEmail(), "alice@teste.com");
	}
	
	@Test
	public void alteraValidaDados(){
		Usuario usuario = new Usuario("Alice", "alice@teste.com");
		this.usuarioRepository.save(usuario);

		assertThat(usuario.getId(), true);
		assertEquals(usuario.getNome(), "Alice");
		assertEquals(usuario.getEmail(), "alice@teste.com");

		Usuario usuario2 = new Usuario("Alice Teste", "alice@teste.com");
		this.usuarioRepository.save(usuario2);
	
		assertThat(usuario2.getId(), true);
		assertEquals(usuario2.getNome(), "Alice Teste");
		assertEquals(usuario2.getEmail(), "alice@teste.com");
	}
	
	@Test
	public void excluiValidaDados(){
		Usuario usuario = new Usuario("Alison", "alison@teste.com");
		this.usuarioRepository.save(usuario);

		assertThat(usuario.getId(), true);
		assertEquals(usuario.getNome(), "Alison");
		assertEquals(usuario.getEmail(), "alison@teste.com");
		
		this.usuarioRepository.delete(usuario);
		
		usuario = this.usuarioRepository.findByEmail("alison@teste.com");

		assertNull(usuario); 
	}

}
