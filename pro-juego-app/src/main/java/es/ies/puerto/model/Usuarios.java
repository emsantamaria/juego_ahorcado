package es.ies.puerto.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Usuarios {
    private List<Usuario>usuarios;
    String path="jdbc:sqlite:src/main/resources/db/Usuario.db";
    
    private Bbdd bbdd;

    public Usuarios() throws SQLException{
        try {
            bbdd=new Bbdd(path);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        usuarios=bbdd.getAllData(path);
        bbdd.closeConnection();
    }

    public boolean iniciarSesion(String nombre,String password){
        if(nombre==null||nombre.isBlank()){
            return false;
        }
        if(password==null||password.isBlank()){
            return false;
        }
        Usuario buscado=new Usuario( password, nombre);
        for (Usuario usuario : usuarios) {
            if(usuario.equals(buscado)){
                return true;
            }
        }
        return false;
    }

    public boolean findEmail(String email){
        for (Usuario usuario : usuarios) {
            if(usuario.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    public Usuario darUsuarioPorEmail(String email){
        for (Usuario usuario : usuarios) {
            if(usuario.getEmail().equals(email)){
                return usuario;
            }
        }
        return null;
    }

    public Usuario recibirUsurio(String nombre) throws SQLException, ClassNotFoundException{
        bbdd=new Bbdd(path);
        return bbdd.findUsuario(nombre);
   }

    public boolean remove(Usuario usuario) throws SQLException, ClassNotFoundException{
       bbdd=new Bbdd(path);
       usuarios=bbdd.getAllData(path);
        if(usuarios.remove(usuario)){
            try {
                bbdd.deleteData(usuario.getId());
                bbdd.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }   
            return true;
        }
       return false;
    }
    public void escribir(Usuario usuario) throws IOException{
        String path="src/main/resources/es/ies/puerto/usuario.txt";
        File file=new File(path);
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(usuario.toString());
        }
    }

    public Usuario leer(){
        String path="src/main/resources/es/ies/puerto/usuario.txt";
        File file=new File(path);
        Usuario usuario=new Usuario();
        try {
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String line="";
            while ((line=reader.readLine())!=null) {
                String[]array=line.split(",");
                int id=Integer.parseInt(array[3]);
                String nombre=array[0];
                String contrasenia=array[1];
                String email=array[2];
                usuario=new Usuario(id, contrasenia, email, nombre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public boolean update(Usuario usuario) throws SQLException, ClassNotFoundException{
        bbdd=new Bbdd(path);
        usuarios=bbdd.getAllData(path);
        try {
            bbdd.updateData(usuario);
            bbdd.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    public boolean aniadir(Usuario usuario) throws SQLException, ClassNotFoundException{
            bbdd=new Bbdd(path);
        try {
            bbdd.insertData(usuario);
            usuarios=bbdd.getAllData(path);
            bbdd.closeConnection();
        } catch (SQLException e) {
           
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Usuarios(List<Usuario> usuarios, String path, Bbdd bbdd) {
        this.usuarios = usuarios;
        this.path = path;
        this.bbdd = bbdd;
    }

    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bbdd getBbdd() {
        return this.bbdd;
    }

    public void setBbdd(Bbdd bbdd) {
        this.bbdd = bbdd;
    }

    public Usuarios usuarios(List<Usuario> usuarios) {
        setUsuarios(usuarios);
        return this;
    }

    public Usuarios path(String path) {
        setPath(path);
        return this;
    }

    public Usuarios bbdd(Bbdd bbdd) {
        setBbdd(bbdd);
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarios, path, bbdd);
    }

    @Override
    public String toString() {
        return "" + getUsuarios();
    }
   
}
