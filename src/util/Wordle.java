package util;

public class Wordle {

    String palabra = "";

    public Wordle(String palabra){
        this.palabra = palabra;
    }

    public Wordle(){
        palabra = GeneradorPalabras.obtenerPalabra();
    }

    public boolean evaluarPalabra(String palabraUsuario){

        palabraUsuario = completarPalabra(palabraUsuario);

        //Si adivina la palabra, salgo
        if(palabra.equalsIgnoreCase(palabraUsuario)){
            return true;
        }

        //Analizo la palabra
        for (int i = 0; i < palabra.length(); i++) {

            //Para que el main no colapse


            //Pintar de verde si está bien
            //Pintar de rojo si está mal
            //Pintar de amarillo si existe la palabra
            if(this.palabra.charAt(i) == palabraUsuario.charAt(i)){
                ColoresConsola.imprimirEnVerde(palabraUsuario.charAt(i));
            } else if(this.palabra.charAt(i) != palabraUsuario.charAt(i) && tieneLetra(palabraUsuario.charAt(i))){
                ColoresConsola.imprimirEnAmarillo(palabraUsuario.charAt(i));
            } else{
                ColoresConsola.imprimirEnRojo(palabraUsuario.charAt(i));
            }

            //Salto de linea después de pintar
            if(i == palabra.length() -1){
                System.out.println();
            }
        }

        return false;
    }

    private boolean tieneLetra(char letra) {

        //Recorre la palabra y analiza si existe la letra en el total de la palabra
        for (int i = 0; i < palabra.length(); i++) {
            if(palabra.charAt(i) == letra){
                return true;
            }
        }
            return false;
    }

    public String obtenerContadorPalabras(){

        //Obtiene una x por cada palabra
        for (int i = 0; i < palabra.length(); i++) {
            System.out.print("x ");
        }
        System.out.println();
        return "";
    }

    public String completarPalabra(String palabraUsuario){
        //Evaluar la palabra del usuario, para ver si es más corta
        if(palabraUsuario.length() < palabra.length()){
            //Añadir una x por cada caracter sin valor
            for (int j = palabraUsuario.length(); j < palabra.length(); j++) {
                palabraUsuario += "x";
            }
        }
      return palabraUsuario;
    }

}
