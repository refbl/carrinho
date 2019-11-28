package br.com.carrinho.model;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public class Item {

    @Id  @Getter @Setter
    private String id;

    @Getter @Setter
    private String nome;
    
    @Getter @Setter
    private Double valor;

    public Item() {}

    public Item(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format(
                "Item[id=%s, Nome='%s', Valor='%s']",
                this.id, this.nome, this.valor);
    }

}
