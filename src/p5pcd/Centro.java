/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p5pcd;

import java.util.ArrayList;

/**
 *
 * @author alepd
 */
public class Centro {

    CanvasCentro canvas;

    public Centro(CanvasCentro canvas) {

        this.canvas = canvas;

    }

    private boolean MLibre = true;
    private boolean RLibre = true;
    private boolean VLibre = true;
    private boolean MEnRehab = false;
    private ArrayList<String> ColaMasaje = new ArrayList<String>();
    private ArrayList<String> ColaRehabilitacion = new ArrayList<String>();

    public synchronized void entraMasaje(String cliente) throws InterruptedException {

        ColaMasaje.add(cliente);
        canvas.actualizaColaMasaje(ColaMasaje);
        while (!MLibre && !RLibre) {
            wait();
        }
        if (!ColaMasaje.isEmpty()) {
            ColaMasaje.remove(0);
        }

        canvas.actualizaColaMasaje(ColaMasaje);
        if (MLibre) {
            MLibre = false;
             canvas.ocuparMasaje(cliente);
        } else {
            MEnRehab = true;
            RLibre = false;
             canvas.ocuparRehab(cliente);
        }

    }

    public synchronized void entraRehabilitacion(String cliente) throws InterruptedException {

        ColaRehabilitacion.add(cliente);
        canvas.actualizaColaRehab(ColaRehabilitacion);

        while (!RLibre) {
            wait();
        }

        if (!ColaRehabilitacion.isEmpty()) {
            ColaRehabilitacion.remove(0);
        }
        RLibre = false;
        canvas.actualizaColaRehab(ColaRehabilitacion);
        canvas.ocuparRehab(cliente);
    }

    public synchronized void saleMasaje(String cliente) throws InterruptedException {

        while (!VLibre) {
            wait();
        }

        if (MEnRehab) {
            MEnRehab = false;
            RLibre = true;
            VLibre = false;
            canvas.desocuparRehab();
            canvas.ocuparVestuario(cliente);
        } else {
            MLibre = true;
            VLibre = false;
            canvas.desocuparMasaje();
            canvas.ocuparVestuario(cliente);

        }
        notifyAll();
    }

    public synchronized void saleRehabilitacion(String cliente) throws InterruptedException {
        while (!VLibre) {
            wait();
        }
        RLibre = true;
        VLibre = false;
        canvas.desocuparRehab();
        canvas.ocuparVestuario(cliente);
        notifyAll();
    }

    public synchronized void termina() {
        VLibre = true;
        notifyAll();
        canvas.desocuparVestuario();
    }
}
