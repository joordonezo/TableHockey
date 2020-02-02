/*Pelota.java
 * Descripción: crea la pelota, maneja el tiempo inicial y maneja el contador del marcador
 * Autores: Jorge Luis Ordoñez Ospina, Santiago Ramírez Gallego, Hans Rivera Londoño, Mariana Rodas Hernández
 */
package ClasesAdic;

import java.awt.Rectangle;
import java.awt.geom.*;

public class Pelota {
    
    private double posX, posY;// posicion de la pelota en X y Y
    private double dx = Double.parseDouble(Configuracion.velocidad[0]); // estos son los valores que van sumando a posX y PosY que representan la posicion de la pelota
    private double dy = Double.parseDouble(Configuracion.velocidad[0]); // estos son los valores que van sumando a posX y PosY que representan la posicion de la pelota
    private final int TAMPELOTA = 10; // tamaño de la pelota en pixeles

    private int marcador1 = 0, marcador2 = 0; // marcadores iniciados en 0
    public static boolean finJuego = false; // variable para finalizar el juego y no se quede en un bucle infinito  

    public static int min = Integer.parseInt(Configuracion.tiempo[0]), seg = 0;// tiempo de juego máximo con minutos y segundos

    public Pelota(double posX, double posY) {// metodo constructor de la clase, lleva como argumentos la posicion en X y en Y
        this.posX = posX;// autorefencia para asiganarle el valor que recibe el metodo a la posicion en X
        this.posY = posY;// autorefencia para asiganarle el valor que recibe el metodo a la posicion en X

    }
    
    public Ellipse2D getPelota() {//metodo que retorna una figura tipo elipse para graficar la pelota.

        Rectangle2D rectangulo = new Rectangle2D.Double(posX, posY, TAMPELOTA, TAMPELOTA); // crea un objeto de tipo rectangulo para que sea contenedo de la elipse

        double CentroenX = rectangulo.getCenterX();// localizar el centro del rectangulo en el eje X
        double CentroenY = rectangulo.getCenterY();// localizar el centro del rectangulo en el eje Y

        Ellipse2D pelota = new Ellipse2D.Double(); // crea un objeto elipse 2D llamado pelota 
        pelota.setFrameFromCenter(CentroenX, CentroenY, CentroenX + TAMPELOTA, CentroenY + TAMPELOTA); //  le crea los limites a la elipse

        return pelota; // retorna el objeto tipo elipse 2D 
    }
    
    public void mover(Rectangle limites, boolean colisionR1, boolean colisionR2, boolean iz1, boolean iz2, boolean der1, boolean der2) {
        posX += dx;// posicion de la pelota en X + dx + la posicion anterio 
        posY += dy; // posicion de la pelota en X + dx + la posicion anterio 

        if (colisionR1) {// colision con la raqueta 1 cambia la posicion en x y la actuliza a 51 y con -dx cambiamos la direccion 
            dx = -dx;
            posX = 51;
        }
        
        if (colisionR2) {// colision con la raqueta 2 cambia la posicion en x y la actuliza a 723 y con -dx cambiamos la direccion
            dx = -dx;
            posX = 723;
        }
        
        if (iz1 || iz2) {// colision con los bordes izquierdos cambia la posicion en x y la actuliza a 20 y con -dx cambiamos la posicion
            dx = -dx;
            posX = 20;
        }
        
        if (der1 || der2) {// colision con los bordes derechos cambia la posicion en x y la actuliza a 760 y con -dx cambiamos la posicion
            dx = -dx;
            posX = 760;
        }
        
        if (posY > (limites.getMaxY() - 25)) {// colision con el borde inferior cambia la posicion en Y y la actuliza a el (valor maximo del tablero -25) y con -dx cambiamos la posicion
            dy = -dy;
            
        }
        
        if (posY < 20) {// colision con el borde superior cambia la posicion en Y y la actuliza a 20 y con -dx cambiamos la posicion
            dy = -dy;
            
        }
        
        if (posX + TAMPELOTA >= limites.getMaxX()) { // para saber cundo la pelota pasa los limites y poder contarle 1 punto al jugador 1
            marcador1++; //el puntaje del jugador1 aumenta en uno

            posX = limites.getCenterX();
            posY = limites.getCenterY();
            dx = -dx;
            
        }
        
        if (posX < limites.getMinX()) {// para saber cundo la pelota pasa los limites y poder contarle 1 punto al jugador 2
            marcador2++; //el puntaje del jugador2 aumenta en uno

            posX = limites.getCenterX();
            posY = limites.getCenterY();
            dx = -dx;
            
        }
        
    }
    
    public int getMarcador1() { // retorna el marcado del jugador 1
        return marcador1;
    }
    
    public int getMarcador2() { // retorna el marcado del jugador 2
        return marcador2;
    }
    
}
