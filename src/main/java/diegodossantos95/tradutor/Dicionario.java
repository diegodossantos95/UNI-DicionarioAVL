package main.java.diegodossantos95.tradutor;

import java.text.Collator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

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
		Collections.sort(this.definicoes);
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
	
	public Integer compararPalavra(String comparador){
		/*
		 * Compara as palavras ignorando acentos e case
		 * Retorna 0 se for igual
		 * Retorna -1 se o comparador for menor que base
		 * Retorna 1 se o comparador for maior que base
		 */
		Collator collator = Collator.getInstance(Locale.US);
        collator.setStrength(Collator.PRIMARY);
        
        return collator.compare(comparador, this.palavra);
	}
	
	public void mergeDefinicoes(LinkedList<String> novasDefinicoes) {
		if(novasDefinicoes == null || novasDefinicoes.isEmpty()) {
			return;
		}
		Set<String> temp = new TreeSet<String>(this.definicoes);
		temp.addAll(novasDefinicoes);
		this.definicoes = new LinkedList<String>(temp);
	}
}
