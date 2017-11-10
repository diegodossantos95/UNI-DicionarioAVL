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

	//TODO: Documentacao
	private ArvoreAVL arvore;
	
	public void carregaDicionario(String arquivo) {
		/*
		 * Carrega o arquivo de dicionario 
		 * (dicionario.dat) para a arvore AVL.
		 */
		try (Stream<String> stream = Files.lines(Paths.get(arquivo))) {
			this.arvore = new ArvoreAVL(); 
			stream.forEach(this::parseLinhaToDicionario);
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
			e.printStackTrace();
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
			this.adicionarDicionario(palavra, definicoes);
		}catch(Exception e){
			//TODO: tratar errors
			e.printStackTrace();
		}
	}

	public void salvaDicionario(String arquivo) {
		/*
		 * Salva o arquivo de dicionario
		 * (dicionario.dat) com as respectivas
		 * definicoes baseado no conteudo da arvore AVL
		 */
		try {
			List<Dicionario> dicionarios = this.arvore.getConteudo();
			String conteudo = dicionarios.stream()
				  .map(Dicionario::toString)
				  .collect(Collectors.joining(System.lineSeparator()));
			Files.write(Paths.get(arquivo), conteudo.getBytes());
		} catch (IOException e) {
			//TODO: tratar errors
			e.printStackTrace();
		}
	}
	
	/*
	 * Metodos privados
	 */
	private void parseLinhaToDicionario(String linha){
		LinkedList<String> palavras = this.parseLinhaToList(linha);
		this.adicionarDicionario(palavras.pop(), palavras);
	}
	
	private void adicionarDicionario(String palavra, List<String> definicoes){
		Dicionario dicionario = new Dicionario(palavra, definicoes);
		this.arvore.adicionar(dicionario);
	}
	
	private LinkedList<String> parseLinhaToList(String linha){
		LinkedList<String> palavras = new LinkedList<String>(Arrays.asList(linha.split("#")));
		return palavras;
	}
}
