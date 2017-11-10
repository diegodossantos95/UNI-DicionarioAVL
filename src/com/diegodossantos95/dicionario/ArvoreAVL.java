package com.diegodossantos95.dicionario;

import java.util.ArrayList;
import java.util.List;

public class ArvoreAVL {
	//TODO: Documentacao

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
	
	public List<Dicionario> getConteudo(){
		List<Dicionario> conteudo = new ArrayList<Dicionario>();
		conteudo.add(raiz);
		return conteudo;
		
		//TODO: percorrer arvore em ordem e retornar conteudo em List
	}
}
