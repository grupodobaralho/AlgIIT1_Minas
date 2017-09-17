package main;

public class Coordenada {

	int x;
	int y;

	/**
	 * Objeto Coordenada que instancia dois parametros de posicoes em uma matriz
	 * 
	 * @param x
	 *            Horizontal
	 * @param y
	 *            Vertical
	 */
	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "(x=" + x + ", y=" + y + ")";
	}

}
