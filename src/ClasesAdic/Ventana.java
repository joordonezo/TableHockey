/*Ventana.java
 * Descripción: Crea la ventana que contiene el tablero de juego
 * Autores: Jorge Luis Ordoñez Ospina, Santiago Ramírez Gallego, Hans Rivera Londoño, Mariana Rodas Hernández
 */
package ClasesAdic;

import java.awt.Image; // importa clase que Image para usar sus métodos
import javax.swing.*; // importa las clases necesarias para usar el aspecto gráfico 

public class Ventana extends JFrame {

    private final int ANCHO = 800, ALTO = 500; // tamaño de la ventana 
    public final Tablero tablero; // declaracion del objeto tablero (para mostrar el tablero de juego)  
    private final Hilo hilo; // declaracion del obejeto tipo Hilo para que el programa se guie de un una unica secuencia

    public Ventana() {

        setTitle("Hockey de Mesa");// método heredados de JFrame
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setSize(ANCHO, ALTO); // método heredados de JFrame 

        setLocationRelativeTo(null);//para tener el juego en el centro de la pantalla // método heredados de JFrame 

        setResizable(false); // no permite modificar el tamaño // método heredados de JFrame 
        setVisible(true); // método heredados de JFrame 

        Image icono = new ImageIcon("src/graficos/icon.png").getImage(); // crea un objeto tipo imagen e importa una imagen, la ubicacion del archivo(src/graficos/icon.png)
        setIconImage(icono); // selecciona la imagen "icon" como icono del programa  

        // termina la ejecucion del juego cuando se cierra la ventana // método heredados de JFrame 
        tablero = new Tablero(); // crea (separa el espacio en la memoría) un obejto tipo tablero
        add(tablero); // método heredados de JFrame para añadir el tablero a la ventana
        addKeyListener(new EventoTeclado());//  para que el programa sepa que tecla se presiona, para la direccion de las raquetas
        hilo = new Hilo(tablero); // crea (separa el espacio en la memoría) un obejto tipo hilo

        hilo.start(); // inicia el hilo para que comience la ejecucion del juego 

    }

}
