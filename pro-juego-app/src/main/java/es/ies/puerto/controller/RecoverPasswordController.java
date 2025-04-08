package es.ies.puerto.controller;

import java.sql.SQLException;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Usuario;
import es.ies.puerto.model.Usuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RecoverPasswordController extends AbstractController {
    @FXML private Text textMensaje;
    @FXML private Button buttonBack;
    @FXML private TextField filedEmail;
    @FXML private Text textUsuario;
    @FXML private Button buttonRegistrar;
    Usuarios usuarios;
    public RecoverPasswordController(){
        
    }

    public void postInitialize() throws SQLException{
        usuarios=new Usuarios();
    }
    /**
     * Metodo que permite recuperar una contrasenia olvidada
     */
    @FXML
    protected void recuperar(){
        if(filedEmail==null||filedEmail.getText().isBlank()){
            textMensaje.setText("Email no valido");
            return;
        }
        if(!usuarios.findEmail(filedEmail.getText())){
            textMensaje.setText("No se ha encontrado un usuario con ese email");
            return;
        }
        Usuario usuario=usuarios.darUsuarioPorEmail(filedEmail.getText());
        textMensaje.setText("Su contraseña es "+usuario.getPassword());
    }
    /**
     * Metodo que permite volver al login
     */
    @FXML
    protected void goBacTokMain(){
        try {

            FXMLLoader loader = new FXMLLoader(PrincipalApplication.class.getResource("login.fxml"));
            Parent root = loader.load();
    
            LoginController registroController = loader.getController();
            registroController.setPropertiesIdioma(this.getPropertiesIdioma());
    
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
