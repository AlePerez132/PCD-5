/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p5pcd;

import java.util.Random;

/**
 *
 * @author alepd
 */
public class Masaje extends Thread {

    private Centro centro;
    private Random random = new Random();
    String cliente = "M";

    public Masaje(Centro centro) {
        this.centro = centro;
    }

    @Override
    public void run() {
        try {
            System.out.println("Soy " + cliente + Thread.currentThread().getId() + " intento entrar a masaje");
            centro.entraMasaje(cliente + java.lang.Thread.currentThread().getId());
            System.out.println("Soy " + cliente + Thread.currentThread().getId() + " estoy recibiendo masaje");

            Thread.sleep(random.nextInt(2000) + 2000);
            System.out.println("Soy " + cliente + Thread.currentThread().getId() + " he terminado el masaje");
            centro.saleMasaje(cliente + java.lang.Thread.currentThread().getId());

            System.out.println("Soy " + cliente + Thread.currentThread().getId() + " espero a vestuario");
            Thread.sleep(2000);
            centro.termina();
            System.out.println("Soy " + cliente + Thread.currentThread().getId() + " salgo del vestuario");
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
