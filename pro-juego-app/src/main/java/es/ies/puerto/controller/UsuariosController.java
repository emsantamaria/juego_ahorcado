package es.ies.puerto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Usuario;
import es.ies.puerto.model.Usuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UsuariosController extends AbstractController{
    @FXML Text text1;
    @FXML Text text2;
    @FXML Text text3;
    @FXML Text text4;
    @FXML Text text5;
    @FXML Button goBack;
    Usuarios usuarios;
    List<Text>listText;
    public UsuariosController(){

    }

    public void postInitialize() throws SQLException{
        usuarios=new Usuarios();
        listText=new ArrayList<>(Arrays.asList(text1,text2,text3,text4,text5));
        mostrarUsuarios();
    }


    protected void mostrarUsuarios(){
        for (int i = 0; i < 5; i++) {
            if(i<usuarios.getUsuarios().size()){
                listText.get(i).setText(usuarios.getUsuarios().get(i).toString());
            } 
        }
    }

    @FXML
    protected void siguiente(){
        Usuario usuario=leer(listText.get(4).getText());
        int posicion=usuarios.getUsuarios().indexOf(usuario);
        List<Usuario>usariosList=usuarios.getUsuarios().subList(posicion+1, usuarios.getUsuarios().size());
       if(posicion+6<usuarios.getUsuarios().size()){
        usariosList=usuarios.getUsuarios().subList(posicion+1, posicion+6);
       }
       if(usariosList.size()<5){
        int repeticiones=listText.size()-usariosList.size();
        for (int i = listText.size()-repeticiones; i < listText.size(); i++) {
            listText.get(i).setText(" ");
        }
    }
        for (int i = 0; i < usariosList.size(); i++) {
            listText.get(i).setText(usariosList.get(i).toString());
        }

    }

    @FXML
    protected void anterior(){
        Usuario usuario=leer(listText.get(0).getText());
        int posicion=usuarios.getUsuarios().indexOf(usuario);
        List<Usuario>usuariosList=usuarios.getUsuarios().subList(0, 4);
        if(posicion>4){
            usuariosList=usuarios.getUsuarios().subList(posicion-5, posicion);
        }
     
        for (int i = 0; i < usuariosList.size(); i++) {
            listText.get(i).setText(usuariosList.get(i).toString());
        }
    }

      public Usuario leer(String usuarioString){
        Usuario usuario=new Usuario();
                String[]array=usuarioString.split(",");
                int id=Integer.parseInt(array[3]);
                String nombre=array[0];
                String contrasenia=array[1];
                String email=array[2];
                usuario=new Usuario(id, contrasenia, email, nombre);
           
        return usuario;
    }

    @FXML
    protected void goBack(){
         try {

            FXMLLoader loader = new FXMLLoader(PrincipalApplication.class.getResource("login.fxml"));
            Parent root = loader.load();
    
            LoginController registroController = loader.getController();
            registroController.setPropertiesIdioma(this.getPropertiesIdioma());
    
            Stage stage = (Stage) goBack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
