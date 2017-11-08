package com.diegodossantos95.dicionario;

import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
		Tradutor tra = new Tradutor();
		tra.carregaDicionario("dicionario.dat");
		System.out.println(tra.traduzPalavra("palavra"));
		
		LinkedList<String> list = new LinkedList<String>();
		list.add("traducao111");
		tra.insereTraducao("palavra322", list);
		System.out.println(tra.traduzPalavra("palavra322"));
	}
}
