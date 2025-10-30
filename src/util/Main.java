package util;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numJugadores = 0;
        boolean volverAPreguntar = true;
        boolean terminarJuego = false;

        while (!terminarJuego) {
            while (volverAPreguntar) {
                System.out.println("------------------------------------");
                System.out.println("-      ¡Juguemos al Wordle!        -");
                System.out.println("-  ¿Cuántos jugadores sois? (1/2)  -");
                System.out.println("------------------------------------");

                String entrada = scanner.nextLine().trim();
                boolean esNumero = true;

                //Comprobar si todos los caracteres son dígitos
                for (int i = 0; i < entrada.length(); i++) {
                    if (!Character.isDigit(entrada.charAt(i))) {
                        esNumero = false;
                        break;
                    }
                }

                if (esNumero && !entrada.isEmpty()) {
                    numJugadores = Integer.parseInt(entrada);

                    if (numJugadores == 1) {
                        volverAPreguntar = false;
                        jugarSolo();
                    } else if (numJugadores == 2) {
                        volverAPreguntar = false;
                        jugarDuo();
                    } else {
                        System.out.println("------------------------------------");
                        System.out.println("-   Debes jugar solo o en pareja   -");
                        System.out.println("------------------------------------");
                    }
                } else {
                    System.out.println("------------------------------------");
                    System.out.println("-  Por favor, introduce 1 o 2 :)   -");
                    System.out.println("------------------------------------");
                }
            }

            terminarJuego = !volverAJugar();
            volverAPreguntar = !terminarJuego;
        }
    }

    public static void jugarSolo(){

        Scanner scanner = new Scanner(System.in);
        String nombreJug1 = "";
        String palabraUsuario = "";
        int contadorWhile = 0;

        System.out.println("------------------------------------");
        System.out.println("-       ¿Cuál es tu nombre?        -");
        System.out.println("------------------------------------");
        nombreJug1 = scanner.nextLine();
        boolean jugar = true;

        while(jugar) {

            System.out.println("------------------------------------");
            System.out.println("        Bienvenido " + nombreJug1 + "          ");
            System.out.println("------------------------------------");
            Wordle wordle = new Wordle();
            System.out.println("------------------------------------");
            System.out.println("-    Intenta adivinar la palabra   -");
            System.out.println("-   Donde x es el total de letras  -");
            System.out.println("-         Tienes 6 intentos        -");
            System.out.println("------------------------------------");
            wordle.obtenerContadorPalabras();

            while (contadorWhile < 5) {
                System.out.println("Intento " + (contadorWhile + 1));
                palabraUsuario = scanner.nextLine();
                if (wordle.evaluarPalabra(palabraUsuario)) {
                    System.out.println("------------------------------------");
                    System.out.println("-         ¡LO ADIVINASTE!          -");
                    System.out.println("------------------------------------");
                    contadorWhile = 0;
                    break;
                }

                contadorWhile++;
            }
            if (contadorWhile == 5) {

                System.out.println("------------------------------------");
                System.out.println("-          Lo intentaste           -");
                System.out.println(" La palabra era: " + wordle.palabra);
                System.out.println("------------------------------------");
                contadorWhile = 0;
                break;
            }

            jugar = volverAJugarModo();

        }
    }

    private static boolean esEnteroPositivo(String s) {
        //Para el switch de jugarDuo() que solo sea números positivos
        if (s == null) return false;
        s = s.trim();
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    private static void jugarDuo() {
        boolean seguirJugando = true;
        Scanner scanner = new Scanner(System.in);
        String nombreJug1 = "";
        String nombreJug2 = "";

        System.out.println("------------------------------------");
        System.out.println("-      ¿Cuál es el nombre del      -");
        System.out.println("-         primer jugador?          -");
        System.out.println("------------------------------------");
        nombreJug1 = scanner.nextLine();

        System.out.println("------------------------------------");
        System.out.println("-      ¿Cuál es el nombre del      -");
        System.out.println("-         segundo jugador?         -");
        System.out.println("------------------------------------");
        nombreJug2 = scanner.nextLine();

        while (seguirJugando) {

            System.out.println("------------------------------------");
            System.out.println("        Bienvenido " + nombreJug1);
            System.out.println("           y " + nombreJug2);
            System.out.println("------------------------------------");

            int opcionDeJuego = 0;
            boolean puedeSeguir = false;

            while (!puedeSeguir) {
                System.out.println("-----------------------------------------------");
                System.out.println("-         Escoged un modo de juego            -");
                System.out.println("- 1. Uno escoge una palabra y el otro adivina -");
                System.out.println("- 2. Adivinar la palabra por turnos           -");
                System.out.println("-----------------------------------------------");

                String entrada = scanner.nextLine().trim();
                boolean esNumero = esEnteroPositivo(entrada);

                if (esNumero) {
                    opcionDeJuego = Integer.parseInt(entrada);
                    if (opcionDeJuego == 1) {
                        jugarConPalabra(nombreJug1, nombreJug2);
                        puedeSeguir = true;
                    }
                    else if (opcionDeJuego == 2) {
                        jugarPorTurnos(nombreJug1, nombreJug2);
                        puedeSeguir = true;
                    }
                    else {
                        System.out.println("-----------------------------------------");
                        System.out.println("- Debes escoger una opcion válida (1/2) -");
                        System.out.println("-----------------------------------------");
                    }
                }
                else {
                    System.out.println("-----------------------------------------");
                    System.out.println("- Debes escoger una opcion válida (1/2) -");
                    System.out.println("-----------------------------------------");
                }
            }

            seguirJugando = volverAJugarModo();
        }
    }

    private static void jugarPorTurnos(String nombreJugador1, String nombreJugador2) {

        Scanner scanner = new Scanner(System.in);
        String palabraUsuario = "";
        int rondaJugador1 = 0;
        int rondaJugador2 = 0;

        Wordle wordle = new Wordle();

        System.out.println("------------------------------------");
        System.out.println("-    Intentad adivinar la palabra  -");
        System.out.println("-   Donde x es el total de letras  -");
        System.out.println("-   Tiene 6 intentos cada jugador  -");
        System.out.println("------------------------------------");

        // Muestra las x una vez
        wordle.obtenerContadorPalabras();
        System.out.println();

        for (int turnoTotal = 0; turnoTotal < 12; turnoTotal++) {
            boolean turnoDeJ1 = (turnoTotal % 2 == 0);

            if (turnoDeJ1) {
                // Si J1 ya consumió sus 6 intentos, saltamos su turno
                if (rondaJugador1 >= 6) continue;

                System.out.println("Intento " + (rondaJugador1 + 1));
                System.out.println("Turno de " + nombreJugador1);

                palabraUsuario = scanner.nextLine();

                if (wordle.evaluarPalabra(palabraUsuario)) {
                    System.out.println("------------------------------------");
                    System.out.println("-         ¡LO ADIVINASTE!          -");
                    System.out.println("Muy bien " + nombreJugador1);
                    System.out.println("------------------------------------");
                    return;
                }
                rondaJugador1++;

            } else {
                // Turno J2
                if (rondaJugador2 >= 6) continue;

                System.out.println("Intento " + (rondaJugador2 + 1));
                System.out.println("Turno de " + nombreJugador2);

                palabraUsuario = scanner.nextLine();

                if (wordle.evaluarPalabra(palabraUsuario)) {
                    System.out.println("------------------------------------");
                    System.out.println("-         ¡LO ADIVINASTE!          -");
                    System.out.println("Muy bien " + nombreJugador2);
                    System.out.println("------------------------------------");
                    return;
                }
                rondaJugador2++;
            }
        }

        //Más de 6 intentos de ambos
        System.out.println("------------------------------------");
        System.out.println("-         Lo intentasteis          -");
        System.out.println(" La palabra era: " + wordle.palabra);
        System.out.println("------------------------------------");
    }


    private static void jugarConPalabra(String nombreJugador1, String nombreJugador2) {
        Scanner scanner = new Scanner(System.in);
        String palabraUsuario = "";
        String palabraIntroducida = "";
        boolean juegoAcabado = false;
        int contadorWhile = 0;
        while (!juegoAcabado) {

                if (contadorWhile % 2 == 0) {
                    System.out.println("------------------------------------");
                    System.out.println(nombreJugador1 + " escoge una palabra para " + nombreJugador2);
                    System.out.println("------------------------------------");
                    palabraIntroducida = scanner.nextLine();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("                        ╔═══════════╗");
                    System.out.println("        /\\_/\\           ║  Palabra  ║");
                    System.out.println("       ( o.o )          ║  oculta   ║");
                    System.out.println("        > ^ <           ╚═══════════╝");
                    Wordle wordle = new Wordle(palabraIntroducida);
                    System.out.println("------------------------------------");
                    System.out.println("-    Intenta adivinar la palabra   -");
                    System.out.println("-   Donde x es el total de letras  -");
                    System.out.println("-         Tienes 6 intentos        -");
                    System.out.println("------------------------------------");
                    System.out.println("Intento " + (contadorWhile + 1));
                    wordle.obtenerContadorPalabras();

                    while (contadorWhile < 7) {
                        contadorWhile++;

                        palabraUsuario = scanner.nextLine();
                        if (wordle.evaluarPalabra(palabraUsuario)) {
                            System.out.println("------------------------------------");
                            System.out.println("-         ¡LO ADIVINASTE!          -");
                            System.out.println("------------------------------------");
                            break;
                        }
                        if (contadorWhile < 6) {
                            System.out.println("Intento " + (contadorWhile + 1));
                        }


                    }
                    if (contadorWhile >= 7) {
                        System.out.println("------------------------------------");
                        System.out.println("-          Lo intentaste           -");
                        System.out.println(" La palabra era: " + wordle.palabra);
                        System.out.println("------------------------------------");
                    }
                }
                else {
                    contadorWhile = 0;
                    System.out.println("------------------------------------");
                    System.out.println(nombreJugador2 + " escoge una palabra para " + nombreJugador1);
                    System.out.println("------------------------------------");
                    palabraIntroducida = scanner.nextLine();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("                        ╔═══════════╗");
                    System.out.println("        /\\_/\\           ║  Palabra  ║");
                    System.out.println("       ( o.o )          ║  oculta   ║");
                    System.out.println("        > ^ <           ╚═══════════╝");
                    Wordle wordle = new Wordle(palabraIntroducida);
                    System.out.println("------------------------------------");
                    System.out.println("-    Intenta adivinar la palabra   -");
                    System.out.println("-   Donde x es el total de letras  -");
                    System.out.println("-         Tienes 6 intentos        -");
                    System.out.println("------------------------------------");
                    System.out.println("Intento " + (contadorWhile + 1));
                    wordle.obtenerContadorPalabras();

                    while (contadorWhile < 7) {
                        contadorWhile++;
                        palabraUsuario = scanner.nextLine();
                        if (wordle.evaluarPalabra(palabraUsuario)) {
                            System.out.println("------------------------------------");
                            System.out.println("-         ¡LO ADIVINASTE!          -");
                            System.out.println("------------------------------------");
                            juegoAcabado = true;
                            break;
                        }
                        if (contadorWhile < 6) {
                            System.out.println("Intento " + (contadorWhile + 1));
                        }
                    }
                    if (contadorWhile >= 7) {
                        System.out.println("------------------------------------");
                        System.out.println("-          Lo intentaste           -");
                        System.out.println(" La palabra era: " + wordle.palabra);
                        System.out.println("------------------------------------");
                        juegoAcabado = true;
                    }
                }
            }
    }

    private static boolean volverAJugarModo() {
        //Puede acabar el modo
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------------");
        System.out.println("-¿Desea volver a jugar este modo?(S/N)-");
        System.out.println("---------------------------------------");

        return ProcesarRespuestaVolverAJugar(scanner.next());
    }

    private static boolean volverAJugar() {
        //Acaba el juego
            Scanner scanner = new Scanner(System.in);
            System.out.println("------------------------------------");
            System.out.println("-   ¿Quiere seguir jugando?(S/N)   -");
            System.out.println("------------------------------------");

            return ProcesarRespuestaVolverAJugar(scanner.next());
    }

    private static boolean ProcesarRespuestaVolverAJugar(String respuesta){
        //Analiza la entrada para volverAJugarModo y volverAJugar
        Scanner scanner = new Scanner(System.in);
        while(true){
            if(respuesta.equalsIgnoreCase("S")){
                return true;
            }else if(respuesta.equalsIgnoreCase("N")){
                return false;
            }else {
                System.out.println("------------------------------------");
                System.out.println("-  Escoja una opción válida (S/N)  -");
                System.out.println("------------------------------------");
               respuesta = scanner.nextLine();
            }
        }
    }
}