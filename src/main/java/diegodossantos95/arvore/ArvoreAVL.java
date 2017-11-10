package main.java.diegodossantos95.arvore;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
		return raiz;
		
		//TODO: procurar o dicinario na arvore pela chave(palavra)
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
	
	private void adicionar(Dicionario raiz, Dicionario adicionar){
		if(this.compararPalavras(adicionar.getPalavra(), raiz.getPalavra()) < 0){ //Esquerda
			if(raiz.getEsquerda() == null){
				raiz.setEsquerda(adicionar);
				adicionar.setPai(raiz);
				this.verificarBalanceamento(adicionar);
			}else{
				this.adicionar(raiz.getEsquerda(), adicionar);
			}
		}else if(this.compararPalavras(adicionar.getPalavra(), raiz.getPalavra()) > 0){ //Direita
			if(raiz.getDireita() == null){
				raiz.setDireita(adicionar);
				adicionar.setPai(raiz);
				this.verificarBalanceamento(adicionar);
			}else{
				this.adicionar(raiz.getDireita(), adicionar);
			}
		}
	}
	
	private void verificarBalanceamento(Dicionario dicionario){
		//TODO: algoritmo para verificar balanceamento
	}
	
	private Integer compararPalavras(String comparador, String base){
		/*
		 * Compara as palavras ignorando acentos e case
		 * Retorna 0 se for igual
		 * Retorna -1 se o comparador for antes de base
		 * Retorna 1 se o comparador for depois de base
		 */
		Collator collator = Collator.getInstance(Locale.US);
        collator.setStrength(Collator.PRIMARY);
        
        return collator.compare(comparador, base);
	}
}
