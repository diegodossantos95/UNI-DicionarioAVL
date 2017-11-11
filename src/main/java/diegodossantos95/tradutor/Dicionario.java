package main.java.diegodossantos95.tradutor;

import java.util.LinkedList;
import java.util.List;

public class Dicionario {
	//TODO: Documentacao

	private String palavra; 
	private LinkedList<String> definicoes;
	
	// No
	private Dicionario pai;
	private Dicionario esquerda;
	private Dicionario direita;
	
	public Dicionario getPai() {
		return pai;
	}

	public void setPai(Dicionario pai) {
		this.pai = pai;
	}

	public Dicionario getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(Dicionario esquerda) {
		this.esquerda = esquerda;
	}

	public Dicionario getDireita() {
		return direita;
	}

	public void setDireita(Dicionario direita) {
		this.direita = direita;
	}
	
	public Dicionario(String palavra, List<String> definicoes) {
		super();
		this.palavra = palavra;
		this.definicoes = new LinkedList<String>(definicoes);
	}
	
	public LinkedList<String> getDefinicoes(){
		return this.definicoes;
	}
	
	public String getPalavra() {
		return palavra;
	}

	@Override
	public String toString() {
		LinkedList<String> conteudo = this.definicoes;
		conteudo.add(0, this.palavra);
		return String.join("#", conteudo);
	}
}
