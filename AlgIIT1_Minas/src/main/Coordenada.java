package main;

public class Coordenada {

	int x;
	int y;

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
		return "Coordenadas [x=" + x + ", y=" + y + "]";
	}

}
