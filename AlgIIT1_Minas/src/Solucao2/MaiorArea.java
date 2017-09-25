package Solucao2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MaiorArea {

	// Atributos recebidos
	private int w;
	private int h;
	private int m;

	// Conjunto que armazena a posicao das minas
	private List<Coordenada> minas;

	private Map<Integer, List<Integer>> minasPorX;

	// Atributos do Maior Retangulo
	private Coordenada infEsquerda;
	private Coordenada infDireita;
	private Coordenada supEsquerda;
	private Coordenada supDireita;
	private int alturaFinal;
	private int areaFinal;

	// Variavel Auxilar de posicao
	private int posTemp = 0;

	/**
	 * Metodo Construtor
	 * 
	 * @param w
	 *            largura
	 * @param h
	 *            altura
	 * @param m
	 *            quantidade de minas
	 * @param minas
	 *            conjunto de minas
	 */
	public MaiorArea(int w, int h, int m, List<Coordenada> minas) {
		this.w = w;
		this.h = h;
		this.m = m;
		this.minas = minas;
		this.alturaFinal = 0;
		this.areaFinal = 0;
		minas.sort(Comparator.comparing(Coordenada::getY));
		minasPorX = new HashMap<>();
		for (Coordenada c : minas) {
			if (minasPorX.containsKey(c.getX()))
				minasPorX.get(c.getX()).add(c.getY());
			else {
				minasPorX.put(c.getX(), new ArrayList<>());
				minasPorX.get(c.getX()).add(c.getY());
			}
		}
		geraHistogramas();
	}

	public int[] geraHistogramas2(int Y) {
		int[] histograma = new int[w];
		List<Integer> minasEmX = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < w; j++) {
				if (minas.get(i).getX() == j + 1 && minas.get(i).getY() == Y)
					minasEmX.add(j+1);
			}

		}
		for (int i = 0; i < w; i++) {
			if (minasEmX.contains(i+1)) {
				histograma[i] = 0;
			} else {
				if (minasPorX.containsKey(i + 1)) {
					List<Integer> MinasEmY = minasPorX.get(i+1);
					int maisProxima = -1;
					for (Integer p : MinasEmY) {
						if (p < Y) {
							if (maisProxima < p)
								maisProxima = p;
							histograma[i] = Y - maisProxima;
						} else {
							histograma[i] = Y;
						}
					}
				} else
					histograma[i] = Y;
			}
		}
//		for (int i = 0; i < histograma.length; i++) {
//			System.out.print(histograma[i] + " - ");
//		}
//		System.out.println('\n');

		return histograma;
	}

	/**
	 * Metodo gerador de histograma que simula o caminhamento em uma matriz w por h
	 */
	public void geraHistogramas() {
		Coordenada minaAtual;
		for (int i = 0; i < m; i++) {
			minaAtual = minas.get(i);
			fazAlg(geraHistogramas2(minaAtual.getY()), minaAtual.getY());
		}
		fazAlg(geraHistogramas2(h), h -1);
	}

	/**
	 * Algoritmo de manipulacao do histograma com duas Stacks auxiliares
	 * 
	 * @param histograma
	 *            histograma atualizado
	 * @param Y
	 *            linha atual da "matriz"
	 */
	public void fazAlg(int[] histograma, int Y) {
		// stack para guardar posicoes
		Stack<Integer> posStack = new Stack<>();
		// stack para guardar a altura atual da pesquisa
		Stack<Integer> hStack = new Stack<>();

		for (int i = 0; i < histograma.length; i++) {

			if (posStack.isEmpty() || histograma[i] > hStack.peek()) {
				posStack.push(i);
				hStack.push(histograma[i]);

			} else if (histograma[i] < hStack.peek()) {

				while (!hStack.isEmpty() && histograma[i] < hStack.peek())
					atualizar(posStack, hStack, i, Y);

				hStack.push(histograma[i]);
				posStack.push(posTemp);

			}
		}

		// Esvazia a pilha
		while (!hStack.isEmpty()) {
			atualizar(posStack, hStack, histograma.length, Y);
		}

	}

	/**
	 * Metodo auxiliar que remove elementos das pilhas, gera nova Area e confere se
	 * eh a maior, atualizando os atributos do maior retangulo
	 * 
	 * @param posStack
	 *            = stack para guardar posicoes
	 * @param hStack
	 *            = tack para guardar a altura atual da pesquisa
	 * @param pos
	 *            = posicao horizontal maxima do retangulo atual
	 * @param Y
	 *            = linha atual da "matriz"
	 */
	public void atualizar(Stack<Integer> posStack, Stack<Integer> hStack, int pos, int Y) {
		posTemp = posStack.pop();
		int hTemp = hStack.pop();

		int area = hTemp * (pos - posTemp);

		if (area > areaFinal) {
			areaFinal = area;
			alturaFinal = hTemp;
			infEsquerda = new Coordenada(posTemp, Y);
			infDireita = new Coordenada(pos - 1, Y);
		}

	}

	/**
	 * Metodo que imprime os atributos do maior retangulo
	 */
	public void printa() {
		infDireita.setX(infDireita.getX() + 1);
		infDireita.setY(infDireita.getY() + 1);
		infEsquerda.setX(infEsquerda.getX() + 1);
		infEsquerda.setY(infEsquerda.getY() + 1);
		supEsquerda = new Coordenada(infEsquerda.getX(), infEsquerda.getY() - alturaFinal + 1);
		supDireita = new Coordenada(infDireita.getX(), infDireita.getY() - alturaFinal + 1);

		System.out.println("############## COORDENDAS DO RETANGULO #############");
		System.out.println(supEsquerda.toString() + supDireita.toString());
		System.out.println(infEsquerda.toString() + infDireita.toString());
		System.out.println("Altura total do maior retangulo sem minas: " + alturaFinal);
		System.out.println("Area total do maior retangulo sem minas: " + areaFinal);
	}
}
