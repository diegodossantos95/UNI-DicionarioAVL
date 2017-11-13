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


/**
 * Classe da interface, não tem documentação pois não é importante para o exercicio
 * @author i848202
 *
 */
public class Janela extends JFrame {
	private Tradutor tradutor = new Tradutor();
	private JPanel panel = new JPanel();
	private JPanel panelPesquisar = new JPanel();
	private JPanel panelResultado = new JPanel();
	private JButton pesquisar;
	
	public Janela() throws HeadlessException {
		super("UNI-DicionarioAVL");
		this.setLayout();
		this.initTradutor();
		this.initSalvar();
		this.initTraducao();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
	}
	
	public void showJanela() {
		this.setSize (640, 380);
		this.setVisible(true);
	}
	
	private void setLayout(){
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(panelPesquisar);
		panel.add(panelResultado);
	}
	
	private void initTradutor() {
		tradutor.carregaDicionario("dicionario.dat");
	}
	
	private void initTraducao() {
		JLabel label = new JLabel("Pesquisar por:");
		panelPesquisar.add(label);
		
		JTextField field = new JTextField(20);
		panelPesquisar.add(field);
		
		JLabel label2 = new JLabel("Resultados:");
		label2.setVisible(false);
		panelResultado.add(label2);
		
		JList<String> list = new JList<String>();
		panelResultado.add(list);
		
		pesquisar = new JButton("Pesquisar");
		ActionListener pesquisarListener = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  String palavra = field.getText();
			  if(palavra.isEmpty()){
				  return;
			  }
			  
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
		panelPesquisar.add(pesquisar);
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
		panel.add(salvar);
	}
	
	private void showAddDicionario(String palavra) {
		String definicoes = JOptionPane.showInputDialog(this, "Palavra n�o encontrada, digite as tradu��es separado por virgula");
		if(definicoes != null){
			List<String> list = Arrays.asList(definicoes.split(","));
			tradutor.insereTraducao(palavra, list);
			pesquisar.doClick();
			JOptionPane.showMessageDialog(this, "Tradu��o adicionada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
