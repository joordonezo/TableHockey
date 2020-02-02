/*Raqueta.java
 * Descripción: crea las raquetas y su movimiento.
 * Autores: Jorge Luis Ordoñez Ospina, Santiago Ramírez Gallego, Hans Rivera Londoño, Mariana Rodas Hernández
 */
package ClasesAdic;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Raqueta {

    private final int posX; // atributos de posicion de la raqueta en X y Y
    private int posY; // atributos de posicion de la raqueta en X y Y
    private final int ANCHO = 10, ALTO = 70; // variables finales del tamaño de ancho y alto

    public Raqueta(int posX, int posY) { // metodo constructor de raqueta

        this.posX = posX;
        this.posY = posY;

    }

    public Rectangle2D getRaqueta() {// devuelve un rectangulo que serian las raqutas

        return new Rectangle2D.Double(posX, posY, ANCHO, ALTO);

    }

    public void moverRaq1(Rectangle limites) {
        if (EventoTeclado.w && posY > limites.getMinY() + 11) {// la raqueta se mueve hasta el limite superior y resta dos para subir la raqueta
            posY -= 2; // velocidad de la raqueta 1 en Y
        }
        if (EventoTeclado.s && (posY < limites.getMaxY() - (ALTO + 11))) {// la raqueta se mueve hasta el limite inferior y suma dos para subir la raqueta
            posY += 2; // velocidad de la raqueta 1 en X
        }

    }

    public void moverRaq2(Rectangle limites) {

        if (EventoTeclado.up && posY > limites.getMinY() + 11) {// la raqueta se mueve hasta el limite superior y resta dos para subir la raqueta
            posY -= 2; // velocidad de la raqueta 2 en Y
        }
        if (EventoTeclado.down && (posY < limites.getMaxY() - (ALTO + 11))) {// la raqueta se mueve hasta el limite superior y resta dos para subir la raqueta
            posY += 2; // velocidad de la raqueta 2 en X
        }

    }

}
