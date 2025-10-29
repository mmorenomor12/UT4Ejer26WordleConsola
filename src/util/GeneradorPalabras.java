package util;

public class GeneradorPalabras {
                                        //  00000000001111111111222222222233333333334444
                                        //  01234567890123456789012345678901234567890123
    //private static final String PALABRAS = "pila mesa vaso dado lupa hoja arpa mono seta";
    private static final String PALABRAS = "luz oceano fragmento digital amanecer codigo teatro universo fugaz resonancia espejo silencio viento algoritmo latido horizonte metalico vibrar instante sombra circuito raiz llama destello profundo artificio memoria textura neon eco cristal sueno prisma palabra impulso celeste binario niebla chispa aurora verso enlace vacio atomo reflejo canto orbita secuencia vibracion chispeante existencia";

    public static String obtenerPalabra() {

        int cantidadEspacios = 0;

        //Bucle para contar la cantidad de espacios para luego sumar 1 y sabemos cuantas palabras hay
        //Esto se usará luego para el Math.Random()
        for (int i = 0; i < PALABRAS.length(); i++) {
            if(PALABRAS.charAt(i) == ' '){
                cantidadEspacios ++;
            }
        }
        int cantidadPalabras = cantidadEspacios + 1;

        //System.out.println(cantidadPalabras);

        int numPalabra = (int)(Math.random()*cantidadPalabras)+1;
        int primerEspacio = -1;
        int segundoEspacio = PALABRAS.length();
        int contadorEspacios = -1;

        //Bucle para buscar la palabra aleatoria según numPalabra buscando los espacios ' '
        for (int i = 0; i < PALABRAS.length(); i++) {

            if(PALABRAS.charAt(i) == ' ')
            {
                contadorEspacios ++;
                if(contadorEspacios == numPalabra - 2)
                {
                    primerEspacio = i;
                }
                else if(contadorEspacios == numPalabra -1)
                {
                    segundoEspacio = i;
                }

            }
        }

        return PALABRAS.substring(primerEspacio+1, segundoEspacio);
    }

    public static void main(String[] args) {
        System.out.println(obtenerPalabra());
    }



}
