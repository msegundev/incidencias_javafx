package com.example.entregable.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Properties;

public class Responsable {
    private String nif;



    private String nombre;
    private String apellido;

    public Responsable() {

    }
    public Responsable(String nif,String nombre, String apellido) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    @Override
    public String toString() {
        return nombre+" "+apellido;
    }
    public ObservableList<Responsable> getResponsables() {
        ObservableList<Responsable> obs = FXCollections.observableArrayList();
        try {
            //Class.forName(DRIVER_CLASS);
            Connection con = null;

            Properties props = new Properties();
            props.put("user", "root");
            props.put("password", "CESJUANPABLOII!");

            // Abro la conexion
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/incidencias?enabledTLSProtocols=TLSv1.2", props);
            // realizo la consulta
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from incidencias.responsable");


            // recorro los resultados
            while(rs.next()){

                // Cojo los datos
                String nifff = rs.getString("nif");
                String apellidooo = rs.getString("apellido");
                String nombreee = rs.getString("nombre");

                // Creo el cliente
                Responsable r = new Responsable(nifff,nombreee, apellidooo);

                obs.add(r);

            }

            // Cierro la conexion
            rs.close();
            st.close();
            con.close();


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
            //} catch (ClassNotFoundException e) {
            //throw new RuntimeException(e);
        }
        return obs;
    }
}
