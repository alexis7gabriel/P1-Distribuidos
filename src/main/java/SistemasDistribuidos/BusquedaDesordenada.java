package SistemasDistribuidos;

import java.io.File;
import java.util.Scanner;

public class BusquedaDesordenada implements Runnable {
    private final int numero;

    public BusquedaDesordenada(int numero) {
        this.numero = numero;
    }

    @Override
    public void run() {
        try {
            File archivo = new File("src/main/resources/4millones.txt");
            Scanner scanner = new Scanner(archivo);

            int[] arreglo = new int[4000005];

            int index = 0;
            while (scanner.hasNextLine() && index < 4000000) {
                arreglo[index] = Integer.parseInt(scanner.nextLine().trim());
                index++;
            }

            int numHilos = 2;
            int tamCacho = arreglo.length / numHilos;

            Thread[] hilos = new Thread[numHilos];
            for (int i = 0; i < numHilos; i++) {
                int inicio = i * tamCacho;
                int fin = (i == numHilos - 1) ? arreglo.length : inicio + tamCacho;
                hilos[i] = new Thread(() -> buscarEnSubarreglo(arreglo, inicio, fin, numero));
                hilos[i].start();
            }

            for (Thread hilo : hilos) {
                hilo.join();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buscarEnSubarreglo(int[] arreglo, int inicio, int fin, int numero) {
        for (int i = inicio; i < fin; i++) {
            if (arreglo[i] == numero) {
                System.out.println("Número " + numero + " encontrado en posición " + i + " (Desordenado)");
                return;
            }
        }
        System.out.println("Número " + numero + " no encontrado en el subarreglo [" + inicio + ", " + (fin - 1) + "] (Desordenado)");
    }
}
