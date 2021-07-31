package br.com.ksg.cm.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.ksg.cm.event.CampoEvent;
import br.com.ksg.cm.model.Campo;
import br.com.ksg.cm.model.Cores;
import br.com.ksg.cm.observer.CampoObserver;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObserver {

	private Campo campo;
	private final Cores cores = new Cores();
	
	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBorder(BorderFactory.createBevelBorder(0));
		setBackground(this.cores.getPadrao());
		
		this.campo.registerObserver(this);
	}
	
	@Override
	public void eventEmit(Campo campo, CampoEvent event) {
		switch (event) {
		case ABRIR: {
			this.aplicarEstiloAbrir();
		}
		case MARCAR: {
			this.aplicarEstiloMarcar();
		}
		case EXPLODIR: {
			this.aplicarEstiloExplodir();
		}
		default:
			this.aplicarEstiloPadrao();
		}
	}

	private void aplicarEstiloPadrao() {
		// TODO Auto-generated method stub
		
	}

	private void aplicarEstiloAbrir() {
		// TODO Auto-generated method stub
		
	}
	
	private void aplicarEstiloMarcar() {
		// TODO Auto-generated method stub
		
	}
	
	private void aplicarEstiloExplodir() {
		// TODO Auto-generated method stub
		
	}

}
