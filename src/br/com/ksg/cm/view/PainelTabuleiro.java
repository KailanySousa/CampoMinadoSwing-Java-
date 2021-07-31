package br.com.ksg.cm.view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import br.com.ksg.cm.model.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {

	public PainelTabuleiro(Tabuleiro tabuleiro) {
		
		setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));
		
		tabuleiro.getCampos().stream().forEach(c -> add(new BotaoCampo(c)));
		
		tabuleiro.registerObserver(e -> {
			// TODO mostrar resultado para o usuário
		});
	}

}
