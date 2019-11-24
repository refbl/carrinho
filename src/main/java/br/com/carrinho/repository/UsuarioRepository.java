package br.com.carrinho.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.carrinho.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    public Usuario findByNome(String nome);
    public Usuario findByEmail(String email);

}

