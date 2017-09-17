package main;

import java.util.Set;
import java.util.Stack;

public class MaiorArea {

	// Atributos recebidos
	private int w;
	private int h;
	private int m;

	// Conjunto que armazena a posicao das minas
	private Set<String> minas;

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
	public MaiorArea(int w, int h, int m, Set<String> minas) {
		this.w = w;
		this.h = h;
		this.m = m;
		this.minas = minas;
		this.alturaFinal = 0;
		this.areaFinal = 0;
		geraHistogramas();
	}

	/**
	 * Metodo gerador de histograma que simula o caminhamento em uma matriz w por h
	 */
	public void geraHistogramas() {
		int[] histograma = new int[w];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				String coordenada = j + "-" + i;
				if (minas.contains(coordenada)) {
					histograma[j] = 0;
				} else {
					histograma[j]++;
				}
			}
			fazAlg(histograma, i);
		}
	}

	/**
	 * Algoritmo de manipulacao do histograma com duas Stacks auxiliares
	 * 
	 * @param histograma
	 *           histograma atualizado
	 * @param Y
	 *           linha atual da "matriz"
	 */
	public void fazAlg(int[] histograma, int Y) {
		//stack para guardar posicoes
		Stack<Integer> posStack = new Stack<>();
		//stack para guardar a altura atual da pesquisa
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
		supEsquerda = new Coordenada(infEsquerda.getX(), infEsquerda.getY() - alturaFinal + 1);
		supDireita = new Coordenada(infDireita.getX(), infDireita.getY() - alturaFinal + 1);

		System.out.println("############## COORDENDAS DO RETANGULO #############");
		System.out.println(supEsquerda.toString() + supDireita.toString());
		System.out.println(infEsquerda.toString() + infDireita.toString());
		System.out.println("Altura total do maior retangulo sem minas: " + alturaFinal);
		System.out.println("Area total do maior retangulo sem minas: " + areaFinal);
	}
}
