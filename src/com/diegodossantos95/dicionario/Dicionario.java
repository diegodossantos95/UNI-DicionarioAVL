package com.diegodossantos95.dicionario;

import java.util.LinkedList;
import java.util.List;

public class Dicionario {
	private String palavra; 
	private LinkedList<String> definicoes;
	
	// No
	private Dicionario pai;
	private Dicionario esquerda;
	private Dicionario direita;
	private int balanceamento;
	
	public Dicionario(String palavra, LinkedList<String> definicoes) {
		super();
		this.palavra = palavra;
		this.definicoes = definicoes;
	}
	
	public Dicionario(String palavra, List<String> definicoes) {
		super();
		this.palavra = palavra;
		this.definicoes = new LinkedList<String>(definicoes);
	}
	
	public LinkedList<String> getDefinicoes(){
		return this.definicoes;
	}
}