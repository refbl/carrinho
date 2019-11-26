package br.com.carrinho.dto;

import lombok.Getter;
import lombok.Setter;

public class UsuarioDto {
	
	 @Getter @Setter	
	 private String id;
	 
	 @Getter @Setter
	 private String nome;
	 
	 @Getter @Setter
	 private String email;
	 
	 @Getter @Setter
	 private String mensagem;
}
