package util;

public class Wordle {

    String palabra = "";

    public Wordle(String palabra){
        this.palabra = palabra;
    }

    public Wordle(){
        palabra = GeneradorPalabras.obtenerPalabra();
    }

    public void evaluarPalabra(String palabraUsuario){

    }

}
