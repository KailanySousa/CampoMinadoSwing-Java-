package br.com.ksg.cm.view;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.ksg.cm.model.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {

	public PainelTabuleiro(Tabuleiro tabuleiro) {
		
		setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));
		
		tabuleiro.getCampos().stream().forEach(c -> add(new BotaoCampo(c)));
		
		tabuleiro.registerObserver(e -> {
			
			// o código só será executado depois que a tela for atualizada
			SwingUtilities.invokeLater(() -> {
				if(e.isGanhou()) {
					JOptionPane.showMessageDialog(this, "Ganhou :)", "Resultado", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Perdeu :(", "Resultado", JOptionPane.PLAIN_MESSAGE);
				}
				
				tabuleiro.reiniciar();
			});
			
		});
	}

}
