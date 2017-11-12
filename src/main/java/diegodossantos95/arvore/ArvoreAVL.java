package main.java.diegodossantos95.arvore;

import java.util.ArrayList;
import java.util.List;

import main.java.diegodossantos95.tradutor.Dicionario;

public class ArvoreAVL {
	//TODO: Documentacao
	private Dicionario raiz;
	
	public void adicionar(Dicionario dicionario){
		if(raiz == null){
			raiz = dicionario;
		}else{
			this.adicionar(this.raiz, dicionario);
		}
	}
	
	public Dicionario procurarPelaPalavra(String palavra){
		Dicionario temp = this.raiz;
		
		while(temp != null) {
			if(temp.compararPalavra(palavra) == 0) {
				return temp;
			} else if(temp.compararPalavra(palavra) < 0) { //Esquerda = menor
				temp = temp.getEsquerda();
			} else if(temp.compararPalavra(palavra) > 0) { //Direita = maior
				temp = temp.getDireita();
			}
		}
		
		return null;
	}
	
	public List<Dicionario> getConteudo(){
		List<Dicionario> lista = new ArrayList<Dicionario>();
		this.percorrerEmOrdem(this.raiz, lista);
		return lista;
	}
	
	/*
	 * Metodos privados
	 */
	private void percorrerEmOrdem(Dicionario raiz, List<Dicionario> lista){
		if(raiz == null){
			return;
		}
		this.percorrerEmOrdem(raiz.getEsquerda(), lista);
		lista.add(raiz);
		this.percorrerEmOrdem(raiz.getDireita(), lista);
	}
	
	private void adicionar(Dicionario atual, Dicionario adicionar){
		if(atual.compararPalavra(adicionar.getPalavra()) < 0){ //Esquerda
			if(atual.getEsquerda() == null){
				atual.setEsquerda(adicionar);
				adicionar.setPai(atual);
				this.verificarBalanceamento(adicionar);
			}else{
				this.adicionar(atual.getEsquerda(), adicionar);
			}
		}else if(atual.compararPalavra(adicionar.getPalavra()) > 0){ //Direita
			if(atual.getDireita() == null){
				atual.setDireita(adicionar);
				adicionar.setPai(atual);
				this.verificarBalanceamento(adicionar);
			}else{
				this.adicionar(atual.getDireita(), adicionar);
			}
		}else if(atual.compararPalavra(adicionar.getPalavra()) == 0) {
			atual.mergeDefinicoes(adicionar.getDefinicoes());
		}
	}
	
	private void verificarBalanceamento(Dicionario atual){
		int balanceamento = atual.getBalanceamento();
		
		if(balanceamento == -2) { 
			if(atual.getDireita().getBalanceamento() <= 0) {
				this.rotacaoEsquerda(atual);
			} else {
				this.rotacaoDuplaEsquerda(atual);
			}
		}else if(balanceamento == 2) {
			if(atual.getEsquerda().getBalanceamento() >= 0) {
				this.rotacaoDireita(atual);
			} else {
				this.rotacaoDuplaDireita(atual);
			}
		}
		
		if(atual.getPai() != null) {
			this.verificarBalanceamento(atual.getPai());
		} else {
			this.raiz = atual;
		}
	}
	
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
	
	private void rotacaoDuplaEsquerda(Dicionario atual) {
		this.rotacaoDireita(atual.getDireita());
		this.rotacaoEsquerda(atual);
	}
	
	private void rotacaoDuplaDireita(Dicionario atual) {
		this.rotacaoEsquerda(atual.getEsquerda());
		this.rotacaoDireita(atual);
	}
}
