package trabajo;

//En esta clase se encuentra lo relativo al tablero de la partida
// ya que aquí lo creamos, lo actualizamos, y lo mostramos con sus respectivos métodos.

public class Juego {
	private int[] tablero;
	private int[][] combinacionesVictoria = new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 },
			{ 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };
	private int contadorFichasPuestas;

	Juego() {
		this.tablero = new int[9];
		this.contadorFichasPuestas = 0;
	}

	public int poneFicha(String posicionTablero, String marca) {
		int posicion = Integer.parseInt(posicionTablero);
		int m;
		if(marca.equals("X")) {
			m = 1;
			
		} else {
			m = -1;
		}
		if (tablero[posicion - 1] != 0) {
			return 1;
		} else {
			tablero[posicion - 1] = m;
			contadorFichasPuestas++;
		}
		if (contadorFichasPuestas == 9) {
			return 2;
		}
		for (int i = 0; i < combinacionesVictoria.length; i++) {
			if (tablero[combinacionesVictoria[i][0]] == 1 && tablero[combinacionesVictoria[i][1]] == 1
					&& tablero[combinacionesVictoria[i][2]] == 1) {
				return 3;
			} else if (tablero[combinacionesVictoria[i][0]] == -1 && tablero[combinacionesVictoria[i][1]] == -1
					&& tablero[combinacionesVictoria[i][2]] == -1) {
				return 4;
			}
		}

		return 0;

	}


	public String muestraTablero() {
		String repTablero = "Tablero actual: \n";
		String marca = "";
		for (int i = 1; i < 10; i++) {
			if(tablero[i-1] == 0) {
				marca = " ";
			} else if(tablero[i-1] == -1) {
				marca = "O";
			} else {
				marca = "X";
			}
			repTablero += String.format("%2s", marca); 
			if (i % 3 == 0) {
				repTablero += "\n--------\n";
			} else {
				repTablero += "|";
			}
		}
		repTablero += "\n";
		return repTablero;
	}
}
