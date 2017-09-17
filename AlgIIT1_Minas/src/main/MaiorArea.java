package main;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MaiorArea {
	
	int[][] matriz = new int[6][5]; //(ESTE CÓDIGO É PARA TESTE!)	

	// Atributos recebidos
	private int w;
	private int h;
	private int m;

	// private Map<Coordenada, Integer> minas;
	private Set<String> minas;

	// Maior Retangulo
	private Coordenada esquerda;
	private Coordenada direita;
	private int alturaFinal;
	private int areaFinal;
	
	//Auxiliar
	private int posTemp = 0;	

	public MaiorArea(int w, int h, int m, Set<String> minas) {
		this.w = w;
		this.h = h;
		this.m = m;
		this.minas = minas;
		geraHistogramas();
	}

	public void geraHistogramas() {
		int[] histograma = new int[w];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				String coordenada = j + "-" + i;
				if (minas.contains(coordenada)) {
					histograma[j] = 0;	
					//System.out.print("("+j+","+i+")"); (ESTE CÓDIGO É PARA TESTE!)
				} else {
					histograma[j]++;
					matriz[i][j]=1;	//(ESTE CÓDIGO É PARA TESTE!)		
				}
				
			}
			fazAlg(histograma, i);
		}
		printa();
	}

	public void fazAlg(int[] histograma, int Y) {

		Stack<Integer> posStack = new Stack<>();
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

		// esvazia a pilha
		while (!hStack.isEmpty()) {
			atualizar(posStack, hStack, histograma.length, Y);
		}

	}

	public void atualizar(Stack<Integer> posStack, Stack<Integer> hStack, int pos, int Y) {
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

	public void printa() {
		
		Coordenada supEsquerda = new Coordenada(esquerda.getX(), esquerda.getY() - alturaFinal + 1);
		Coordenada supDireita = new Coordenada(direita.getX(), direita.getY() - alturaFinal + 1);

		System.out.println("############## COORDENDAS DO RETANGULO #############");
		System.out.println(supEsquerda.toString() + supDireita.toString());
		System.out.println(esquerda.toString() + direita.toString());
		System.out.println("Altura total do maior retangulo sem minas: " + alturaFinal);
		System.out.println("Area total do maior retangulo sem minas: " + areaFinal);
		
		//(ESTE CÓDIGO É PARA TESTE!)
		for(int i=0; i<matriz.length;i++){
			for(int j=0; j<matriz[i].length; j++){
				System.out.print(matriz[i][j]);
			}
			System.out.println();
		}
		
	}
}
