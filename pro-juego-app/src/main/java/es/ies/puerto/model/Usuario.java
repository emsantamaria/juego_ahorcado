package es.ies.puerto.model;

import java.util.Objects;

public class Usuario {
    private String nombre;
    private String password;
    private String email;
    private int id;

    public Usuario() {
    }

    public Usuario( String password,String nombre ) {
        this.nombre = nombre;
        this.password = password;
    }

    public Usuario(String password, String email,String nombre ) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
    }

    public Usuario( int id,String password, String email,String nombre ) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.id=id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Usuario nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Usuario password(String password) {
        setPassword(password);
        return this;
    }

    public Usuario email(String email) {
        setEmail(email);
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre) && Objects.equals(password, usuario.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash( nombre, password);
    }

    @Override
    public String toString() {
        return  getNombre() + "" +
            "," + getPassword() + "" +
            "," + getEmail() + "" +
            "," + getId();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario id(int id) {
        setId(id);
        return this;
    }

}
