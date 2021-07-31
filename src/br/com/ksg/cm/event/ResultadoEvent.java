package br.com.ksg.cm.event;

public class ResultadoEvent {
	
	private final boolean ganhou;
	
	public ResultadoEvent(boolean ganhou) {
		this.ganhou = ganhou;
	}
	
	public boolean isGanhou() {
		return this.ganhou;
	}

}
