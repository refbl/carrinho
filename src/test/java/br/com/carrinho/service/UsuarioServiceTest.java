package br.com.carrinho.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.carrinho.model.Usuario;
import br.com.carrinho.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	@Test
	public void validaSalvarSucesso(){
		Usuario usuario = new Usuario();
		
		usuario.setId("12345");
		usuario.setEmail("teste@teste.com");
		usuario.setNome("Teste de Sistemas");
		
		service.salvar(usuario);
		usuario = null;
		usuario = service.pesquisaPorEmail("teste@teste.com");
		
		assertEquals(usuario.getId(), "12345");
		assertEquals(usuario.getNome(), "Teste de Sistemas");
		assertEquals(usuario.getEmail(), "teste@teste.com");
	}
	
	@Test
	public void validaObterPorId(){
		Usuario usuario = new Usuario();
		usuario.setId("12345");
		usuario.setEmail("teste@teste.com");
		usuario.setNome("Teste de Sistemas");
		
		service.salvar(usuario);
		usuario = null;
		usuario = service.pesquisaPorId("12345");
		
		assertEquals(usuario.getId(), "12345");
		assertEquals(usuario.getNome(), "Teste de Sistemas");
		assertEquals(usuario.getEmail(), "teste@teste.com");
	}
	
	@Test
	public void validaObterTodos(){
		
		repositoryUsuario.deleteAll();
		
		Usuario usuario = new Usuario();
		
		usuario.setEmail("teste@teste.com");
		usuario.setNome("Teste de Sistemas");
		service.salvar(usuario);
		
		Usuario usuario2 = new Usuario();
		usuario2.setEmail("jorge@teste.com");
		usuario2.setNome("Jorge Teste");
		service.salvar(usuario2);
		
		
		Iterable<Usuario> usuariosIterable = service.obterTodos();
		
		int i=0;
		for (Usuario usuarioAux : usuariosIterable) {
			if (i==0){
				assertEquals(usuarioAux.getEmail(), "teste@teste.com");
				assertEquals(usuarioAux.getNome(), "Teste de Sistemas");
			} else {
				assertEquals(usuarioAux.getEmail(), "jorge@teste.com");
				assertEquals(usuarioAux.getNome(), "Jorge Teste");
			}
			i++;
		}
	}
	
	@Test
	public void validaExclusao(){
		Usuario usuario = new Usuario("Joaquim", "joaquim@teste.com");
		service.salvar(usuario);

		assertThat(usuario.getId(), true);
		assertEquals(usuario.getNome(), "Joaquim");
		assertEquals(usuario.getEmail(), "joaquim@teste.com");
		
		service.deletar(usuario);
		
		usuario = service.pesquisaPorEmail("joaquim@teste.com");

		assertNull(usuario); 
	}

}