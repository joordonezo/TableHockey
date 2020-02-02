/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesAdic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author GEORGE
 */
public class Configuracion extends JFrame implements ActionListener {

    public static int n;
    public static String[] nuevosNombres;
    public static String[] listaNombres;
    public static String[] velocidad = {"1", "2", "3", "4", "5"};
    public static String[] tiempo = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
    private static JButton listo;
    public static Color colorRizq;
    public static Color colorRder;
    public static Color colorPelota;
    private static JColorChooser colorDer;
    private static JColorChooser colorIzq;
    private static JColorChooser colorPel;
    private static JComboBox combo;
    private static JComboBox combo2;
    private final JComboBox comboVelP;
    private final JComboBox comboTiempo;
    public static Ventana juego;

    public Configuracion() {

        Image icono = new ImageIcon("src/graficos/icon.png").getImage(); // crea un objeto tipo imagen e importa una imagen, la ubicacion del archivo(src/graficos/icon.png)
        setIconImage(icono);
        setTitle("Opciones");
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setSize(1300, 730);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel panel = new JPanel();
        JPanel panelDer = new JPanel();
        JPanel panelIzq = new JPanel();
        JPanel panelCen = new JPanel();
        JPanel panelArr = new JPanel();
        JPanel panelBoton = new JPanel();
        panel.setLayout(new BorderLayout());
        panelCen.setLayout(new GridLayout(1, 2));

        leerArchivo();

        combo = new JComboBox(listaNombres);
        combo2 = new JComboBox(listaNombres);
        panelIzq.add(combo);
        panelDer.add(combo2);

        combo2.setSelectedItem("---Seleccione---");
        combo2.setEditable(true);
        combo.setSelectedItem("---Seleccione---");
        combo.setEditable(true);
        JLabel colorPd = new JLabel("Selecciona un color para la raqueta ");
        JLabel colorPi = new JLabel("Selecciona un color para la raqueta ");
        JLabel colorPe = new JLabel("Selecciona un color para la Pelota ");
        panelDer.add(colorPd);
        panelIzq.add(colorPi);
        panelArr.add(colorPe);
        colorDer = new JColorChooser(new Color(39, 175, 28));
        colorIzq = new JColorChooser(new Color(186, 9, 9));
        colorPel = new JColorChooser(new Color(0, 0, 0));
        panelArr.add(colorPel);
        panelDer.add(colorDer);
        panelIzq.add(colorIzq);

        panelCen.add(panelIzq);
        panelCen.add(panelDer);
        JLabel veloPe = new JLabel("Velocidad para la Pelota ");
        comboVelP = new JComboBox(velocidad);
        panelArr.add(veloPe);
        panelArr.add(comboVelP);

        JLabel tiempoMax = new JLabel("  Y la duracion del tiempo ");
        JLabel min = new JLabel("minutos");
        comboTiempo = new JComboBox(tiempo);
        comboTiempo.setSelectedItem("3");

        panelArr.add(tiempoMax);
        panelArr.add(comboTiempo);
        panelArr.add(min);
        panel.add(panelArr, BorderLayout.NORTH);
        panel.add(panelCen, BorderLayout.CENTER);
        listo = new JButton("Todo listo, ¡JUGAR AHORA!");
        panelBoton.add(listo);
        panel.add(panelBoton, BorderLayout.SOUTH);
        add(panel);

        setVisible(true);
        listo.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        guardarArchivo();
        listo = (JButton) evento.getSource();

        listaNombres[0] = combo.getSelectedItem().toString();
        listaNombres[1] = combo2.getSelectedItem().toString();
        velocidad[0] = comboVelP.getSelectedItem().toString();
        tiempo[0] = comboTiempo.getSelectedItem().toString();
        colorRder = colorRaqDer();
        colorRizq = colorRaqIzq();
        colorPelota = colorPel();
        setVisible(false);
        juego = new Ventana();

    }

    public static Color colorRaqIzq() {

        return colorIzq.getColor();
    }

    public static Color colorRaqDer() {

        return colorDer.getColor();
    }

    public static Color colorPel() {

        return colorPel.getColor();
    }

    public static void guardarArchivo() {

        try {
            File nombre = new File("src/Archivos/Jugadores.txt");
            FileWriter escritor = new FileWriter(nombre, true);
            BufferedWriter bufSalida = new BufferedWriter(escritor);
            PrintWriter salida = new PrintWriter(bufSalida);
            boolean bandera = true;
            boolean bandera2 = true;
            for (int i = 0; i < listaNombres.length; i++) {
                if (combo.getSelectedItem().equals(listaNombres[i])) {
                    bandera = false;
                }
                if (combo2.getSelectedItem().equals(listaNombres[i])) {
                    bandera2 = false;
                }
                if (combo2.getSelectedItem().equals(combo.getSelectedItem())) {
                    bandera2 = false;
                }

            }

            if (bandera) {
                salida.println(combo.getSelectedItem().toString());
            }
            if (bandera2) {
                salida.println(combo2.getSelectedItem().toString());
            }
            salida.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el archivo", "error creando el archivo", ERROR);

        }

    }

    public static void leerArchivo() {
        try {
            File nombre = new File("src/Archivos/Jugadores.txt");
            FileReader lector = new FileReader(nombre);
            BufferedReader entrada = new BufferedReader(lector);
            String dato;
            int i = 1;
            dato = entrada.readLine();
            while (dato != null) {
                dato = entrada.readLine();
                i++;
            }
            entrada.close();

            nombre = new File("src/Archivos/Jugadores.txt");
            lector = new FileReader(nombre);
            entrada = new BufferedReader(lector);
            listaNombres = new String[i];
            i = 0;
            dato = entrada.readLine();
            listaNombres[i] = dato;
            while (dato != null) {
                dato = entrada.readLine();
                listaNombres[i] = dato;
                i++;
            }
            entrada.close();

        } catch (IOException e) {

            try {
                File nombre = new File("src/Archivos/Jugadores.txt");
                FileWriter escritor = new FileWriter(nombre);
                BufferedWriter bufSalida = new BufferedWriter(escritor);
                PrintWriter salida = new PrintWriter(bufSalida);
                salida.println("************");
                salida.println("---Seleccione---");
                salida.close();
                JOptionPane.showMessageDialog(null, "Error leyendo el archivo, ejecute nuevamete el programa", "Error de lectura", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null, "El problema fue solucionado correctamente", "Solucionado", JOptionPane.INFORMATION_MESSAGE);
                System.exit(1);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Imposible solucionar el problema", "Error de lectura", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
