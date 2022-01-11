package trabajo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//En esta clase creamos todo lo correspondiente al cliente, tanto su constructor, como sus métodos
// escribir y leer que nos serviran para la comunicación... Por último hemos incluido en esta
// misma clase el método main del cliente donde tenemos dos hilos, uno para la lectura y otro
// para la escritura.
//Hemos utilizado el carácter '&' como carácter clave para dejar de leer bytes al utilizar el método read
//Al ejecutar dos instancias de esta clase se iniciará la partida.

public class Cliente {
	private String nombre;
	private Socket s;
	private OutputStream os;
	private InputStream is;

	public Cliente(Socket socket) {
		try {
			this.s = socket;
			this.is = socket.getInputStream();
			this.os = socket.getOutputStream();
			os.write(("Introduce tu nombre: " + "&").getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		nombre = leer();
		System.out.println("Se ha unido a la partida el jugador de nombre " + nombre);
	}

	public String getNombre() {
		return this.nombre;
	}

	public String leer() {
		String dato = "";
		boolean salir = false;
		while (!salir) {
			try { if (is.available() > 0) {
					int leido = is.read();
					while (leido != 38) {
						dato = dato + (char) leido;
						leido = is.read();
					}
					salir = true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dato;
	}

	public void escribir(String dato) {
		try {
			os.write((dato + "&").getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cerrarTodo() {
		try {
			s.close();
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", 8000);
		OutputStream os = socket.getOutputStream();
		InputStream is = socket.getInputStream();

		Thread lectura = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try { if (is.available() > 0) {
							String dato = "";
							int leido = is.read();
							while (leido != 38) {
								dato = dato + (char) leido;
								leido = is.read();
							}
							System.out.println(dato);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		lectura.start();

		Thread escritura = new Thread(new Runnable() {
			@Override
			public void run() {
				Scanner s = new Scanner(System.in);
				while (true) {
					String dato = s.nextLine();
					try {
						os.write((dato + "&").getBytes());
						os.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		escritura.start();
	}
}
