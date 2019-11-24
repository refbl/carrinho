package br.com.carrinho.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.carrinho.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

    public Item findByNome(String nome);
    public List<Item> findByValor(double valor);

}