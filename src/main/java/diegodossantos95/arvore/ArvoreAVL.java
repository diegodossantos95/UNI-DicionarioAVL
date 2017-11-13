package main.java.diegodossantos95.arvore;

import java.util.ArrayList;
import java.util.List;

import main.java.diegodossantos95.tradutor.Dicionario;

/**
 * Classe da Arvore AVL
 * @author i848202
 *
 */
public class ArvoreAVL {
	/**
	 * Atributo privado da raiz da arvore
	 */
	private Dicionario raiz;
	
	/**
	 * Metodo responsavel por adicionar um novo nodo a arvore
	 * Caso a raiz estja vazia, o nodo novo vira a raiz
	 * Caso contrario, é utilizado um metodo auxiliar para adicionar o novo nodo
	 * @param dicionario
	 */
	public void adicionar(Dicionario dicionario){
		if(raiz == null){
			raiz = dicionario;
		}else{
			this.adicionar(this.raiz, dicionario);
		}
	}
	
	/**
	 * Metodo responsavel por procurar a palavra (key)
	 * na arvore AVL
	 * Utiliza o algoritmo de busca, considerando 
	 * que a arvore esteja ordenada
	 * @param palavra
	 * @return
	 */
	public Dicionario procurarPelaPalavra(String palavra){
		Dicionario temp = this.raiz;
		
		while(temp != null) {
			if(temp.compararPalavra(palavra) == 0) { 
				//Palavra encontrada
				return temp;
			} else if(temp.compararPalavra(palavra) < 0) {
				/*
				 * Palavra é menor que o nodo atual (temp), 
				 * pegar nodo da esquerda = menor
				 */
				temp = temp.getEsquerda();
			} else if(temp.compararPalavra(palavra) > 0) {
				/*
				 * Palavra é maior que o nodo atual (temp), 
				 * pegar nodo da direita = maior
				 */
				temp = temp.getDireita();
			}
		}
		
		return null;
	}
	
	/**
	 * Metodo responsavel por retornar o conteudo da arvore.
	 * Utiliza um metodo auxiliar para retornar o conteudo em uma lista.
	 * @return
	 */
	public List<Dicionario> getConteudo(){
		List<Dicionario> lista = new ArrayList<Dicionario>();
		this.percorrerEmOrdem(this.raiz, lista);
		return lista;
	}
	
    //--------------METODOS PRIVADOS-----------------------
	/**
	 * Metodo recursivo que percorre a lista em ordem
	 * @param raiz
	 * @param lista
	 */
	private void percorrerEmOrdem(Dicionario raiz, List<Dicionario> lista){
		if(raiz == null){
			return;
		}
		this.percorrerEmOrdem(raiz.getEsquerda(), lista);
		lista.add(raiz);
		this.percorrerEmOrdem(raiz.getDireita(), lista);
	}
	
	/**
	 * Metodo recursivo responsavel por adicionar um nodo a arvore
	 * Recebe como parametro o nodo atual e o nodo a adicionar
	 * Faz as comparacoes necessarias entre os nodos e adicona no local adequado
	 * @param atual
	 * @param adicionar
	 */
	private void adicionar(Dicionario atual, Dicionario adicionar){
		if(atual.compararPalavra(adicionar.getPalavra()) < 0){ 
			/*
			 * O nodo a adicionar é menor que o nodo atual (esquerda)
			 */
			if(atual.getEsquerda() == null){
				atual.setEsquerda(adicionar);
				adicionar.setPai(atual);
				this.verificarBalanceamento(adicionar);
			}else{
				this.adicionar(atual.getEsquerda(), adicionar);
			}
		}else if(atual.compararPalavra(adicionar.getPalavra()) > 0){
			/*
			 * O nodo a adicionar é maior que o nodo atual (direita)
			 */
			if(atual.getDireita() == null){
				atual.setDireita(adicionar);
				adicionar.setPai(atual);
				this.verificarBalanceamento(adicionar);
			}else{
				this.adicionar(atual.getDireita(), adicionar);
			}
		}else if(atual.compararPalavra(adicionar.getPalavra()) == 0) {
			/*
			 * O nodo é igual ao atual, fazer o merge das definicoes
			 */
			atual.mergeDefinicoes(adicionar.getDefinicoes());
		}
	}
	
