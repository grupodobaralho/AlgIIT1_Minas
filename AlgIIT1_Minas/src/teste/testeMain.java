package teste;

import java.util.Stack;

import main.Coordenada;

public class testeMain {
	
	//Maior Retangulo
	private static Coordenada esquerda;
	private static Coordenada direita;
	private static int alturaFinal;
	private static int areaFinal;
	
	//Variáveis de auxilio
	private static int posTemp = 0;	
	
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();

	      long total = 0;
	      for (int i = 0; i < 10000000; i++) {
	         total += i;
	      }

	      long stopTime = System.currentTimeMillis();
	      long elapsedTime = stopTime - startTime;
	      System.out.println(elapsedTime);
		
			 startTime = System.currentTimeMillis();

		       total = 0;
		      for (int i = 0; i < 10000000; i++) {
		         total += i;
		      }

		       stopTime = System.currentTimeMillis();
		       elapsedTime = stopTime - startTime;
		      System.out.println(elapsedTime);
		
		
		
		
		int input[][] =	{
				{1,1,1,0,1},
                {1,1,1,0,1},
                {1,0,1,1,1},
                {1,1,0,1,0},
                {0,1,1,1,1},
                {1,1,1,1,1}
         };
		
		int[] histograma = new int[input[0].length];
		
		
		for(int i=0; i<input.length; i++){
			for(int j=0; j<input[i].length; j++){
				if(input[i][j]==0)
					histograma[j] = 0;
				else
					histograma[j]++;					
			}
			fazAlg(histograma, i);			
		}
		printa(input);	
		
	}
	
	public static void fazAlg(int[] histograma, int Y){
		
		Stack<Integer> posStack = new Stack<>();
		Stack<Integer> hStack = new Stack<>();
		
		//int h = 0;
		//int pos = 0;
		
		for(int i=0; i<histograma.length; i++){
			
			if(posStack.isEmpty() || histograma[i] > hStack.peek()){
				posStack.push(i);
				hStack.push(histograma[i]);
				
			} else if(histograma[i] < hStack.peek()){
				
				while(!hStack.isEmpty() && histograma[i] < hStack.peek())
					atualizar(posStack, hStack, i, Y);	
				
				hStack.push(histograma[i]);
				posStack.push(posTemp);
				
			}			
		}
		
		//esvazia a pilha
		while(!hStack.isEmpty()){
			atualizar(posStack, hStack, histograma.length, Y);
		}
		
				
	}
	
	public static void atualizar(Stack<Integer> posStack, Stack<Integer> hStack, int pos, int Y){
		posTemp = posStack.pop();
		int hTemp = hStack.pop();
		
	    int area = hTemp * (pos - posTemp);
	    
	    if(area > areaFinal) {
	    	areaFinal = area; 
	    	alturaFinal = hTemp;
	    	esquerda = new Coordenada(posTemp,Y);
	    	direita = new Coordenada(pos-1,Y);
	    	
	    	
	    }
	    
	}
	
	public static void printa(int[][] matriz){
		for(int i=0; i<matriz.length; i++){
			for(int j=0; j<matriz[i].length; j++){
				if(i==esquerda.getY() && j==esquerda.getX()){
					matriz[i][j] = 3;					
				}
				if(i==direita.getY() && j==direita.getX()){
					matriz[i][j] = 3;					
				}
				System.out.print(matriz[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("Altura: "+ alturaFinal);
		System.out.println("ÁreaFinal: "+ areaFinal);
	}
	
	
}
