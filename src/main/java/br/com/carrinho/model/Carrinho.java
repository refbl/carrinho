package br.com.carrinho.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode @ToString
public class Carrinho {

    //@Id
    //public String id;
    
	@Id @Getter @Setter
    public Usuario usuario;

    @Getter @Setter
    public List<Item> itens;

    public Carrinho() {}

    public Carrinho(Usuario usuario, Item item) {
        this.usuario = usuario;
        this.addItem(item);
    }

    @Override
    public String toString() {
        return String.format(
                "Carrinho[Usuario=%s, Itens='%s']",
                this.usuario, this.itens);
    }

	public void addItem(Item item) {
		// TODO Auto-generated method stub
		if (this.itens == null) this.itens = new ArrayList<Item>();
        this.itens.add(item);
	}

}