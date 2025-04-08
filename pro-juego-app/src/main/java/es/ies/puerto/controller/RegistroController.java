package es.ies.puerto.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.Usuario;
import es.ies.puerto.model.Usuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegistroController extends AbstractController {
    Properties properties;
    
    @FXML TextField textFiledUsuario;

    @FXML Text textMensaje;

    @FXML Button buttonRegistrar;

    @FXML PasswordField textFieldPassword;

    @FXML PasswordField textFieldPasswordRepit;

    @FXML TextField textFieldEmail;

     @FXML Button goBack;

    @FXML
    private Text textUsuario;

    @FXML
    private Text textContrasenia;

    Usuarios usuarios;

    
    public void initialize() throws SQLException {
        usuarios=new Usuarios();
    }

    public void postInitialize() {
        Properties properties = getPropertiesIdioma();
        if (properties != null) {
            textUsuario.setText(properties.getProperty("textUsuario", "Usuario"));
            textContrasenia.setText(properties.getProperty("textContrasenia", "Contraseña"));
        } else {
            textUsuario.setText("Usuario");
            textContrasenia.setText("Contraseña");
        }
    }


    @FXML
    protected void onClickRegistar() throws SQLException, IOException, ClassNotFoundException  {
            if (textFieldPassword == null ||  textFieldPassword.getText().isEmpty()
                    || textFieldPasswordRepit == null || textFieldPasswordRepit.getText().isEmpty()) {
                textMensaje.setText("¡El password no puede ser nulo o vacio!");
                return;
            }
            
            if (!textFieldPassword.getText().equals(textFieldPasswordRepit.getText())) {
                textMensaje.setText("¡El password no es correcto");
                return;
            }
            if(usuarios.iniciarSesion(textFiledUsuario.getText(), textFieldPassword.getText())){
                textMensaje.setText("Ya existe un usuario con ese nombre");
                return;
            }
            if(usuarios.findEmail(textFieldEmail.getText())){
                textMensaje.setText("Ya existe un usuario con ese email");
                return;
            }
            
            Usuario usuari=new Usuario(textFieldPassword.getText(), textFieldEmail.getText(), textFiledUsuario.getText());
            usuarios.aniadir(usuari);
            usuarios=new Usuarios();
            usuari=usuarios.recibirUsurio(textFiledUsuario.getText());
            usuarios.escribir(usuari);
            perfil();
    }
    @FXML
    protected void perfil(){
        try {

            FXMLLoader loader = new FXMLLoader(PrincipalApplication.class.getResource("perfil.fxml"));
            Parent root = loader.load();
    
            PerfilController registroController = loader.getController();
            registroController.setPropertiesIdioma(this.getPropertiesIdioma());
            
            registroController.postInitialize();
    
            Stage stage = (Stage) buttonRegistrar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    protected void goBacTokMain(){
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
