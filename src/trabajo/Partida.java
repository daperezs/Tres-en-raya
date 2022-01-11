package trabajo;

import java.io.IOException;
import java.util.Random;

//Esta es la clase donde se realiza la partida de tres en raya.
//Como vemos, es una clase que implementa runnable y con ello un método run que es donde comienza y 
//acaba la partida.
//Tiene un método para sortear el inicio de la partida.
//Cuenta con el método juega que es donde el usuario elegirá el lugar de la ficha que quiere poner,
//además, este método va informando del flujo de la partida y de su final, mostrando el tablero...


public class Partida implements Runnable {
	private Juego juego;
	private Cliente Cliente1;
	private Cliente Cliente2;

	public Partida(Cliente Cliente1, Cliente Cliente2) throws IOException {
		juego = new Juego();
		this.Cliente1 = Cliente1;
		this.Cliente2 = Cliente2;
	}

	public void run() {

		Cliente1.escribir("El estado inicial del tablero es el siguiente");
		Cliente2.escribir("El estado inicial del tablero es el siguiente");
		Cliente1.escribir(juego.muestraTablero());
		Cliente2.escribir(juego.muestraTablero());

		boolean finPartida = false;
		String marca1 = "X";
		String marca2 = "O";

		boolean turno = sorteo();
		if (turno) {
			Cliente1.escribir("Has ganado el sorteo, comienzas jugando");
			Cliente2.escribir("Has perdido el sorteo, no comienzas jugando");
		} else {
			Cliente2.escribir("Has ganado el sorteo, comienzas jugando");
			Cliente1.escribir("Has perdido el sorteo, no comienzas jugando");
		}

		while (!finPartida) {
			if (turno) {
				finPartida = juega(Cliente1, Cliente2, marca1);
				turno = false;
			}

			else {
				finPartida = juega(Cliente2, Cliente1, marca2);
				turno = true;
			}

		}
		Cliente1.cerrarTodo();
		Cliente2.cerrarTodo();
	}

	private boolean sorteo() {
		Random numAleatorio = new Random();
		return numAleatorio.nextBoolean();

	}

	private boolean juega(Cliente c1, Cliente c2, String marca) {
		String posicionTablero;
		boolean añadido = false;
		int codigo = 0;
		while (!añadido) {
			c1.escribir("Elige un número del 1 al 9: ");
			posicionTablero = c1.leer();
			codigo = juego.poneFicha(posicionTablero, marca);
			switch (codigo) {
			case 1:
				c1.escribir("Posición ya ocupada, selecciona otra!!!");
				break;
			case 2:
				c1.escribir("La partida ha terminado en empate.");
				c2.escribir("La partida ha terminado en empate.");
				System.out.println("La partida ha terminado en empate.");
				System.out.println();
				System.out.println("Va a comenzar otra partida.");
				return true;
			case 3:
				c1.escribir("Enhorabuena, eres el ganador de la partida!!!!");
				c2.escribir("Lo sentimos, eres el perdedor de la partida :(");
				c1.escribir(juego.muestraTablero());
				c2.escribir(juego.muestraTablero());
				System.out.println("La partida ha terminado, el ganador es " + c1.getNombre());
				System.out.println();
				System.out.println("Va a comenzar otra partida.");
				return true;
			case 4:
				c1.escribir("Enhorabuena, eres el ganador de la partida!!!!");
				c2.escribir("Lo sentimos, eres el perdedor de la partida :(");
				c1.escribir(juego.muestraTablero());
				c2.escribir(juego.muestraTablero());
				System.out.println("La partida ha terminado, el ganador es " + c1.getNombre() + " !!!!");
				System.out.println();
				System.out.println("Va a comenzar otra partida.");
				return true;
			default:
				añadido = true;
				break;
			}

		}
		c1.escribir(juego.muestraTablero());
		c2.escribir(juego.muestraTablero());

		c1.escribir("Espera tu turno, el rival está haciendo su jugada");
		c2.escribir("Es tu turno");

		return false;
	}
}
