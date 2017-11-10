package main.java.diegodossantos95.tradutor;

import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
		Tradutor tra = new Tradutor();
		tra.carregaDicionario("dicionario.dat");
		//System.out.println(tra.traduzPalavra("palavra"));
		
		LinkedList<String> list = new LinkedList<String>();
		list.add("traducao14");
		tra.insereTraducao("palavra4", list);
		System.out.println(tra.traduzPalavra("palavra4"));
		
		tra.salvaDicionario("dicionario2.txt");
	}
}
