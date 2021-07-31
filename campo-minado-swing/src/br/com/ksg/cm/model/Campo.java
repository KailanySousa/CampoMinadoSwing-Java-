package br.com.ksg.cm.model;

import java.util.ArrayList;
import java.util.List;

import br.com.ksg.cm.event.CampoEvent;
import br.com.ksg.cm.observer.CampoObserver;

public class Campo {

	private final int linha;
	private final int coluna;

	// identifica se o campo está com uma mina ou não
	private boolean minado;

	// identifica se o campo está aberto ou não
	private boolean aberto;

	// identifica se o campo está marcado ou não
	private boolean marcado;

	private List<Campo> vizinhos = new ArrayList<>();
	private List<CampoObserver> observers = new ArrayList<>();

	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public void registerObserver(CampoObserver observer) {
		this.observers.add(observer);
	}
	
	private void notifyObservers(CampoEvent event) {
		this.observers.stream().forEach(o ->o.eventEmit(this, event));
	}

	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = this.linha != vizinho.linha;
		boolean colunaDiferente = this.coluna != vizinho.coluna;

		/*
		 * se o vizinho estiver em uma linha e coluna diferente significa que está em
		 * uma das diagonais
		 */

		boolean diagonal = linhaDiferente && colunaDiferente;

		int deltaLinha = Math.abs(this.linha - vizinho.linha);
		int deltaColuna = Math.abs(this.coluna - vizinho.coluna);

		int deltaGeral = deltaColuna + deltaLinha;

		/*
		 * só será possível adicionar um vizinho, caso ele não esteja em uma diagonal e
		 * a diferença entre a linha e/ou coluna seja de 1
		 *
		 * ou se ele estiver na diagonal e a diferença for de 2
		 */

		if (deltaGeral == 1 && !diagonal) {
			this.vizinhos.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			this.vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}

	}
	
	public void setMarcado() {
		if(!this.aberto) {
			this.marcado = !this.marcado;
			
			if(this.marcado) {
				this.notifyObservers(CampoEvent.MARCAR);
			} else {
				this.notifyObservers(CampoEvent.DESMARCAR);
			}
		}
	}
	
	public boolean abrir() {
		if(!this.aberto && !this.marcado) {
			this.aberto = true;
			
			if(this.minado) {
				this.notifyObservers(CampoEvent.EXPLODIR);
			} else {
				this.setAberto(true);
				if(this.vizinhancaSegura()) vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	public boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	public boolean isMinado() {
		return this.minado;
	}
	
	public boolean isMarcado() {
		return this.marcado;
	}
	
	void setAberto(boolean aberto) {
		this.aberto = aberto;
		if(this.aberto) this.notifyObservers(CampoEvent.ABRIR);
	}
	
	public boolean isAberto() {
		return this.aberto;
	}
	
	public boolean isFechado() {
		return !this.aberto;
	}
	
	void minar() {
		this.minado = true;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	boolean objetivoAlcancado(){
		boolean desvendado = !this.minado && this.aberto;
		boolean protegido = this.minado && this.marcado;
		
		return desvendado || protegido;
	}
	
	public int minasNaVizinha() {
		return (int) vizinhos.stream().filter(v -> v.minado).count();
	}
	
	void reiniciar() {
		this.aberto = false;
		this.marcado = false;
		this.minado = false;
		
		this.notifyObservers(CampoEvent.REINICIAR);
	}
	
}
