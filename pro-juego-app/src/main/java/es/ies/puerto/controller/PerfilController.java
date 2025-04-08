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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PerfilController extends AbstractController {
    @FXML private TextField nombreTextFiled;
    @FXML private PasswordField passwordTextField;
    @FXML private TextField emailTextField;
    @FXML private Button saveChangesButton;
    @FXML private Button goBackButton;
    @FXML private Text nameText;
    @FXML private Text passwordText;
    @FXML private Text emailText;
    @FXML protected Text mensaje;
    @FXML private Button goBack;
    @FXML private Button jugar;
    Usuario usuario;
    Usuarios usuarios;
    
    public PerfilController(){

    }

    public void initialize(){

        try {
            usuarios=new Usuarios();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        usuario=usuarios.leer();
        nameText.setText(usuario.getNombre());
        passwordText.setText(usuario.getPassword());
        emailText.setText(usuario.getEmail());
    }

    public void postInitialize(){
    
    }
    /**
     * Metodo que permite guardar los cambios
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    protected void saveChanges() throws SQLException, ClassNotFoundException{
        String password=usuario.getPassword();
        String email=usuario.getEmail();
        String name=usuario.getNombre();
        if(nombreTextFiled==null||nombreTextFiled.getText().isBlank()){
            mensaje.setText("Error, nombre nulo o vacio");
            return;
        }
        if(passwordTextField==null||passwordText.getText().isBlank()){
            mensaje.setText("Error, password vacio  o nulo");
            return;
        }
        if(emailTextField==null||emailTextField.getText().isBlank()){
            mensaje.setText("Error, email vacio  o nulo");
            return;
        }
        usuario=new Usuario(usuario.getId(), passwordTextField.getText(), emailTextField.getText(), nombreTextFiled.getText());
        usuarios.update(usuario);
    }
    /**
     * Metodo que permite volver al login
     */
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
    @FXML
    protected void jugar(){
        System.out.println("pepe");
        try {
            FXMLLoader loader=new FXMLLoader(PrincipalApplication.class.getResource("game.fxml"));
            Parent root=loader.load();
            
            GameController gameController=loader.getController();
            gameController.setPropertiesIdioma(this.getPropertiesIdioma());
            
            Stage stage=(Stage)jugar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
