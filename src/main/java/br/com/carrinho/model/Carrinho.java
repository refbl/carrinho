package br.com.carrinho.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public class Carrinho {

    
	@Id @Getter @Setter
	private String id;
	
	@Getter @Setter
	private Usuario usuario;

    @Getter @Setter
    private List<Item> itens;

    public Carrinho() {}

    public Carrinho(Usuario usuario, Item item) {
        this.usuario = usuario;
        if (item != null) this.addItem(item);
    }

    @Override
    public String toString() {
        return String.format(
                "Carrinho[Usuario=%s, Itens='%s']",
                this.usuario, this.itens);
    }

	public void addItem(Item item) {
		if (this.itens == null) this.itens = new ArrayList<Item>();
        this.itens.add(item);
	}
	
	public void removeItem(Item item) {
		if (this.itens != null) this.itens.remove(item);
	}

}