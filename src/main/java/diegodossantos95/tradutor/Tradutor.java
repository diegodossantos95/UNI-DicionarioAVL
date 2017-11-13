package main.java.diegodossantos95.tradutor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.java.diegodossantos95.arvore.ArvoreAVL;

public class Tradutor {
	/**
	 * Referencia a arvore utilizada
	 */
	private ArvoreAVL arvore;
	
	/**
	 * Metodo para carregar o arquivo do 
	 * dicionario para a Arvore AVL
	 * @param arquivo
	 */
	public void carregaDicionario(String arquivo) {
		try (Stream<String> stream = Files.lines(Paths.get(arquivo))) {
			this.arvore = new ArvoreAVL(); 
			stream.forEach(this::parseLinhaToDicionario);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para traduzir uma unica palavra.
	 * Recebe como parametro a palavra a ser traduzida
	 * e retorna a lista das possiveis traducoes
	 * para esta palavra, ou vazio caso a palavra
	 * nao tenha sido encontrada
	 * @param palavra
	 * @return
	 */
	public LinkedList<String> traduzPalavra(String palavra) {
		try{
			Dicionario dicionario = this.arvore.procurarPelaPalavra(palavra);		
			return dicionario.getDefinicoes(); 
		}catch(Exception e){
			e.printStackTrace();
			return new LinkedList<String>();
		}
	}

	/**
	 * Metodo para inserir uma nova definicao no dicionario.
	 * Recebe como parametro a palavra em ingles
	 * e a lista de possiveis traducoes
	 * @param palavra
	 * @param definicoes
	 */
	public void insereTraducao(String palavra, List<String> definicoes) {
		try{
			this.adicionarDicionario(palavra, definicoes);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para salvar o arquivo do dicionario
	 * com as respectivas definicoes baseado no conteudo da arvore AVL
	 * @param arquivo
	 */
	public void salvaDicionario(String arquivo) {
		try {
			List<Dicionario> dicionarios = this.arvore.getConteudo();
			String conteudo = dicionarios.stream()
				  .map(Dicionario::toString)
				  .collect(Collectors.joining(System.lineSeparator()));
			Files.write(Paths.get(arquivo), conteudo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Metodos privados
	 */
	/**
	 * Metodo para parsear a linha do arquivo 
	 * em instancias da classe Dicionario
	 * @param linha
	 */
	private void parseLinhaToDicionario(String linha){
		LinkedList<String> palavras = this.parseLinhaToList(linha);
		this.adicionarDicionario(palavras.pop(), palavras);
	}
	
	/**
	 * Metodo para criar uma instancia de Dicionario 
	 * e adicionar na Arvore AVL
	 * @param palavra
	 * @param definicoes
	 */
	private void adicionarDicionario(String palavra, List<String> definicoes){
		Dicionario dicionario = new Dicionario(palavra, definicoes);
		this.arvore.adicionar(dicionario);
	}
	
	/**
	 * Metodo para transformar uma String 
	 * e uma linkedlist de String.
	 * É utilizado o caracter # como separador na String.
	 * @param linha
	 * @return
	 */
	private LinkedList<String> parseLinhaToList(String linha){
		LinkedList<String> palavras = new LinkedList<String>(Arrays.asList(linha.split("#")));
		return palavras;
	}
}
