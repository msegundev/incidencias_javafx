package com.example.entregable.controlador;

import com.example.entregable.Application;
import com.example.entregable.modelo.Incidencia;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Pagina3Controller implements Initializable {

    @FXML
    private Label fechai;

    @FXML
    private TextField textfecha;

    @FXML
    private Label nifr;

    @FXML
    private TextField textnifr;

    @FXML
    private Label maquinai;

    @FXML
    private TextField textmaquinai;

    @FXML
    private Label prioridadi;

    @FXML
    private TextField textprioridadi;

    @FXML
    private Label responsablei;

    @FXML
    private TextField textresponsablei;

    @FXML
    private Label descipcioni;

    @FXML
    private TextArea textdescripcioni;

    @FXML
    private Incidencia incidencia;

    @FXML
    private ObservableList<Incidencia> incidencias;

    @FXML
    private ImageView ivimage;



    public void initAttributtes(ObservableList<Incidencia> incidencias, Incidencia i)
    {

        this.incidencias=incidencias;
        this.incidencia = i;
        this.textdescripcioni.setText(i.getDescripcion());
        this.textfecha.setText(i.getFecha().toString());
        this.textresponsablei.setText(i.getResponsab());
        this.textprioridadi.setText(i.getPriori());
        this.textnifr.setText(i.getNifresponsable());
        this.textmaquinai.setText(i.getMaqui());
        System.out.println(""+i.getMaqui());
/**
 * Se ha tenido que hacer la carga de imagenes por ruta absoluta, ya que de forma relativa me daba error de URL
 */

        if(i.getMaqui().equals("AM-01"))
        {
            Image uno = new Image("C:\\Users\\lenovo2021\\Documents\\entregable\\src\\main\\resources\\com\\example\\entregable\\imagenes/1.jpg");

            System.out.println(""+i.getMaqui());
            this.ivimage.setImage(uno);
        }
        else if(i.getMaqui().equals("AM-02"))
        {
            Image dos = new Image("C:\\Users\\lenovo2021\\Documents\\entregable\\src\\main\\resources\\com\\example\\entregable\\imagenes/2.jpg");
            System.out.println(""+i.getMaqui());
            this.ivimage.setImage(dos);
        }
        else if(i.getMaqui().equals("AM-03"))
        {
            Image tres = new Image("C:\\Users\\lenovo2021\\Documents\\entregable\\src\\main\\resources\\com\\example\\entregable\\imagenes/3.jpg");
            System.out.println(""+i.getMaqui());
            this.ivimage.setImage(tres);
        }
        else if(i.getMaqui().equals("K-1"))
        {
            Image cuatro = new Image("C:\\Users\\lenovo2021\\Documents\\entregable\\src\\main\\resources\\com\\example\\entregable\\imagenes/4.jpeg");
            System.out.println(""+i.getMaqui());
            this.ivimage.setImage(cuatro);
        }
        else if(i.getMaqui().equals("K-2"))
        {
            Image cinco = new Image("C:\\Users\\lenovo2021\\Documents\\entregable\\src\\main\\resources\\com\\example\\entregable\\imagenes/5.jpg");
            System.out.println(""+i.getMaqui());
            this.ivimage.setImage(cinco);
        }
        else if(i.getMaqui().equals("K-3"))
        {
            Image seis = new Image("C:\\Users\\lenovo2021\\Documents\\entregable\\src\\main\\resources\\com\\example\\entregable\\imagenes/6.jpg");
            System.out.println(""+i.getMaqui());
            this.ivimage.setImage(seis);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /*comentado porque no queremos que la pagina 2 se cierre al acceder a la pagina3
    public void cierrapagina3_abrepagina2() {

        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("vista/pagina2-view.fxml"));
            Pagina2Controller controlador = loader.getController();
            // Cargo el padre
            Parent root = loader.load();

            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();

            // Ciero la ventana donde estoy
            Stage myStage = (Stage) this.textfecha.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }*/
}
