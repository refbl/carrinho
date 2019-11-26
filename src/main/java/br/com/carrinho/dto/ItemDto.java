package br.com.carrinho.dto;

import lombok.Getter;
import lombok.Setter;

public class ItemDto {
	 @Getter @Setter	
	 private String id;
	 
	 @Getter @Setter
	 private String nome;
	 
	 @Getter @Setter
	 private double valor;
	 
	 @Getter @Setter
	 private String mensagem;
}
