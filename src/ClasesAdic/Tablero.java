/*Tablero.java
 * Descripción: Dibuja todo lo que contiene el juego en su interior.
 * Autores: Jorge Luis Ordoñez Ospina, Santiago Ramírez Gallego, Hans Rivera Londoño, Mariana Rodas Hernández
 */
package ClasesAdic;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Tablero extends JPanel {

    public static Image fondo; // creando objeto tipo imagen para poner el fondo 
    private Image mgInicial; //
    private final int ANCHO = 794, ALTO = 471; // tamaño del tablero con constantes 
    Pelota pelota = new Pelota(390, 245); // crea el objeto pelota
    Raqueta jugador1 = new Raqueta(30, 195); // crea el objeto raqueta para jugador 1
    Raqueta jugador2 = new Raqueta(750, 195); // crea el objeto raqueta para jugador 2
    RoundRectangle2D iz1 = new RoundRectangle2D.Double(0, 0, 11, 170, 11, 11);// crea el borde colisionador izquierdo 1 (superior) (posicion en 0,0; tamaño de ancho 11 x alto 173, y borde de radio 11 (11,11) )
    RoundRectangle2D iz2 = new RoundRectangle2D.Double(0, 300, 11, 170, 11, 11);// crea el borde colisionador izquierdo 2 (inferior)
    RoundRectangle2D der1 = new RoundRectangle2D.Double(783, 0, 11, 170, 11, 11);// crea el borde colisionador derecho 1 (superior) 
    RoundRectangle2D der2 = new RoundRectangle2D.Double(783, 300, 11, 170, 11, 11);// crea el borde colisionador derecho 2 (inferior)

    public Tablero() { // método constructor 
        preInit(); // método para llamar la imagen y poder mostrarla
        initComponents();// método necesario para el fondo, están creados en la clase

        setVisible(true); // métodos heredados de JPanel, para que el tablero sea visible
        setSize(ANCHO, ALTO); // tamaño del tablero

        final Timer t = new Timer(); // objeto de la clase Timer necesario para iniciar el contador del tiempo 

        t.scheduleAtFixedRate(new TimerTask() { // metodo para cuenta regresiva, tiene tres argumentos, una tarea, un retardo (0) y un periodo de tiempo 

            @Override// sobrescribir el método run
            public void run() { // inicie el tiempo 
                if (EventoTeclado.iniciar) { // solo descuenta al tiempo cuando eventoteclado.iniciar es verdadero 
                    pelota.seg--; // resta un segundo cada vez que se ejecuta o le resta uno a la variable seg 
                    if (pelota.seg == -1) {//cuando seg  llegue a cero se resta 1 a min y es necesario que sea -1 para que no se muestre 60 seg y quede en x:00
                        pelota.min -= 1; // resta un minuto cada vez que se cumpla la condición o le resta uno a la variable min 
                        pelota.seg = 59; // se reinicia segundos en 59 para que se cumpla el tiempo 
                    }
                    if (pelota.min == 0 && pelota.seg == 0) {

                        t.cancel();// el método que detiene la ejecucion del tiempo para que no muestre valores negativos en el tiempo

                    }
                }

            }
        }, 0, 1000);// retardo en 0 y periodo en 16.66 minutos

    }

    @Override // sobrescribir el método paint component 
    public void paintComponent(Graphics g) {// método para dibujar los objetos en el tablero
        super.paintComponent(g); // constructor de la clase padre  
        g.drawImage(fondo, 0, 0, ANCHO, ALTO, this);//Dibuja el fondo, en posicion 0,0

        Graphics2D g2 = (Graphics2D) g; // casting del objeto g (graphics) a graphics 2D con el objeto g2

        dibujar(g2); // dibuja la pelota y las raquetas en el tablero, metodos creados
        actualizar();// actualiza la posición de la pelota en el tablero (movimiento de la pelota)
        dibujarMarcador(g2); // dibuja el marcador en el tablero
        dibujarTiempo(g2); // dibuja el tiempo 
        if (!EventoTeclado.iniciar) { // mientras sea falso, debe poner la imagen inicial 
            dibujarNombre1(g2);
            dibujarNombre2(g2);
            g.drawImage(mgInicial, 147, 158, 501, 155, this);//Dibuja el fondo, en posicion 0,0

        }

    }

    private void initComponents() { // Se usa en conjunto con preInit para agragarle el fondo // carga todo los objetos que hallas puesto en el JFrame -> JPanel al momento de ejecutar el programa es por eso que va dentro de el contructor de la clase.

    }

    private void preInit() {//pre carga las imagenes 
        try { // para solucionar el problema que no se ecuentre la imagen y se pueda cerrar el programa (try = intentar)
            fondo = new ImageIcon("src/graficos/Table.jpg").getImage(); // ruta de la imagen de fondo, el método getImage obtine la imagen
            mgInicial = new ImageIcon("src/graficos/MensajeInicial1.png").getImage(); // ruta de la imagen de fondo, el método getImage obtine la imagen
        } catch (Exception e) { // atrapa un excepcion general para evitar que se detenga el programa (catch = atrapar)
            System.out.println("La imagen no se encuentra");

        }

    }

    public void dibujar(Graphics2D g) { //método para dibujar la pelota, las raquetas y los colisionadores de la izquierda y la derecha
        g.setPaint(Configuracion.colorPelota);// seleccionamos un color para la pelota (negro por la constante de clase BLACK)
        g.fill(pelota.getPelota()); // para dibujar la pelota 

//        Color rojo = new Color(186, 9, 9); // crea el color rojo custom (186 rojo, 9 de verde, 9 de azul)
        g.setPaint(Configuracion.colorRizq); // seleccionamos el color rojo para la raqueta del jugador 1
        g.fill(jugador1.getRaqueta()); // dibuja la raqueta del jugador 1

//        Color verde = new Color(39, 175, 28); // crea el color rojo custom (39 rojo, 175 de verde, 28 de azul)
        g.setPaint(Configuracion.colorRder);// seleccionamos el color verde para la raqueta del jugador 2
        g.fill(jugador2.getRaqueta()); // dibuja la raqueta del jugador 2

        Color naranja = new Color(234, 145, 15); // crea el color naranja custom (234 rojo, 145 de verde, 15 de azul)
        g.setPaint(naranja);// seleccionamos el color naranja para los colisionadores de izquierda y derecha
        g.fill(iz1);// dibuja el borde colisionador izquierdo 1 (superior) 
        g.fill(iz2); // dibuja el borde colisionador izquierdo 2 (inferior)

        g.fill(der1);// dibuja el borde colisionador derecho 1 (superior) 
        g.fill(der2); // dibuja el borde colisionador derecho 2 (inferior)

    }

    public void actualizar() {
        pelota.mover(getBounds(), colisionRaqu(jugador1.getRaqueta()), colisionRaqu(jugador2.getRaqueta()),
                colisionPared(iz1.getBounds()), colisionPared(iz2.getBounds()), colisionPared(der1.getBounds()),
                colisionPared(der2.getBounds())); // se usa para mover la pelota

        jugador1.moverRaq1(getBounds()); // se usa para mover la raqueta del jugador 1
        jugador2.moverRaq2(getBounds()); // se usa para mover la raqueta del jugador 2

    }

    private boolean colisionRaqu(Rectangle2D raqueta) {// colision contra las raquetas 

        return pelota.getPelota().intersects(raqueta); // golpe contra la raqueta

    }

    private boolean colisionPared(Rectangle2D pared) {// colision contra las paredes de la izquierda y derecha 

        return pelota.getPelota().intersects(pared);
    }

    private void dibujarMarcador(Graphics2D g) { // dibuja el marcador 
        g.setPaint(Color.BLACK);// seleccionamos un color para el marcado (negro por la constante de clase BLACK)
        Graphics2D g1 = g, g2 = g; // dos objetos que toman lo mismo que tenga g

        Font marcador = new Font("helvetica", Font.BOLD, 30); // se crea un objeto de tipo font(fuente o tipo de letra)
        g.setFont(marcador);// seleccionamos el tipo de letra a el marcador

        g1.drawString(Integer.toString(pelota.getMarcador1()), (float) getBounds().getCenterX() - 50, 41); // dibuja el marcador, obteniendo el marcador desde la clase pelota, una posicion en X y una posicion en Y 
        g2.drawString(Integer.toString(pelota.getMarcador2()), (float) getBounds().getCenterX() + 25, 41); // dibuja el marcador, obteniendo el marcador desde la clase pelota, una posicion en X y una posicion en Y

        if (pelota.getMarcador1() == 5) { // condicion para que gane el jugador 1 con un maximo de 5 puntos
            g.drawString("Ganó " + Configuracion.listaNombres[0], (float) getBounds().getCenterX() - 110, (float) getBounds().getCenterY() - 150);
            Pelota.finJuego = true; // termina el juego (finaliza el hilo)
        }
        if (pelota.getMarcador2() == 5) { // condicion para que gane el jugador 2 con un maximo de 5 puntos
            g.drawString("Ganó " + Configuracion.listaNombres[1], (float) getBounds().getCenterX() - 110, (float) getBounds().getCenterY() - 150);
            Pelota.finJuego = true; // termina el juego (finaliza el hilo)
        }
    }

    private void dibujarTiempo(Graphics2D g) {
        g.setPaint(Color.BLACK);// seleccionamos un color para el tiempo (negro por la constante de clase BLACK)
        Graphics2D g1 = g; // dos objetos que toman lo mismo que tenga g

        Font letraTiem = new Font("helvetica", Font.BOLD, 10);// se crea un objeto de tipo font(fuente o tipo de letra)
        g.setFont(letraTiem);// seleccionamos el tipo de letra a el tiempo
        g.drawString("Tiempo: ", (float) 22, (float) 423);

        Font letraNum = new Font("helvetica", Font.BOLD, 30);// se crea un objeto de tipo font(fuente o tipo de letra)
        g.setFont(letraNum);// seleccionamos el tipo de letra a el tiempo 

        if (pelota.seg < 10) {// condicion para dibujar de manera adecuada el tiempo 
            g1.drawString(Integer.toString(pelota.min) + ":0" + Integer.toString(pelota.seg), (float) 22, 450);
        } else {
            g1.drawString(Integer.toString(pelota.min) + ":" + Integer.toString(pelota.seg), (float) 22, 450);
        }

        if (pelota.min == 0 && pelota.seg == 0) { // condicion para que gane el jugador 2 con un maximo de 5 puntos

            if (pelota.getMarcador1() > pelota.getMarcador2()) { // condicion para que gane el jugador 1
                g.drawString("Ganó " + Configuracion.listaNombres[0], (float) getBounds().getCenterX() - 110, (float) getBounds().getCenterY() - 150);
                Pelota.finJuego = true;
            } else if (pelota.getMarcador2() > pelota.getMarcador1()) { // condicion para que gane el jugador 2 
                g.drawString("Ganó " + Configuracion.listaNombres[1], (float) getBounds().getCenterX() - 110, (float) getBounds().getCenterY() - 150);
                Pelota.finJuego = true;
            } else { // condicion para que finalice en empate 
                g.drawString("¡Empate!", (float) getBounds().getCenterX() - 70, (float) getBounds().getCenterY() - 150);
                Pelota.finJuego = true;
            }

        }

    }

    private void dibujarNombre1(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawString(Configuracion.listaNombres[0], (float) getBounds().getCenterX() - 230, (float) getBounds().getCenterY() - 35);
    }

    private void dibujarNombre2(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawString(Configuracion.listaNombres[1], (float) getBounds().getCenterX() + 45, (float) getBounds().getCenterY() - 35);
    }

}
