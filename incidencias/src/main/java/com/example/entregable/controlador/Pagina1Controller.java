package com.example.entregable.controlador;

import com.example.entregable.Application;
import javafx.css.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.ResourceBundle;
public class Pagina1Controller implements Initializable {
    @FXML
    private Label usuario;

    @FXML
    private Label password;

    @FXML
    private TextField usuariotext;

    @FXML
    private TextField passwordtext;

    @FXML
    private Button btnentrar;
    @FXML
    private void onEntrar(ActionEvent event) {
        String usuario = usuariotext.getText().toString();
        String password = passwordtext.getText().toString();

        try{
            Connection con = null;

            Properties props = new Properties();
            props.put("user", "root");
            props.put("password", "CESJUANPABLOII!");

            // Abro la conexion
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/incidencias?enabledTLSProtocols=TLSv1.2", props);
            // realizo la consulta

            String sql = "SELECT * FROM Users WHERE usuario = ? and password = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();


            if(!rs.next()){
                infoBox2("Introduce correctamente usuario y Password", "Failed", null);
            }else{
                infoBox1("Login correcto", "Success", null);
                try {
                    // Cargo la vista
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Application.class.getResource("vista/pagina2-view.fxml"));
                    // Cargo el padre
                    Parent ventana = loader.load();

                    // Obtengo el controlador
                    Pagina2Controller controlador = loader.getController();

                    // Creo la scene y el stage
                    Scene scene = new Scene(ventana);
                    Stage stage = new Stage();

                    // Asocio el stage con el scene
                    stage.setScene(scene);
                    stage.show();
                    //en caso de cerrar la pagina2en la parte superior en la x,llamamos a la funcion cierrapagina2_confirmacion()
                    stage.setOnHidden(e->controlador.cierrapagina2_confirmacion());
                    // Ciero la ventana donde estoy
                    Stage myStage = (Stage) this.btnentrar.getScene().getWindow();
                    myStage.close();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void infoBox2(String infoMessage, String titleBar, String headerMessage)
    {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        Label label = new Label(infoMessage);
        label.setStyle("-fx-text-fill: red; ");
        alert.getDialogPane().setContent(label);
        alert.showAndWait();
    }
    public static void infoBox1(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}