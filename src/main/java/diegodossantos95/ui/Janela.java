package main.java.diegodossantos95.ui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.diegodossantos95.tradutor.Tradutor;

public class Janela extends JFrame {
	private Tradutor tradutor = new Tradutor();
	private JPanel panel1 = new JPanel();
	private JButton pesquisar;
	
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
		
		JLabel label2 = new JLabel("Resultados:");
		panel1.add(label2);
		
		JList<String> list = new JList<String>();
		panel1.add(list);
		
		pesquisar = new JButton("Pesquisar");
		ActionListener pesquisarListener = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  String palavra = field.getText();
			  LinkedList<String> definicoes = tradutor.traduzPalavra(palavra);
			  String[] array = definicoes.toArray(new String[definicoes.size()]);
			  list.setListData(array);
			  
			  if(definicoes.size() > 0) {
				  label2.setVisible(true);
			  }else{
				  label2.setVisible(false);
				  showAddDicionario(palavra);
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
		    JOptionPane.showMessageDialog(null, "Dicionario salvo com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		  }
		};
		salvar.addActionListener(salvarListener);
		panel1.add(salvar);
	}
	
	private void showAddDicionario(String palavra) {
		String definicoes = JOptionPane.showInputDialog(this, "Palavra não encontrada, digite as traduções separado por virgula");
		List<String> list = Arrays.asList(definicoes.split(","));
		tradutor.insereTraducao(palavra, list);
		pesquisar.doClick();
		JOptionPane.showMessageDialog(this, "Tradução adicionada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	}
}
