package Solucao2;

public class Coordenada {

	private int x;
	private int y;

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
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "(x=" + x + ", y=" + y + ")";
	}

}
