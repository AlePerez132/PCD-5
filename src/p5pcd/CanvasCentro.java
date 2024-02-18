/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p5pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author alepd
 */
public class CanvasCentro extends Canvas {

    private String clienteMasaje = "";
    private String clienteRehabilitacion = "";
    private String clienteVestuario = "";
    private ArrayList<String> espMasaje = new ArrayList<>();
    private ArrayList<String> espRehabilitacion = new ArrayList<>();

    public CanvasCentro(int Ancho, int Alto) {
        this.setSize(Ancho, Alto);
    }

    public void actualizaColaMasaje(ArrayList<String> ColaMasaje) {

        this.espMasaje = ColaMasaje;
        repaint();

    }

    public void actualizaColaRehab(ArrayList<String> colaRehabilitacion) {

        this.espRehabilitacion = colaRehabilitacion;
        repaint();

    }

    public void ocuparMasaje(String cliente) {

        if (clienteMasaje.isEmpty()) {
            clienteMasaje = cliente;
        } else {
            this.espMasaje.add(cliente);
        }
        repaint();
    }

    public void ocuparRehab(String cliente) {

        if (clienteRehabilitacion.isEmpty()) {
            clienteRehabilitacion = cliente;
        } else {
            this.espRehabilitacion.add(cliente);
        }
        repaint();
    }

    public void ocuparVestuario(String cliente) {
        clienteVestuario = cliente;
        repaint();
    }

    public void desocuparMasaje() {
        clienteMasaje = "";
        if (!this.espMasaje.isEmpty()) {
            clienteMasaje = this.espMasaje.get(0);
            this.espMasaje.remove(0);
        }
        repaint();
    }

    public void desocuparRehab() {
        clienteRehabilitacion = "";
        if (!this.espRehabilitacion.isEmpty()) {
            clienteRehabilitacion = this.espRehabilitacion.get(0);
            this.espRehabilitacion.remove(0);
        }
        repaint();
    }

    public void desocuparVestuario() {
        clienteVestuario = "";
        repaint();
    }

    @Override
    public void paint(Graphics g) {

        Font f1 = new Font("Banschrift", Font.PLAIN, 30);
        Image img = createImage(getWidth(), getHeight());
        Graphics og = img.getGraphics();

        og.setFont(f1);

        og.setColor(Color.white);

        og.drawString("Masajista: ", 100, 200);
        og.drawString("Rehabilitación: ", 480, 200);
        og.drawString("Vestuario: ", 890, 200);

        og.drawString(clienteMasaje, 135, 315);
        og.drawRect(90, 230, 150, 150);

        og.drawString(clienteRehabilitacion, 550, 315);
        og.drawRect(500, 230, 150, 150);
        
        og.drawString(clienteVestuario, 930, 315);
        og.drawRect(880, 230, 150, 150);
        
        og.drawRect(250, 10, 900, 70);
        og.drawRect(350, 475, 800, 70);

        int xMasaje = 260;
        int xRehab = 360;
        String espera;

        og.drawString("Cola de masaje :", 15, 50);
        for (int i = 0; i < espMasaje.size(); i++) {
            espera = espMasaje.get(i);
            og.drawString(espera, xMasaje, 55);
            xMasaje = xMasaje + 70;
        }

        og.drawString("Cola de Rehabilitación :", 10, 525);
        for (int i = 0; i < espRehabilitacion.size(); i++) {
            espera = espRehabilitacion.get(i);
            og.drawString(espera, xRehab, 525);
            xRehab = xRehab + 70;
        }
        g.drawImage(img, 0, 0, null);

    }
    
    
}
