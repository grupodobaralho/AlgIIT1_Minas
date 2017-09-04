package main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Trabalho 1 da discplina de Algoritmos e Estruturas de Dados II *
 * 
 * @Título Você e as Minas
 * @author Israel Deorce Vieira Júnior 13/08/2017
 * @email israel.idvj@gmail.com
 * @professor Marcelo Cohen
 */

public class App {

	private static int largura, altura, quantidade;
	//private static Map<Coordenada, Integer> minas = new HashMap<>();
	private static Set<String> minas = new HashSet<>();
	private static StringBuilder str;
	
	public static void main(String[] args) {	
		
		long startTime = System.currentTimeMillis();
		load("Files/teste");	
		MaiorArea m = new MaiorArea(largura,altura,quantidade,minas);
	    long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println(elapsedTime);
		/*
	    startTime = System.currentTimeMillis();
		load("Files/caso020");	
		m = new MaiorArea(largura,altura,quantidade,minas);
	    stopTime = System.currentTimeMillis();
	    elapsedTime = stopTime - startTime;
	    System.out.println(elapsedTime);
		
		startTime = System.currentTimeMillis();
		load("Files/caso030");	
		m = new MaiorArea(largura,altura,quantidade,minas);
	    stopTime = System.currentTimeMillis();
	    elapsedTime = stopTime - startTime;
	    System.out.println(elapsedTime);
	    */
		
		/*
		load("Files/caso040");	
		m = new MaiorArea(largura,altura,quantidade,minas);
		
		load("Files/caso050");	
		m = new MaiorArea(largura,altura,quantidade,minas);
		
		load("Files/caso060");	
		m = new MaiorArea(largura,altura,quantidade,minas);
		
		load("Files/caso070");	
		m = new MaiorArea(largura,altura,quantidade,minas);
		
		load("Files/caso080");	
		m = new MaiorArea(largura,altura,quantidade,minas);
		
		load("Files/caso090");	
		m = new MaiorArea(largura,altura,quantidade,minas);
		
		load("Files/caso0100");	
		m = new MaiorArea(largura,altura,quantidade,minas);
		*/
		
	}

	public static void load(String arquivo) {

		Path path = Paths.get(arquivo);
		String nome = "";
		Scanner sc2;

		try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {

			largura = Integer.parseInt(sc.next());
			altura = Integer.parseInt(sc.next());
			quantidade = Integer.parseInt(sc.next());
			System.out.println(largura + " " + altura + " " + quantidade);			
			while (sc.hasNext()) {
				//int x = Integer.parseInt(sc.next());
				//int y = Integer.parseInt(sc.next());
				//Coordenada c = new Coordenada(x,y);
				//minas.put(c, 1);
				
				String x = sc.next();
				String y = sc.next();
				str = new StringBuilder();
				str.append(x);
				str.append("-");
				str.append(y);
				minas.add(str.toString());
				
				//System.out.println(x + " " + y);
			}
		} catch (IOException e) {
			System.out.println("FALHOU");
			e.printStackTrace();
		} catch (Throwable e1) {
			System.out.println("A app apresentou o seguinte erro:");
			e1.printStackTrace();
		}

	}

}
