package br.com.carrinho.model;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public class Usuario {

    @Id @Getter @Setter
    private String id;

    @Getter @Setter 
    private String nome;
    
    @Getter @Setter 
    private String email;

    public Usuario() {}

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "Usuario[id=%s, Nome='%s', Email='%s']",
                this.id, this.nome, this.email);
    }

}