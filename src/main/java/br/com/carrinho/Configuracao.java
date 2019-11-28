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
import br.com.carrinho.service.CarrinhoService;




@SpringBootApplication
public class Configuracao implements CommandLineRunner {
	
	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	@Autowired
	private ItemRepository repositoryItem;
	
	@Autowired
	private CarrinhoRepository repositoryCarrinho;
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Configuracao.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {

		System.out.println("-------------- USUARIO     --------------------------------");
		
		repositoryUsuario.deleteAll();

		// save a couple of customers
		repositoryUsuario.save(new Usuario("Alice", "alice@teste.com"));
		repositoryUsuario.save(new Usuario("Bob", "bob@teste.com"));
		repositoryUsuario.save(new Usuario("Jose", "firma@teste.com"));
		repositoryUsuario.save(new Usuario("Maria", "firma2@teste.com"));

		// fetch all customers
		System.out.println("Usuarios found with findAll():");
		System.out.println("-------------------------------");
		for (Usuario usuario : repositoryUsuario.findAll()) {
			System.out.println(usuario);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Usuarios found with findByName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repositoryUsuario.findByNome("Alice"));

		System.out.println("Usuarios found with findByEmail('firma@teste.com'):");
		System.out.println("--------------------------------");
		
		
        System.out.println("-------------- ITENS   --------------------------------");
		
		repositoryItem.deleteAll();

		// save a couple of customers
		repositoryItem.save(new Item("Garfo", 20.0));
		repositoryItem.save(new Item("Colher", 15.80));
		repositoryItem.save(new Item("Colher Sobremesa", 15.0));
		repositoryItem.save(new Item("Faca", 20.0));
		repositoryItem.save(new Item("Aparelho de Som", 300.0));

		// fetch all customers
		System.out.println("Itens found with findAll():");
		System.out.println("-------------------------------");
		for (Item item : repositoryItem.findAll()) {
			System.out.println(item);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Itens found with findByNome('Colher'):");
		System.out.println("--------------------------------");
		System.out.println(repositoryItem.findByNome("Colher"));

		System.out.println("Item found with findByValor(20.0):");
		System.out.println("--------------------------------");
		for (Item item : repositoryItem.findByValor(20.0)) {
			System.out.println(item);
		}

        System.out.println("-------------- CARRINHO    --------------------------------");
		
        Usuario usuario = repositoryUsuario.findByNome("Maria");
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
        
		// fetch an individual customer
		System.out.println("Carrinho findByUsuario():");
		System.out.println("--------------------------------");
		System.out.println(repositoryCarrinho.findByUsuario(usuario));
		
		System.out.println("---");
		
		System.out.println(carrinhoService.fecharCompra(carrinho));
		
		System.out.println("--****");
		
		System.out.println(carrinhoService.obterListaCarrinhos());
		
		/*
		// Count
		itens.stream()
		  .collect(Collectors.groupingBy(item2 -> item2.nome, Collectors.counting()))
		  .forEach((nome,count)->System.out.println(nome+"\t"+count));
		
		System.out.println("---");
		
		// Count
		itens.stream()
		  .collect(Collectors.groupingBy(item2 -> item2, Collectors.counting()))
		  .forEach((item2,count)->System.out.println(item2+"\t"+count));
		
		System.out.println("---");
		
		//Sum
		itens.stream()
		  .collect(Collectors.groupingBy(item2 -> item2.nome,
		                                    Collectors.summingDouble(item2-> item2.valor)))
		  .forEach((nome,sumValor)->System.out.println(nome+"\t"+sumValor));
		
		
		System.out.println("---");
		
        System.out.println(itens);
        
        List<Item> itensSemRepeticao = itens.stream().distinct().collect(Collectors.toList());
         
        System.out.println(listWithoutDuplicates);
        */
		
	}

}
