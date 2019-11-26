package br.com.carrinho.dto;

import br.com.carrinho.model.Item;
import lombok.Getter;
import lombok.Setter;

public class ItemCompradoDto {
	
	@Getter @Setter
	private Item item;
	@Getter @Setter
	private int  quantidade;
	@Getter @Setter
	private double valorTotal;
	
    @Override
    public String toString() {
        return String.format(
                "ItemComprado[Item=%s, quantidade='%s', ValorTotal='%s']",
                this.item, this.quantidade, this.valorTotal);
    }
}
