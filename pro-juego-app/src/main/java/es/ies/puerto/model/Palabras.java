package es.ies.puerto.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Palabras {
    List<Palabra>facil;
    List<Palabra>medio;
    List<Palabra>dificil;
    Bbdd bbdd;
    String path="jdbc:sqlite:src/main/resources/db/Palabras.db";
    public Palabras(String dificultad) throws ClassNotFoundException, SQLException{
        bbdd=new Bbdd(path);
        facil=bbdd.readPalabras(dificultad);
    }

    public Palabras(List<Palabra> facil, List<Palabra> medio, List<Palabra> dificil, Bbdd bbdd, String path) {
        this.facil = facil;
        this.medio = medio;
        this.dificil = dificil;
        this.bbdd = bbdd;
        this.path = path;
    }

    public List<Palabra> getFacil() {
        return this.facil;
    }

    public void setFacil(List<Palabra> facil) {
        this.facil = facil;
    }

    public List<Palabra> getMedio() {
        return this.medio;
    }

    public void setMedio(List<Palabra> medio) {
        this.medio = medio;
    }

    public List<Palabra> getDificil() {
        return this.dificil;
    }

    public void setDificil(List<Palabra> dificil) {
        this.dificil = dificil;
    }

    public Bbdd getBbdd() {
        return this.bbdd;
    }

    public void setBbdd(Bbdd bbdd) {
        this.bbdd = bbdd;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Palabras facil(List<Palabra> facil) {
        setFacil(facil);
        return this;
    }

    public Palabras medio(List<Palabra> medio) {
        setMedio(medio);
        return this;
    }

    public Palabras dificil(List<Palabra> dificil) {
        setDificil(dificil);
        return this;
    }

    public Palabras bbdd(Bbdd bbdd) {
        setBbdd(bbdd);
        return this;
    }

    public Palabras path(String path) {
        setPath(path);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Palabras)) {
            return false;
        }
        Palabras palabras = (Palabras) o;
        return Objects.equals(facil, palabras.facil) && Objects.equals(medio, palabras.medio) && Objects.equals(dificil, palabras.dificil) && Objects.equals(bbdd, palabras.bbdd) && Objects.equals(path, palabras.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facil, medio, dificil, bbdd, path);
    }

    @Override
    public String toString() {
        return "{" +
            " facil='" + getFacil() + "'" +
            ", medio='" + getMedio() + "'" +
            ", dificil='" + getDificil() + "'" +
            ", bbdd='" + getBbdd() + "'" +
            ", path='" + getPath() + "'" +
            "}";
    }

    public String ahorcado(String letra,Palabra palabra){
        String texto="";
        char[]arrayChar=palabra.getPalabraString().toCharArray();
        if(letra.equals(palabra.getPalabraString())){
            return letra;
        }

        for (int i = 0; i < arrayChar.length; i++) {
            if((arrayChar[i]+"").equals(letra)){
                texto+=letra;
            }
            else{
                texto+="_";
            }
        }

        return texto;
    }

    public String comparacion(String palabra,String palabraText){
        String text="";
        char[]textPalabra=palabra.toCharArray();
        char[]palabraMensaje=palabraText.toCharArray();
        for (int i = 0; i < palabraMensaje.length; i++) {
            if(textPalabra[i]=='_'){
                textPalabra[i]=palabraMensaje[i];
            }
            text+=textPalabra[i];
        }

        return text;
    }
}
