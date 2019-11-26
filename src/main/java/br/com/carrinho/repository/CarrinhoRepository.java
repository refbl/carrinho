package br.com.carrinho.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.carrinho.model.Carrinho;
import br.com.carrinho.model.Usuario;

public interface CarrinhoRepository extends MongoRepository<Carrinho, String> {

    public Carrinho findByUsuario(Usuario usuario);

}