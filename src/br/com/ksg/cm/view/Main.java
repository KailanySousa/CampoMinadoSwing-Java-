package br.com.ksg.cm.view;

import javax.swing.JFrame;

import br.com.ksg.cm.model.Tabuleiro;

@SuppressWarnings("serial")
public class Main extends JFrame{
	
	public Main() {
		
		Tabuleiro tabuleiro = new Tabuleiro(16, 30, 50);
		PainelTabuleiro pnl = new PainelTabuleiro(tabuleiro);
		
		add(pnl);
		setTitle("Campo Minado");
		setSize(690, 438);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		
		new Main();

	}

}
