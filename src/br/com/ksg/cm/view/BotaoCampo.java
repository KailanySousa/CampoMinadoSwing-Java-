package br.com.ksg.cm.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.ksg.cm.event.CampoEvent;
import br.com.ksg.cm.model.Campo;
import br.com.ksg.cm.model.Cores;
import br.com.ksg.cm.observer.CampoObserver;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObserver, MouseListener {

	private Campo campo;
	private final Cores cores = new Cores();

	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBorder(BorderFactory.createBevelBorder(0));
		setBackground(this.cores.getPadrao());
		setOpaque(true);
		
		this.addMouseListener(this);
		this.campo.registerObserver(this);
	}

	@Override
	public void eventEmit(Campo campo, CampoEvent event) {
		switch (event) {
		case ABRIR:
			this.aplicarEstiloAbrir();
			break;
		case MARCAR:
			this.aplicarEstiloMarcar();
			break;
		case EXPLODIR:
			this.aplicarEstiloExplodir();
			break;
		default:
			this.aplicarEstiloPadrao();
			break;
		}
	}

	private void aplicarEstiloPadrao() {
		setBackground(this.cores.getPadrao());
		setText("");
	}

	private void aplicarEstiloAbrir() {
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		if(this.campo.isMinado()) {
			this.aplicarEstiloExplodir();
			return;
		}
		
		setBackground(this.cores.getPadrao());

		switch (this.campo.minasNaVizinha()) {
		case 1:
			setForeground(this.cores.getTextoVerde());
			break;
		case 2:
			setForeground(Color.BLUE);
			break;
		case 3:
			setForeground(Color.YELLOW);
			break;
		case 4:
		case 5:
		case 6:
			setForeground(Color.RED);
			break;
		default:
			setForeground(Color.PINK);
		}

		String valor = !this.campo.vizinhancaSegura() ? this.campo.minasNaVizinha() + "" : "";
		setText(valor);

	}

	private void aplicarEstiloMarcar() {
		setBackground(this.cores.getMarcar());
		setForeground(Color.BLACK);
		setText("M");
	}

	private void aplicarEstiloExplodir() {
		setBackground(this.cores.getExplodir());
		setForeground(Color.WHITE);
		setText("X");
	}

	@Override
	public void mousePressed(MouseEvent e) {

		// e.getButton == 1 -> botão esquerdo
		if (e.getButton() == 1) {
			this.campo.abrir();
		} else {
			this.campo.setMarcado();
		}

	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
