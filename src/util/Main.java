package util;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numJugadores = 0;
        String nombreJug1 = "";
        String nombreJug2 = "";
        String palabraUsuario = "";
        int contadorWhile = 0;

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
            System.out.println("------------------------------------");
            System.out.println("        Bienvenido " + nombreJug1 + "          ");
            System.out.println("------------------------------------");
            Wordle wordle = new Wordle();
            System.out.println("------------------------------------");
            System.out.println("-    Intenta adivinar la palabra   -");
            System.out.println("-   Donde x es el total de letras  -");
            System.out.println("-         Tienes 6 intentos        -");
            System.out.println("------------------------------------");
            System.out.println("Intento " + (contadorWhile + 1));
            wordle.obtenerContadorPalabras();

                while (contadorWhile < 7){

                    palabraUsuario = scanner.nextLine();
                    if(wordle.evaluarPalabra(palabraUsuario)){
                        System.out.println("------------------------------------");
                        System.out.println("-         ¡LO ADIVINASTE!          -");
                        System.out.println("------------------------------------");
                        return;
                    }
                    if(contadorWhile < 6){System.out.println("Intento " + (contadorWhile + 1));}
                    contadorWhile ++;


                }
                if(contadorWhile >=7){
                    System.out.println("------------------------------------");
                    System.out.println("-          Lo intentaste           -");
                    System.out.println(" La palabra era: " + wordle.palabra);
                    System.out.println("------------------------------------");
                }

        }


    }

    public void jugarSolo(){

    }

}