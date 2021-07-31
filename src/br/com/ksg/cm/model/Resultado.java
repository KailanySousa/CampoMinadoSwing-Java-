package br.com.ksg.cm.model;

public class Resultado {
	
	private final boolean ganhou;
	
	public Resultado(boolean ganhou) {
		this.ganhou = ganhou;
	}
	
	public boolean isGanhou() {
		return this.ganhou;
	}

}
