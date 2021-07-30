package br.com.ksg.cm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private int minas;

	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		this.gerarCampo();
		this.associarVizinhos();
		this.sortearMinas();
	}

	private void gerarCampo() {
		this.campos.removeAll(this.campos);
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				campos.add(new Campo(i, j));
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
		try {
			this.campos.stream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
					.ifPresent(c -> c.abrir());
		} catch (Exception e) {
			// FIXME Ajustar a implementação do metodo abrir
			this.campos.forEach(c -> c.setAberto(true));
			throw e;
		}
	}

	public void marcar(int linha, int coluna) {
		this.campos.stream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(c -> c.setMarcado());
	}

}
