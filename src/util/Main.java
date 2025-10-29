package util;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numJugadores = 0;
        String nombreJug1 = "";
        String nombreJug2 = "";
        String palabraUsuario = "";

        System.out.println("------------------------------------");
        System.out.println("-     ¡Jueguemos al ahorcado!      -");
        System.out.println("-  ¿Cuántos jugadores sois? (1/2)  -");
        System.out.println("------------------------------------");
        numJugadores = Integer.parseInt(scanner.nextLine());

        if (numJugadores == 1){
        System.out.println("------------------------------------");
        System.out.println("-       ¿Cuál es tu nombre?        -");
        System.out.println("------------------------------------");
        nombreJug1 = scanner.nextLine();
        Wordle wordle = new Wordle();
        System.out.println("-    Intenta adivinar la palabra   -");
            for (int i = 0; i < 6; i++) {
                palabraUsuario = scanner.nextLine();
                wordle.evaluarPalabra(palabraUsuario);
                if(i == 5){
            System.out.println("-         Último intento           -");
                }
                if(wordle.evaluarPalabra(palabraUsuario = wordle.palabra)){
                    System.out.println("Ganaste");
                }
            }
            System.out.println("-       Fin de la partida          -");

        }


    }
}