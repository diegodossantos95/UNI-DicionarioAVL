package com.diegodossantos95.dicionario;

public class ArvoreAVL {
	private Dicionario raiz;
	
	public void adicionar(Dicionario dicionario){
		if(raiz == null){
			raiz = dicionario;
		}
		
		//TODO: adicionar quando nao for na raiz...
	}
	
	public Dicionario procurarPelaPalavra(String palavra){
		return raiz;
		
		//TODO: procurar o dicinario na arvore pela chave(palavra)
	}
}
