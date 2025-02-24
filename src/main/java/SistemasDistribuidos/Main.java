package SistemasDistribuidos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese un número para buscar: ");
        int numero = scanner.nextInt();

        Thread procesoOrdenado = new Thread(new BusquedaOrdenada(numero));
        procesoOrdenado.start();

        Thread procesoDesordenado = new Thread(new BusquedaDesordenada(numero));
        procesoDesordenado.start();

        try {
            procesoOrdenado.join();
            procesoDesordenado.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Búsqueda completada.");
    }
}