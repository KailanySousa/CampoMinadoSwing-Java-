package br.com.ksg.cm.model;

import java.awt.Color;

public class Cores {
	
	private Color padrao;
	private Color marcado;
	private Color minado;
	private Color texto;
	
	public Cores() {
		this.setPadrao(new Color(184, 184, 184));
		this.setMarcado(new Color(189, 66, 68));
		this.setMinado(new Color(8, 179, 247));
		this.setTexto(new Color(0, 100, 0));
	}
	
	public Cores(Color padrao, Color marcado, Color minado, Color texto) {
		this.setPadrao(padrao);
		this.setMarcado(marcado);
		this.setMinado(minado);
		this.setTexto(texto);
	}
	
	public Cores(Color padrao, Color marcado, Color minado) {
		this.setPadrao(padrao);
		this.setMarcado(marcado);
		this.setMinado(minado);
		this.setTexto(new Color(0, 100, 0));
	}

	public Color getPadrao() {
		return padrao;
	}

	public void setPadrao(Color padrao) {
		this.padrao = padrao;
	}

	public Color getTexto() {
		return texto;
	}

	public void setTexto(Color texto) {
		this.texto = texto;
	}

	public Color getMarcado() {
		return marcado;
	}

	public void setMarcado(Color marcado) {
		this.marcado = marcado;
	}

	public Color getMinado() {
		return minado;
	}

	public void setMinado(Color minado) {
		this.minado = minado;
	}

}
