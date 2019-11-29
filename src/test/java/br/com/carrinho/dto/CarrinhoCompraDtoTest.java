package br.com.carrinho.dto;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.carrinho.model.Carrinho;
import br.com.carrinho.model.Item;
import br.com.carrinho.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrinhoCompraDtoTest {
	
	@Test
	public void validaGettersSetters(){
		CarrinhoCompraDto car = new CarrinhoCompraDto();
		
		
		Carrinho carrinho = new Carrinho(new Usuario("Cristian", "cristian@teste.com"), new Item("Lampada", 20.0));
		List<ItemCompradoDto> itensComprados = new ArrayList<>();
		ItemCompradoDto itemComprado = new ItemCompradoDto();
		itemComprado.setItem(new Item("Lampada", 20.0));
		itemComprado.setQuantidade(2);
		itemComprado.setValorTotal(40.0);
		
		car.setCarrinho(carrinho);
		car.setItensComprados(itensComprados);
		car.setValorTotal(250.46);
		
		assertEquals(car.getCarrinho().getUsuario().getNome(), "Cristian");
		assertEquals(car.getCarrinho().getUsuario().getEmail(), "cristian@teste.com");
		assertEquals(car.getCarrinho().getItens().get(0).getNome(), "Lampada");
		assertEquals(250.46, car.getValorTotal(), 0.000001);

	}

}
