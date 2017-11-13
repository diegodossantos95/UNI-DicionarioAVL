package main.java.diegodossantos95.ui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.diegodossantos95.tradutor.Tradutor;

public class Janela extends JFrame {
	private Tradutor tradutor = new Tradutor();
	private JPanel panel1 = new JPanel();
	
	public Janela() throws HeadlessException {
		super("UNI-DicionarioAVL");
		this.initTradutor();
		this.initTraducao();
		this.initSalvar();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
		this.add(panel1);
	}
	
	public void showJanela() {
		this.setSize (640, 480);
		this.setVisible(true);
	}
	
	private void initTradutor() {
		tradutor.carregaDicionario("dicionario.dat");
	}
	
	private void initTraducao() {
		JLabel label = new JLabel("Pesquisar por:");
		panel1.add(label);
		
		JTextField field = new JTextField();
		panel1.add(field);
		
		JLabel label2 = new JLabel();
		panel1.add(label2);
		
		JList<String> list = new JList<String>();
		panel1.add(list);
		
		JButton pesquisar = new JButton("Pesquisar");
		ActionListener pesquisarListener = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  String palavra = field.getText();
			  LinkedList<String> definicoes = tradutor.traduzPalavra(palavra);
			  String[] array = definicoes.toArray(new String[definicoes.size()]);
			  list.setListData(array);
			  
			  if(definicoes.size() > 0) {
				  label2.setText("Resultados:");
			  }else{
				  label2.setText("");
			  }
		  }
		};
		pesquisar.addActionListener(pesquisarListener);
		panel1.add(pesquisar);
	}
	
	private void initSalvar() {
		JButton salvar = new JButton("Salvar dicionario");
		ActionListener salvarListener = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    tradutor.salvaDicionario("dicionario.dat");
		  }
		};
		salvar.addActionListener(salvarListener);
		panel1.add(salvar);
	}
}