	/**
	 * Metodo responsavel por verificar o balanceamento do nodo atual
	 * Recebe como parametro o nodo para verificar balanceamento
	 * @param atual
	 */
	private void verificarBalanceamento(Dicionario atual){
		int balanceamento = atual.getBalanceamento();
		
		/*
		 * Balanceamento igual a -2 ou 2, signifca que a arvore esta desbalanceada
		 */
		if(balanceamento == -2) {
			/*
			 *  Verifica o balanceamento da subarvore da direita, 
			 *  caso negativo executa uma rotacao simples a esquerda
			 *  Caso positivo executa uma rotacao dupla a esquerda
			 */
			if(atual.getDireita().getBalanceamento() <= 0) {
				this.rotacaoEsquerda(atual);
			} else {
				this.rotacaoDuplaEsquerda(atual);
			}
		}else if(balanceamento == 2) {
			/*
			 *  Verifica o balanceamento da subarvore da esquerda, 
			 *  caso positivo executa uma rotacao simples a direita
			 *  Caso negativo executa uma rotacao dupla a direita
			 */
			if(atual.getEsquerda().getBalanceamento() >= 0) {
				this.rotacaoDireita(atual);
			} else {
				this.rotacaoDuplaDireita(atual);
			}
		}
		
		if(atual.getPai() != null) {
			/*
			 * Verifica o balanceamento do pai do nodo atual
			 */
			this.verificarBalanceamento(atual.getPai());
		} else {
			/*
			 * Caso não tenha pai, após o balanceamento e possiveis rotacoes, 
			 * o nodo atual vira a raiz
			 */
			this.raiz = atual;
		}
	}
	
	/**
	 * Metodo que executa a rotacao a esquerda
	 * Recebe como parametro o nodo para executar a rotacao
	 * @param atual
	 */
	private void rotacaoEsquerda(Dicionario atual) {
		Dicionario direita = atual.getDireita();
		direita.setPai(atual.getPai());

		atual.setDireita(direita.getEsquerda());

		if (atual.getDireita() != null) {
			atual.getDireita().setPai(atual);
		}

		direita.setEsquerda(atual);
		atual.setPai(direita);

		if (direita.getPai() != null) {
			if (direita.getPai().getDireita() == atual) {
				direita.getPai().setDireita(direita);
			} else if (direita.getPai().getEsquerda() == atual) {
				direita.getPai().setEsquerda(direita);
			}
		}
	}
	
	/**
	 * Metodo que executa a rotacao a direita
	 * Recebe como parametro o nodo para executar a rotacao
	 * @param atual
	 */
	private void rotacaoDireita(Dicionario atual) {
		Dicionario esquerda = atual.getEsquerda();
		esquerda.setPai(atual.getPai());

		atual.setEsquerda(esquerda.getDireita());

		if (atual.getEsquerda() != null) {
			atual.getEsquerda().setPai(atual);
		}

		esquerda.setDireita(atual);
		atual.setPai(esquerda);

		if (esquerda.getPai() != null) {
			if (esquerda.getPai().getDireita() == atual) {
				esquerda.getPai().setDireita(esquerda);
			} else if (esquerda.getPai().getEsquerda() == atual) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}
	}
	
	/**
	 * Metodo que executa a rotacao dupla a esquerda
	 * Recebe como parametro o nodo para executar a rotacao
	 * @param atual
	 */
	private void rotacaoDuplaEsquerda(Dicionario atual) {
		this.rotacaoDireita(atual.getDireita());
		this.rotacaoEsquerda(atual);
	}
	
	/**
	 * Metodo que executa a rotacao dupla a direita
	 * Recebe como parametro o nodo para executar a rotacao
	 * @param atual
	 */
	private void rotacaoDuplaDireita(Dicionario atual) {
		this.rotacaoEsquerda(atual.getEsquerda());
		this.rotacaoDireita(atual);
	}
}
