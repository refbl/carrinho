package br.com.carrinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.carrinho.model.Carrinho;
import br.com.carrinho.model.Item;
import br.com.carrinho.model.Usuario;
import br.com.carrinho.repository.CarrinhoRepository;
import br.com.carrinho.repository.ItemRepository;
import br.com.carrinho.repository.UsuarioRepository;

@SpringBootApplication
public class Configuracao implements CommandLineRunner {
	
	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	@Autowired
	private ItemRepository repositoryItem;
	
	@Autowired
	private CarrinhoRepository repositoryCarrinho;
	
	public static void main(String[] args) {
		SpringApplication.run(Configuracao.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {

		repositoryUsuario.deleteAll();
        System.out.println("-------------- Gera Massa USUARIOS  -----------------------------");
		// Cria Massa de Usuarios
		repositoryUsuario.save(new Usuario("Cristiane", "cristiane@teste.com"));
		repositoryUsuario.save(new Usuario("Mauricio", "mauricio@teste.com"));
		repositoryUsuario.save(new Usuario("Josefina", "josefina@teste.com"));
		repositoryUsuario.save(new Usuario("Manoel", "firma@teste.com"));

        System.out.println("-------------- Gera Massa ITENS   ------------------------------");
		
		repositoryItem.deleteAll();

		repositoryItem.save(new Item("Garfo", 20.0));
		repositoryItem.save(new Item("Colher", 15.80));
		repositoryItem.save(new Item("Colher Sobremesa", 15.0));
		repositoryItem.save(new Item("Faca", 20.0));
		repositoryItem.save(new Item("Aparelho de Som", 300.0));

        System.out.println("-------------- Gera Massa CARRINHO -----------------------------");
        Usuario usuario = repositoryUsuario.findByEmail("firma@teste.com");
        Item item = repositoryItem.findByNome("Garfo");
        
		repositoryCarrinho.deleteAll();

        Carrinho carrinho = new Carrinho(usuario ,item);
        carrinho.addItem(item);
        repositoryCarrinho.save(carrinho);
        
        item = repositoryItem.findByNome("Colher");
        carrinho.addItem(item);
        carrinho.addItem(item);
        carrinho.addItem(item);
        
        item = repositoryItem.findByNome("Aparelho de Som");
        carrinho.addItem(item);
        carrinho.addItem(item);
        
        repositoryCarrinho.save(carrinho);
        System.out.println(carrinho);
	}

}
