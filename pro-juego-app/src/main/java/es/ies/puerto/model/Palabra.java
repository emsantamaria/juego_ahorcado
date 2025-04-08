package es.ies.puerto.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Palabra {
    String palabraString;
    String dificultad;

    public Palabra() {
    }

    public Palabra(String palabraString, String dificultad) {
        this.palabraString = palabraString;
        this.dificultad = dificultad;
    }

    public String getPalabraString() {
        return this.palabraString;
    }

    public void setPalabraString(String palabraString) {
        this.palabraString = palabraString;
    }

    public String getDificultad() {
        return this.dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Palabra)) {
            return false;
        }
        Palabra palabra = (Palabra) o;
        return Objects.equals(palabraString, palabra.palabraString) && Objects.equals(dificultad, palabra.dificultad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(palabraString, dificultad);
    }

    @Override
    public String toString() {
        return "{" +
            " palabraString='" + getPalabraString() + "'" +
            ", dificultad='" + getDificultad() + "'" +
            "}";
    }
    public List<String> letras(Palabra palabra){
        List<String> letras=new ArrayList<>();
        switch (palabra.getDificultad()) {
            case "facil":
                int numeroLetras=(int)((palabra.getPalabraString().length()*20)/100);
                if(numeroLetras==0){
                    numeroLetras=1;
                }
                aniadirLetra(palabra.getPalabraString(), letras, numeroLetras);
                break;
            case "medio":
                numeroLetras=(int)((palabra.getPalabraString().length()*10)/100);
                if(numeroLetras==0){
                numeroLetras=1;
                }
                aniadirLetra(palabra.getPalabraString(), letras, numeroLetras);
                break;
            default:
                break;
        }

        return letras;
    }

    public void aniadirLetra(String letras,List<String> palabra,int numeroPalabra){
        while(palabra.size()<numeroPalabra){
            String letra=letraRandom(letras);
            if(!palabra.contains(letra)){
                palabra.add(letra);
            }
        }
    }

    public String letraRandom(String palabra){
        String letra="";
        String[]letras=palabra.split("");
        letra=letras[(int)(Math.random()*letra.length())];
        return letra;
    }
    
}
