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
        for (int i = 0; i < palabra.length(); i++) {

            if(this.palabra.charAt(i) == palabraUsuario.charAt(i)){
                ColoresConsola.imprimirEnVerde(palabraUsuario.charAt(i));
            } else if(this.palabra.charAt(i) != palabraUsuario.charAt(i) && tieneLetra(palabraUsuario.charAt(i))){
                ColoresConsola.imprimirEnAmarillo(palabraUsuario.charAt(i));
            } else{
                ColoresConsola.imprimirEnRojo(palabraUsuario.charAt(i));
            }
            if(palabra.equalsIgnoreCase(palabraUsuario)){
                return true;
            }
        }
        return false;
    }

    private boolean tieneLetra(char letra) {
        for (int i = 0; i < palabra.length(); i++) {
            if(palabra.charAt(i) == letra){
                return true;
            }
        }
            return false;
    }

}
