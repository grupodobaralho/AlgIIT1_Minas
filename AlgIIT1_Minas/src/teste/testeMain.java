package teste;

import java.util.Stack;

import main.Coordenada;

public class testeMain {

	// Atributos do Maior Retangulo
	private static Coordenada esquerda;
	private static Coordenada direita;
	private static Coordenada supDireita;
	private static Coordenada supEsquerda;
	private static int alturaFinal = 0;
	private static int areaFinal = 0;

	// Variavel Auxilar de posicao
	private static int posTemp = 0;
	
	/**
	 * Metodo gerador de histograma que caminha em uma matriz
	 * @param args
	 */
	public static void main(String[] args) {

		int input[][] = { 
				{ 1, 1, 1, 0, 1 },
				{ 1, 1, 1, 0, 1 },
				{ 1, 0, 1, 1, 1 },
				{ 1, 1, 0, 1, 0 },
				{ 0, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1 } 
		};

		int[] histograma = new int[input[0].length];

		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				if (input[i][j] == 0)
					histograma[j] = 0;
				else
					histograma[j]++;
			}
			fazAlg(histograma, i);
		}
		printa(input);

	}

	/**
	 * Algoritmo de manipulacao do histograma com duas Stacks auxiliares
	 * 
	 * @param histograma
	 *           histograma atualizado
	 * @param Y
	 *           linha atual da "matriz"
	 */
	public static void fazAlg(int[] histograma, int Y) {
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
	public static void atualizar(Stack<Integer> posStack, Stack<Integer> hStack, int pos, int Y) {
		posTemp = posStack.pop();
		int hTemp = hStack.pop();

		int area = hTemp * (pos - posTemp);

		if (area > areaFinal) {
			areaFinal = area;
			alturaFinal = hTemp;
			esquerda = new Coordenada(posTemp, Y);
			direita = new Coordenada(pos - 1, Y);

		}

	}

	/**
	 * Metodo que imprime os atributos do maior retangulo
	 */
	public static void printa(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (i == esquerda.getY() && j == esquerda.getX()) {
					matriz[i][j] = 3;
				}
				if (i == direita.getY() && j == direita.getX()) {
					matriz[i][j] = 3;
				}
				System.out.print(matriz[i][j]);
			}
			System.out.println();
		}
		
		supEsquerda = new Coordenada(esquerda.getX(), esquerda.getY() - alturaFinal + 1);
		supDireita = new Coordenada(direita.getX(), direita.getY() - alturaFinal + 1);

		System.out.println("Altura: " + alturaFinal);
		System.out.println("AreaFinal: " + areaFinal);
		System.out.println("############## COORDENDAS #############");
		System.out.println(supEsquerda.toString() + supDireita.toString());
		System.out.println(esquerda.toString() + direita.toString());
	}

}
