package Solucao2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Trabalho 1 da discplina de Algoritmos e Estruturas de Dados II *
 * 
 * @Titulo Voce e as Minas
 * @author Israel Deorce Vieira Junior e Hercilio Ortiz 17/09/2017
 * @email israel.idvj@gmail.com
 * @professor Marcelo Cohen
 */

public class App {

	//Atributos da area total extraidos do .txt
	private static int largura, altura, quantidade;
	//Conjunto de minas 
	private static List<Coordenada> minas = new ArrayList<>();
	//Obejto auxiliador na montagem de Strings
	private static StringBuilder str;

	/**
	 * Metodo Main que chama a funcao de calcular para os arquivos .txt
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("A primeira area nao faz parte do exercicio: ");
		calculaArea("Files/teste");
		/*calculaArea("Files/caso010");
		calculaArea("Files/caso020");
		calculaArea("Files/caso030");
		calculaArea("Files/caso040");
		calculaArea("Files/caso050");
		calculaArea("Files/caso060");
		calculaArea("Files/caso070");
		calculaArea("Files/caso080");
		calculaArea("Files/caso090");
		calculaArea("Files/caso100");*/
	}

	/**
	 * Recebe um caminho para um arquivo .txt, o processa utilizando 
	 * o Scanner e armazena as informacoes nas variaveis globais
	 * @param arquivo
	 */
	public static void load(String arquivo) {

		Path path = Paths.get(arquivo);

		try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {

			largura = Integer.parseInt(sc.next());
			altura = Integer.parseInt(sc.next());
			quantidade = Integer.parseInt(sc.next());
			System.out.println("Largura Total: " + largura + " \nAltura Total: " + altura + " \nQuantidade de Minas:"
					+ quantidade);
			while (sc.hasNext()) {
				int x = Integer.parseInt(sc.next());
				int y = Integer.parseInt(sc.next());
				minas.add(new Coordenada(x,y));
				// System.out.println(x + " " + y);
			}
		} catch (IOException e) {
			System.out.println("FALHOU");
			e.printStackTrace();
		} catch (Throwable e1) {
			System.out.println("A app apresentou o seguinte erro:");
			e1.printStackTrace();
		}

	}

	/**
	 * Metodo que a partir de um caminho para um arquivo .txt
	 * chama load() para ler os dados, calcula a maior area livre de 
	 * minas e imprime os resultados na tela
	 * @param arquivo
	 */
	public static void calculaArea(String arquivo) {
		long startTime = System.currentTimeMillis();
		load(arquivo);
		System.out.println("Calculando Maior Area sem minas...");
		MaiorArea m = new MaiorArea(largura, altura, quantidade, minas);
		m.printa();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Tempo decorrido: " + elapsedTime);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}
}
