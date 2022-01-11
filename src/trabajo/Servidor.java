package trabajo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


//En esta clase creamos el servidor del juego al que se conectarán los jugadores
//Una vez se han conectado comenzará la partida

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8000);
        Socket s = null;
        while (true) {
            System.out.println("Esperando a los jugadores para comenzar la partida...........");
            s = ss.accept();
            Cliente c1 = new Cliente(s);

            System.out.println("Esperando al segundo jugador....");
            s = ss.accept();
            Cliente c2 = new Cliente(s);
            System.out.println("Todos los jugadores se han unido. La partida va a comenzar");

            Partida p = new Partida(c1, c2);
            p.run();
        }
    }
}
