package com.example.entregable.controlador;

import com.example.entregable.Application;
import com.example.entregable.modelo.Incidencia;
import com.example.entregable.modelo.Maquina;
import com.example.entregable.modelo.Prioridad;
import com.example.entregable.modelo.Responsable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class Pagina2Controller implements Initializable {

    @FXML
    private Label fecha;

    @FXML
    private DatePicker dtpfecha;

    @FXML
    private Label maquina;

    @FXML
    private ComboBox <Maquina>cmbmaquina;

    @FXML
    private Label prioridad;

    @FXML
    private ComboBox <Prioridad> cmbprioridad;

    @FXML
    private Label responsable;

    @FXML
    private ComboBox <Responsable>cmbreponsable;

    @FXML
    private Label descripcion;

    @FXML
    private TextArea textincidencia;

    @FXML
    private Button btnguardar;

    @FXML
    private TableView <Incidencia> tbltabla;

    @FXML
    private TableColumn<Incidencia, LocalDate> colFecha;

    @FXML
    private TableColumn<Incidencia, String> colPrioridad;


    @FXML
    private TableColumn<Incidencia, String> colResponsable;

    @FXML
    private TableColumn<Incidencia, String> colMaquina;

    @FXML
    private TableColumn<Incidencia, String> colDescripcion;

    @FXML
    private ObservableList<Incidencia> items;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        this.colFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.colPrioridad.setCellValueFactory(new PropertyValueFactory("priori"));
        this.colResponsable.setCellValueFactory(new PropertyValueFactory("responsab"));
        this.colMaquina.setCellValueFactory(new PropertyValueFactory("maqui"));
        this.colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));

        // Cargo las incidencias en la tabla
        Incidencia i = new Incidencia();
        ObservableList<Incidencia> items = i.getIncidencias();
        this.tbltabla.setItems(items);

        initCombos();
    }


    public void initCombos() {




        // Cargo los Responsables en el combobox
        Responsable r = new Responsable();
        ObservableList<Responsable> obsResponsables = r.getResponsables();
        this.cmbreponsable.setItems(obsResponsables);

         // Cargo las Prioridades en el combobox
        Prioridad p = new Prioridad();
        ObservableList<Prioridad> obsPrioridades = p.getPrioridades();
        this.cmbprioridad.setItems(obsPrioridades);

         // Cargo las Maquinas en el combobox
        Maquina ma = new Maquina();
        ObservableList<Maquina> obsMaquinas = ma.getMaquinas();
        this.cmbmaquina.setItems(obsMaquinas);




    }
   @FXML
    private void seleccionarResponsable(ActionEvent event) {

        // Obtengo el valor del combo del Responsable
        Responsable r = this.cmbreponsable.getValue();


    }
    @FXML
    private void seleccionarMaquina(ActionEvent event) {

        // Obtengo el valor del combo maquina
        Maquina m = this.cmbmaquina.getValue();

    }
    @FXML
    private void seleccionarPrioridad(ActionEvent event) {

        // Obtengo el valor del combo prioridad
        Prioridad p = this.cmbprioridad.getValue();

    }
    @FXML
    private void guardarincidencia(ActionEvent event) {

        // Obtengo los valores
        Maquina v = this.cmbmaquina.getValue();
        Prioridad c = this.cmbprioridad.getValue();
        Responsable r = this.cmbreponsable.getValue();
        LocalDate fech = this.dtpfecha.getValue();
        String descrip = this.textincidencia.getText();

        // Validamos
        String errores = "";

        if (c == null) {
            errores += "- Debes seleccionar una Prioridad\n";
        }

        if (v == null) {
            errores += "- Debes seleccionar una Maquina\n";
        }
        if (r == null) {
            errores += "- Debes seleccionar un Responsable\n";
        }

        if (fech == null) {
            errores += "- Debes seleccionar una fecha \n";
        }

        if (descrip == null) {
            errores += "- La descripcion no puede estar vacia\n";
        }
        if (errores.isEmpty()) {

            try {
                // Creo la incidencia
                Incidencia s = new Incidencia(r.getNif(),fech, descrip, v.getModelo(), c.getGrado(), (r.getNombre()+""+r.getApellido()));

                // lo inserto
                if (s.insertarIncidencia()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Exito");
                    alert.setContentText("Se inserto correctamente");
                    alert.showAndWait();
                    Incidencia i = new Incidencia();
                    ObservableList<Incidencia> itemss = i.getIncidencias();
                    this.tbltabla.setItems(itemss);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("No se inserto correctamente");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(errores);
            alert.showAndWait();
        }

    }
    @FXML
    private void Onseleccionar() {
        Incidencia i = this.tbltabla.getSelectionModel().getSelectedItem();
        if(i!=null) {
            System.out.println("" + i.getDescripcion());
            System.out.println();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Application.class.getResource("vista/pagina3-view.fxml"));

                Pane ventana = (Pane) fxmlLoader.load();
                Pagina3Controller controlador = fxmlLoader.getController();
                controlador.initAttributtes(items,i);
                Scene scene = new Scene(ventana);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                /*comentado porque no queremos que se cierre la ventana 2 al pasar a la 3
                stage.show();
                en caso de cerrar la pagina3 en la parte superior en la x,vuelve a la pagina2
                stage.setOnCloseRequest(e->controlador.cierrapagina3_abrepagina2());
                Stage myStage = (Stage) this.tbltabla.getScene().getWindow();
                myStage.close();*/

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void cierrapagina2_confirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación salida del programa");
        alert.setContentText("¿Estas seguro de querer salir de la aplicacion?");
        Optional<ButtonType> action = alert.showAndWait();

        // Si se ha pulsado en aceptar
        if (action.get() == ButtonType.OK) {
            System.out.println("Has pulsado en aceptar");
            Platform.exit();
        }
        else if( (action.get() == ButtonType.CANCEL)){
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
                Stage myStage = (Stage) this.tbltabla.getScene().getWindow();
                myStage.close();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
