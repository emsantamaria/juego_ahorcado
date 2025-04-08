package es.ies.puerto.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bbdd{

    private Connection con;

    public Bbdd(String dbUrl) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        try {
            this.con = DriverManager.getConnection(dbUrl);
        } catch (Exception e) {
            System.out.println("conexion fallida");
        }
        
    }

    public void insertData(Usuario usuario) throws SQLException {
        String qry = "INSERT INTO usuario (nombre, contrasenia, email) VALUES (?, ?, ?)";
        try (PreparedStatement st = con.prepareStatement(qry)) {
            st.setString(1, usuario.getNombre());
            st.setString(2, usuario.getPassword());
            st.setString(3, usuario.getEmail());
            st.executeUpdate();
        }
    }

    public void updateData(Usuario usuario) throws SQLException {
        String qry = "UPDATE usuario SET nombre=?, contrasenia=?, email=? WHERE ID=?";
        try (PreparedStatement st = con.prepareStatement(qry)) {
            st.setString(1, usuario.getNombre());
            st.setString(2, usuario.getPassword());
            st.setString(3, usuario.getEmail());
            st.setInt(4, usuario.getId());
            st.executeUpdate();
        }
    }

    public void deleteData(int id) throws SQLException {
        String qry = "DELETE FROM usuarios WHERE ID=?";
        try (PreparedStatement st = con.prepareStatement(qry)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }

    public List<Usuario> getAllData(String srcDdbb) throws SQLException {
        PreparedStatement sentencia = con.prepareStatement("SELECT ID, nombre, contrasenia, email FROM usuario");
        ResultSet resultado = sentencia.executeQuery();
        List<Usuario>usuarios=new ArrayList<>();
        while (resultado.next()) {
            int id=resultado.getInt("id");
            String nombre=resultado.getString("nombre");
            String contrasenia=resultado.getString("contrasenia");
            String email=resultado.getString("email");
            Usuario usuario=new Usuario(id, contrasenia, email, nombre);
            usuarios.add(usuario);
        }

        return usuarios;
    }

    public void closeConnection() throws SQLException {
        con.close();
    }

    public Usuario findUsuario(String nombre) throws SQLException{
        Usuario usuario=new Usuario();
        PreparedStatement sentencia=con.prepareStatement("select * from usuario where nombre='"+nombre+"'");
        ResultSet resultSet=sentencia.executeQuery();
        while (resultSet.next()) {
            int id=resultSet.getInt("id");
            String nombreSearch=resultSet.getString("nombre");
            String password=resultSet.getString("contrasenia");
            String email=resultSet.getString("email");
            usuario=new Usuario(id, password, email, nombreSearch);
        }
        return usuario;
    }

    public List<Palabra> readPalabras(String dificultad) throws SQLException{
        List<Palabra>palabras=new ArrayList<>();
        Palabra palabra=new Palabra();
        PreparedStatement sentencia=con.prepareStatement("select * from palabra where dificultad='"+dificultad+"'");
        ResultSet resultSet=sentencia.executeQuery();
        while (resultSet.next()) {
            String palabraString=resultSet.getString("palabraString");
            palabra=new Palabra(palabraString, dificultad);
            palabras.add(palabra);
        }
        return palabras;
    }
}