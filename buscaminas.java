


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author TABARES
 */



public class buscaminas {
	public static char[][] matriz;
	public static char[][] matrizMinada;
	public static int filas = 0;
	public static int columnas = 0;
	public static int minas = 0;
	public static int fila = 0;
	public static int col = 0;
	public static char comando = ' ';
	public static int gana= 0;
	public static boolean finPartida = false;
	public static int contador = 0;
	public static boolean try_again = true;

	/**
	* @Description  representa el orden y como sigue el programa los métodos.
	*/
	public static void main(String[] args) throws Exception {
		while (try_again) {
			contador = 0;
			finPartida = false;
			pedirTamano();
			iniciaTablero();
			imprTablero(filas, columnas, matrizMinada);
			System.out.println("");

			movimiento();
			generarMinas();
			checkMinas();
			descubrirCasilla(fila, col,comando);
			while (!finPartida) {
				
					imprTablero(filas, columnas, matriz);
					imprRespuesta(filas, columnas, matrizMinada);
					System.out.println("");
					
				System.out.println("");
				movimiento();
				descubrirCasilla(fila, col,comando);

			}
			System.out.println("¿Deseas volver a empezar? Oprime 1 si es así");	
			BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
				String linea;
				linea = lector.readLine();
				if(linea.equals("1")){
					main(args);
				}else{
					System.out.println("Nos vemos luego :)");
					System.exit(1);
					try_again=false;
				}
			
		}
		
		

	}

	/**
	* @Description  solicita el tamaño de la matriz con la que se desea iniciar el juego
	* y el número de minas que debe existir
	*/
	public static void pedirTamano() throws Exception {

		System.out.println("Bienvenidos al buscaminas, debes ingresar el alto "
				+ "\n"+"y ancho del tablero, luego ingresar el número de minas"
				+ "\n"+"por ejemplo 8 9 10, que significa un tablero de 8X9 y diez minas en él");	
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		try {
			String linea;

			linea = lector.readLine();

			System.out.println("has digitado "+ linea);
			String[] tamano = linea.split(" ");
			if (minas > filas * columnas)
				throw new Exception("Hay más minas de las que se puede ubicar");
			filas=Integer.parseInt(tamano[0])+1;
			columnas=Integer.parseInt(tamano[1])+1;
			minas=Integer.parseInt(tamano[2]);
			
			matriz=new char[filas][columnas];
			matrizMinada=new char[filas][columnas];
			
		} catch (NumberFormatException e) {
			System.out.println("Porfavor escriba una cadena que corresponda a las instrucciones (espacios)");
			throw new Exception();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			throw new Exception();
		}
	}
	/**
	* @Description  inicia el trablero en '.' en todas las posiciones
	*/
	public static void iniciaTablero() {
		for (int l = 1; l < filas; l++) {
			for (int m = 1; m < columnas; m++) {
				matrizMinada[l][m] = '.';
			}
		}
		for (int l = 1; l < filas; l++) {
			for (int m = 1; m < columnas; m++) {
				matriz[l][m] = '.';
			}
		}
	}
	
	/**
	* @Description  imprime el trablero o la matriz del juego
	*/
	public static void imprTablero(int length, int length0, char[][] matriz) {
		System.out.println("Tablero de juego");	
		for (int i = 1; i < length; i++) {
			if (i == 1) {
				System.out.println("_____________");
			}
			System.out.printf("%4s", i + " ");
			for (int j = 1; j < length0; j++) {
				System.out.printf("%s", " " + matriz[i][j] + " ");
			}
			if (i == length - 1) {
				System.out.println("");
				for (int j = 1; j < columnas; j++) {
					if (j == 1) {
						System.out.print("    ");
					}
					System.out.printf("%3s", j + " ");
				}
			}
			System.out.println("");
		}
		
	}
	
	
	/**
	* @Description  Imprime las soluciones
	*/
	public static void imprRespuesta(int length, int length0, char[][] matriz) {
		System.out.println("Tablero de juego");

		for (int i = 1; i < length; i++) {
			
			
			if (i == 1) {
				System.out.println("__________");
			}
			System.out.printf("%4s", i + " ");
			for (int j = 1; j < length0; j++) {
				System.out.printf("%s", " " + matriz[i][j] + " ");

				
			}
			
			
			if (i == length - 1) {
				System.out.println("");
				for (int j = 1; j < columnas; j++) {
					if (j == 1) {
						System.out.print("    ");
						
						
					}
					System.out.printf("%3s", j + " ");
				}
			}
			System.out.println("");
		}
		
	}
	
	/**
	* @Description  imprime el trablero o la matriz del juego
	*/
	public static void generarMinas() {
		int minaY=0;
		int minaX=0;
		int minasGeneradas=0;
		

		for (int i = 0; i < matriz.length && minas > 0 && minasGeneradas!=minas; i++) {
			
			for (int j = 0; j < matriz[0].length && minas > 0 && minasGeneradas!=minas; j++) {

				
					minaY=(int)(Math.random()*matriz.length);
					minaX=(int)(Math.random()*matriz[0].length);
					if (matriz[i][j]!='*') {
					matriz[minaY][minaX]='*';
					minasGeneradas++;
				}
				

			}
		}
	}

