package br.com.ksg.cm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import br.com.ksg.cm.event.CampoEvent;
import br.com.ksg.cm.event.ResultadoEvent;
import br.com.ksg.cm.observer.CampoObserver;

public class Tabuleiro implements CampoObserver {

	private final int linhas;
	private final int colunas;
	private final int minas;

	private final List<Campo> campos = new ArrayList<>();

	private final List<Consumer<ResultadoEvent>> observers = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		this.gerarCampo();
		this.associarVizinhos();
		this.sortearMinas();
	}
	
	public int getLinhas() {
		return this.linhas;
	}

	public int getColunas() {
		return this.colunas;
	}

	public int getMinas() {
		return this.minas;
	}
	
	public List<Campo> getCampos(){
		return this.campos;
	}

	public void registerObserver(Consumer<ResultadoEvent> observer) {
		this.observers.add(observer);
	}

	private void notifyObservers(boolean resultado) {
		this.observers.stream().forEach(o -> o.accept(new ResultadoEvent(resultado)));
	}

	private void gerarCampo() {
		this.campos.removeAll(this.campos);
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				Campo campo = new Campo(i, j);
				campo.registerObserver(this);
				campos.add(campo);
			}
		}
	}

	private void associarVizinhos() {
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}

	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();

		do {

			int aleatorio = (int) (Math.random() * this.campos.size());
			campos.get(aleatorio).minar();

			minasArmadas = this.campos.stream().filter(minado).count();

		} while (minasArmadas < this.minas);
	}

	public boolean objetivoAlcancado() {
		return this.campos.stream().allMatch(c -> c.objetivoAlcancado());
	}

	public void reiniciar() {
		this.campos.stream().forEach(c -> c.reiniciar());
		this.sortearMinas();
	}

	public void abrir(int linha, int coluna) {

		this.campos.stream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(c -> c.abrir());

	}

	private void mostrarMinas() {
		this.campos.stream()
			.filter(c -> c.isMinado())
			.forEach(c -> c.setAberto(true));
	}

	public void marcar(int linha, int coluna) {
		this.campos.stream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(c -> c.setMarcado());
	}

	@Override
	public void eventEmit(Campo campo, CampoEvent event) {

		if (event == CampoEvent.EXPLODIR) {
			this.mostrarMinas();
			this.notifyObservers(false);
		} else if (this.objetivoAlcancado()) {
			this.notifyObservers(true);
		}

	}

}
