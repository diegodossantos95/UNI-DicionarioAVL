package main.java.diegodossantos95.main;

import java.util.LinkedList;

import main.java.diegodossantos95.tradutor.Tradutor;

public class Main {
	public static void main(String[] args) {
        Tradutor tra = new Tradutor();
        tra.carregaDicionario("dicionario.dat");
        //System.out.println(tra.traduzPalavra("palavra"));
        
        LinkedList<String> list = new LinkedList<String>();
        list.add("traducao124");
        tra.insereTraducao("palavra", list);
        System.out.println(tra.traduzPalavra("palavra"));
        
        //tra.salvaDicionario("dicionario2.txt");
	}
}
