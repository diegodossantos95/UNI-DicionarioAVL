package main.java.diegodossantos95.tradutor;

import java.text.Collator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class Dicionario {
	/**
	 * Propriedades utilizadas para o 
	 * armazenamento da palavra e das definicoes
	 */
	private String palavra; 
	private LinkedList<String> definicoes;
	
	/**
	 * Propriedades utilizadas para o 
	 * controle de nós da arvore AVL
	 */
	private Dicionario pai;
	private Dicionario esquerda;
	private Dicionario direita;
	
	/**
	 * Construtor da classe Dicionario
	 * Recebe como parametro a palavra e as definicoes
	 * Executa um sort das definicoes após adicionar elas na instancia
	 * @param palavra
	 * @param definicoes
	 */
	public Dicionario(String palavra, List<String> definicoes) {
		super();
		this.palavra = palavra;
		this.definicoes = new LinkedList<String>(definicoes);
		Collections.sort(this.definicoes);
	}
	
	/**
	 * Getters and setters
	 */
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
	
	public LinkedList<String> getDefinicoes(){
		return this.definicoes;
	}
	
	public String getPalavra() {
		return palavra;
	}
	
	/**
	 * Metodo utilizado para transformar a 
	 * instancia Dicionario em String
	 */
	@Override
	public String toString() {
		LinkedList<String> conteudo = this.definicoes;
		conteudo.add(0, this.palavra);
		return String.join("#", conteudo);
	}
	
	/**
	 * Compara as palavras ignorando acentos e case
	 * Retorna 0 se for igual
	 * Retorna -1 se o comparador for menor que base
	 * Retorna 1 se o comparador for maior que base
	 * @param comparador
	 * @return
	 */
	public Integer compararPalavra(String comparador){
		Collator collator = Collator.getInstance(Locale.US);
        collator.setStrength(Collator.PRIMARY);
        
        return collator.compare(comparador, this.palavra);
	}
	
	/**
	 * Método para fazer o merge das definicoes
	 * Recebe como parametro uma linkedlist de definicoes
	 * E faz o merge, mantendo o sorting, com as definicoes da instancia
	 * @param novasDefinicoes
	 */
	public void mergeDefinicoes(LinkedList<String> novasDefinicoes) {
		if(novasDefinicoes == null || novasDefinicoes.isEmpty()) {
			return;
		}
		Set<String> temp = new TreeSet<String>(this.definicoes);
		temp.addAll(novasDefinicoes);
		this.definicoes = new LinkedList<String>(temp);
	}
	
	/**
	 * Metodo recursivo para calcular a 
	 * altura das subarvores deste nodo
	 * Retorna o valor em um int
	 * @return
	 */
	public int getAltura() {
		if (this.getEsquerda() == null && this.getDireita() == null) {
			return 0;
		
		} else if (this.getEsquerda() == null) {
			return 1 + this.getDireita().getAltura();
		
		} else if (this.getDireita() == null) {
			return 1 + this.getEsquerda().getAltura();
		
		} else {
			return 1 + Math.max(this.getEsquerda().getAltura(), this.getDireita().getAltura());
		}
	}
	
	/**
	 * Calcula o balanceamento do nodo
	 * Retorna o valor em um int
	 * @return
	 */
	public int getBalanceamento() {
		int hDireita = 0;
		int hEsquerda = 0;
		
		if(this.getDireita() != null){
			hDireita = this.getDireita().getAltura();
		}
		if(this.getEsquerda() != null) {
			hEsquerda = this.getEsquerda().getAltura();
		}
		
		return hEsquerda - hDireita;
	}
}
