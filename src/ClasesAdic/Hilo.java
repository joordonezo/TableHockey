/*Hilo.java
 * Descripción: Maneja el hilo del juego
 * Autores: Jorge Luis Ordoñez Ospina, Santiago Ramírez Gallego, Hans Rivera Londoño, Mariana Rodas Hernández
 */
package ClasesAdic;

public class Hilo extends Thread { // se extiende la clase thead (hilo) para manejar los movientos de la pelota y las raqutas multitarea

    Tablero lamina; // declaracion del objeto tablero (para iniciar el tablero de juego)  

    public Hilo(Tablero lamina) {
        this.lamina = lamina; // asigna el objeto que se acabó de pasar como parámetro this hace referencia al atributo de la clase 
    }

    @Override // sobre escribe el método de la clase thread para que actue diferente y podamos usar el codigo que activa el programa
    public void run() {
        lamina.repaint(); // método para repintar el tablero 

        while (!Pelota.finJuego) {

            if (EventoTeclado.iniciar) {
                lamina.repaint();// metodo para repintar la lamina

            }
            try {
                Thread.sleep(1);// sirve para detener el programa cada 1 milisegundo y así tenemos la velocidad de la pelota

            } catch (Exception e) {
                System.out.println("Error en el hilo conductor: " + e.getMessage());
            }
        }
    }

}
