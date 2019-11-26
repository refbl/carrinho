package br.com.carrinho.dto;

import br.com.carrinho.model.Carrinho;
import lombok.Getter;
import lombok.Setter;

public class CarrinhoDto {
	
	@Getter @Setter
	private Carrinho carrinho;

	@Getter @Setter
	private Double valorTotal;
	
    @Override
    public String toString() {
        return String.format(
                "CarrinhoDto[Carrinho=%s, valorTotal='%s']",
                this.carrinho, this.valorTotal);
    }

}
