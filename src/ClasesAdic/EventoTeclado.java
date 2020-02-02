/*EventoTeclado.java
 * Descripción: maneja los eventos con el teclado.
 * Autores: Jorge Luis Ordoñez Ospina, Santiago Ramírez Gallego, Hans Rivera Londoño, Mariana Rodas Hernández
 */
package ClasesAdic;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EventoTeclado extends KeyAdapter {

    public static boolean w, s, up, down, iniciar = false; // se crean 5 variables boleanas

    @Override//se sobreescribe el metodo keyPressed 
    public void keyPressed(KeyEvent e) { // metodo que identifica cuando se presiona una tecla (w,s,up,down,ecape,space)

        int id = e.getKeyCode(); // identificador de la letra presionada.

        if (id == KeyEvent.VK_W) {
            w = true;
        }
        if (id == KeyEvent.VK_S) {
            s = true;
        }
        if (id == KeyEvent.VK_UP) {
            up = true;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (id == KeyEvent.VK_ESCAPE) {
            iniciar = false;
        }
        if (id == KeyEvent.VK_SPACE) {
            iniciar = true;

        }

    }

    @Override//se sobreescribe el metodo keyPressed 
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode(); // identificador de la letra que se deja de presionar.

        if (id == KeyEvent.VK_W) {
            w = false;
        }
        if (id == KeyEvent.VK_S) {
            s = false;
        }
        if (id == KeyEvent.VK_UP) {
            up = false;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = false;
        }

    }

}