	/**
	* @Description  cuenta minas alrededor
	*/
	public static void checkMinas() {
		int contador = 0;
		for (int i = 2; i <= matriz.length - 2; ++i) {
			for (int j = 2; j <= matriz[0].length - 2; ++j) {
				contador = 0;
				if ((matriz[i + 1][j + 1] == '*') && (matriz[i][j] == '.')) {
					contador++;
				}
				if ((matriz[i - 1][j - 1] == '*') && (matriz[i][j] == '.')) {
					contador++;
				}
				if ((matriz[i - 1][j + 1] == '*') && (matriz[i][j] == '.')) {
					contador++;
				}
				if ((matriz[i + 1][j - 1] == '*') && (matriz[i][j] == '.')) {
					contador++;
				}
				if ((matriz[i - 1][j] == '*') && (matriz[i][j] == '.')) {
					contador++;
				}
				if ((matriz[i + 1][j] == '*') && (matriz[i][j] == '.')) {
					contador++;
				}
				if ((matriz[i][j + 1] == '*') && (matriz[i][j] == '.')) {
					contador++;
				}
				if ((matriz[i][j - 1] == '*') && (matriz[i][j] == '.')) {
					contador++;
				}
				if (matriz[i][j] == '.') {
					if (contador > 0) {
						matriz[i][j] = Integer.toString(contador).charAt(0);
					} else if (contador == 0) {
						matriz[i][j] = Integer.toString(contador).charAt(0);
					}
				}
			}
		}
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (matriz[i][j] == '.') {
					matriz[i][j] = Integer.toString(0).charAt(0);
				}
			}
		}
	}

	/**
	* @Description  Ingresa la casilla y que desea descubrir o colocar una bandera
	*/
	
	public static void movimiento() throws IOException {
		comando=' ';
		System.out.println("¡A Jugar!"+"\n"+"Escribe la Fila, la columna y si desea "
				+"descubrir la filaDada una U, o M si desea marcar que hay una mina en el sitio"+"\n"+
				"IMPORTANTE: separa todo con un space");
	
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		String linea;
		
			
			while (comando!='U'&&comando!='P'){
		System.out.println("Vamos, escribe las coordenadas y lo que deseas hacer U (mayus) o P (mayus): ");
		linea = lector.readLine();
		System.out.println("has digitado "+ linea);
		String[] comand = linea.split(" ");
		try {
		fila=Integer.parseInt(comand[0]);
		col=Integer.parseInt(comand[1]);
		comando=comand[2].charAt(0);
		

		} catch (NumberFormatException e) {
			
			System.out.println("separalo porfavor por un espacio.");
		}
	}
	}

	/**
	* @Description  descubrir casillas de forma recursiva
	*/

	public static void descubrirCasilla(int filaDada, int colDada, char comando) {
		if (comando=='U'){
		 int row = fila;
	        int column = col;
	        if (filaDada > filas - 1 || filaDada < 0 || colDada > columnas - 1 || colDada < 0) {
	        	
	            return;
	        }
	        if (matriz[row][column] == '*') {
	            finPartida = true;
	            System.out.println("¡¡BOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOM  X.X !!");
	            imprTablero(filas, columnas, matriz);
	        } else if (matriz[filaDada][colDada] == '0') {
	        	matriz[filaDada][colDada] = '-';
	            if (matrizMinada[filaDada][colDada] != matriz[filaDada][colDada]) {
	                matrizMinada[filaDada][colDada] = matriz[filaDada][colDada];
	                contador++;
	                descubrirCasilla(filaDada + 1, colDada + 1,comando);
	                descubrirCasilla(filaDada + 1, colDada,comando);
	                descubrirCasilla(filaDada, colDada + 1,comando);
	            }
	            if (filaDada > filas - 1 || filaDada < 0 || colDada > columnas - 1 || colDada < 0) {
	            	descubrirCasilla(filaDada - 1, colDada + 1,comando);
	            	descubrirCasilla(filaDada, colDada - 1,comando);
	                descubrirCasilla(filaDada - 1, colDada,comando);
	                descubrirCasilla(filaDada + 1, colDada - 1,comando);
	                descubrirCasilla(filaDada - 1, colDada - 1,comando);
	               
	            }
	        } else if ((matriz[filaDada][colDada] > '0')) {
	            if (matrizMinada[filaDada][colDada] != matriz[filaDada][colDada]) {
	                matrizMinada[filaDada][colDada] = matriz[filaDada][colDada];
	                contador++;
	            }
	        }
		}else{
			verificarGanador();
		}
	}
	/**
	* @Description  verifica si la bandera está en una casilla con mina.
	*/
	public static void verificarGanador(){
		if(matrizMinada[fila][col]=='.'){
			matrizMinada[fila][col]='P';
		}
		if(matriz[fila][col]=='*'){
			gana++;
		}
		System.out.println(gana+"");
		if(gana==minas){
			System.out.println("felicidades, has ganado!");
			finPartida=true;
			imprTablero(filas, columnas, matriz);
		}
	}
	
	
}