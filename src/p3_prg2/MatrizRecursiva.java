package p3_prg2;

import java.util.Scanner;

public class MatrizRecursiva {
	private static int r = 0;

	public static void main(String[] Args) {
		int filas;
		int columnas;
		//System.out.println("introduzca el tamaño de la matriz:");
		Scanner leer = new Scanner(System.in);
		String aux = leer.next();
		leer.nextLine();
		int n=Integer.parseInt(aux);
		//int n = leer.nextInt();
		if(n<=0||n>(Integer.MAX_VALUE/2)){
			System.out.println("Entrada Inválida.");
			System.exit(0);
		}else{
			filas = n;
			columnas=n;
			//System.out.println("introduzca la matriz de tamaño " +n+ "x" +n);
			String[] vector={""};
			String[] leido= leermatriz(leer, 0, n, vector).split(" ");//llamada al metodo leer y guardado en leido

			int[][] matriz = new int [filas][columnas];
			int[][]matrizLlena=llenarMatriz(matriz, 0, 0, filas, columnas, leido, 0);
			//System.out.println(vector[0]);
			int [][] matrizRaiz =matrizRaizCuadra(matrizLlena, 0, 0, filas, columnas);
			boolean matriztraspuesta=matrizTraspuesta(matrizRaiz,0, 0, filas, columnas);
			if(matriztraspuesta==true){
				System.out.println("La matriz de tamaño "+n+" es de raíz entera simétrica.");
			}else{
				System.out.println("La matriz de tamaño " +n+" es de raíz entera no simétrica.");
			}
			//imprimir la matriz raiz y traspuesta
			/*System.out.println("\nMatriz Raiz");
			for(int i=0; i< matrizRaiz.length; i++){
				for(int j=0; j< matrizRaiz.length; j++){
					System.out.print(matrizRaiz[i][j] + " ");
				}
				System.out.print("\n");
			}*/
			leer.close();
		}
	}
	//metodo para llenar la matriz
	public static int[][] llenarMatriz(int matriz[][], int aux1, int aux2, int i, int j, String[] leido,int contador) {
		if(aux1<i){
			if(aux2<j){
				if(Integer.parseInt(leido[contador])<=0||Integer.parseInt(leido[contador])>(Integer.MAX_VALUE)){
					System.out.println("Entrada Inválida.");
					System.exit(0);
				}	
				matriz[aux1][aux2] = Integer.parseInt(leido[contador]);
				llenarMatriz(matriz, aux1, aux2+1, i, j, leido, contador+1);
			}	
			if(aux2==j){
				aux2=0;
				llenarMatriz(matriz, aux1+1, aux2, i, j, leido, contador);
			}
		}
		return matriz;
	}
	//metodo para leer
	public static String leermatriz(Scanner teclado, int aux, int i, String[] vector){
		if(aux<i){
			vector[0]= vector[0]+ teclado.nextLine()+ " ";//aqui guarda lo que leo(la linea entera);
			//System.out.println(vector[0].length()+"taañoooo33333o");
			leermatriz(teclado, aux+1, i, vector);
		}
		if(vector[0].length()>(i*i*2)+1 || vector[0].length()<(i*i*2)-1){
			//System.out.println(vector[0].length()+"taañooooo");
			System.out.println("Entrada Inválida.");
			System.exit(0);
		}
		return vector[0];
	}
	//metodo para calcular la matriz raiz cuadrada
	public static int[][] matrizRaizCuadra(int matrizRaiz[][], int aux1, int aux2, int i, int j){
		if(aux1<i){ 
			if(aux2<j){
				matrizRaiz[aux1][aux2]= sqrt(matrizRaiz[aux1][aux2]);
				//System.out.println("datocuadrado : "+matrizRaiz[aux1][aux2]);
				matrizRaizCuadra(matrizRaiz, aux1, aux2+1, i, j);
			}	
			if(aux2==j){
				aux2=0;
				matrizRaizCuadra(matrizRaiz, aux1+1, 0, i, j);
			}
		}	
		return matrizRaiz; 
	}
	//metodo que calcula la raiz cuadrada entera de un numero
	public static int sqrt(int n) {
		//Precondicion n>=0
		if (n >= 0) {
			if (r == 0) {
				r = n;
			}
		}
		int result;
		int nCuad = n * n; //equivale a Math.pow(r,2).
		int nMas1Cuad = (n + 1) * (n + 1); // equivale a Math.pow(r+1,2).
		if ((nCuad <= r) && (r < nMas1Cuad)) {
			result = n;
			r = 0;
		} else {
			result = sqrt(n - 1);
		}
		return result;
	}
	//metodo que me comprueba si es simetrica la matriz raiz
	public static boolean matrizTraspuesta(int matrizTra[][], int aux1, int aux2, int i, int j){
		boolean simetrica= matrizTra[aux1][aux2]==matrizTra[aux2][aux1];
		if(simetrica){
			aux2++;
			if(aux2==i){
				aux1++;
				aux2=0;
			}
			if(aux1<matrizTra.length){
				simetrica=matrizTraspuesta(matrizTra, aux1, aux2, i, j);
			}
		}
		//System.out.println("que es"+simetrica);
		return simetrica;
	}
}
