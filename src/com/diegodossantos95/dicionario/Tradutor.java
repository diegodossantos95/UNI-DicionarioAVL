package com.diegodossantos95.dicionario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Tradutor {

	private ArvoreAVL arvore;
	
	public void carregaDicionario(String arquivo) {
		/*
		 * Carrega o arquivo de dicionario 
		 * (dicionario.dat) para a arvore AVL.
		 */
		try (Stream<String> stream = Files.lines(Paths.get(arquivo))) {
			this.arvore = new ArvoreAVL(); 
			stream.forEach(this::carregaArvore);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> traduzPalavra(String palavra) {
		/*
		 * Traduz uma unica palavra. Este metodo
		 * recebe como parametro a palavra a ser
		 * traduzida e retorna a lista das possiveis
		 * traducoes para esta palavra.
		 */
		try{
			Dicionario dicionario = this.arvore.procurarPelaPalavra(palavra);		
			return dicionario.getDefinicoes(); 
		}catch(Exception e){
			//TODO: tratar errors
			return new LinkedList<String>();
		}
	}

	public void insereTraducao(String palavra, List<String> definicoes) {
		/*
		 * Insere uma nova definicao no dicionario.
		 * Recebe como parametro a palavra em
		 * ingles e lista de possiveis traducoes.
		 */
		try{
			Dicionario dicionario = new Dicionario(palavra, definicoes);
			this.arvore.adicionar(dicionario);
		}catch(Exception e){
			//TODO: tratar errors
		}
	}

	public void salvaDicionario(String arq) {
		/*
		 * Salva o arquivo de dicionario
		 * (dicionario.dat) com as respectivas
		 * definicoes baseado no conteudo da arvore AVL
		 */
		
		//TODO
	}
	
	/*
	 * Metodos privados
	 */
	private void carregaArvore(String linha){
		Dicionario dicionario = this.criaDicionario(linha);
		this.arvore.adicionar(dicionario);
	}
	
	private Dicionario criaDicionario(String linha){
		LinkedList<String> palavras = new LinkedList<String>(Arrays.asList(linha.split("#")));
		return new Dicionario(palavras.remove(0), palavras);
	}

}