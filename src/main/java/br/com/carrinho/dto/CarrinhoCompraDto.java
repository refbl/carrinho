package br.com.carrinho.dto;

import java.util.List;

import br.com.carrinho.model.Carrinho;
import lombok.Getter;
import lombok.Setter;

public class CarrinhoCompraDto {
	
	@Getter @Setter
	private Carrinho carrinho;
	
	@Getter @Setter
	private List<ItemCompradoDto> itensComprados;

	@Getter @Setter
	private Double valorTotal;
	
    @Override
    public String toString() {
        return String.format(
                "CarrinhoCompraDto[Carrinho=%s, valorTotal='%s']",
                this.carrinho, this.valorTotal);
    }

}
