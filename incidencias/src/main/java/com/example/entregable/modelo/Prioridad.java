package com.example.entregable.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Properties;

public class Prioridad {
    String grado;

    public Prioridad() {
    }

    public Prioridad(String grado) {
        this.grado = grado;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
    @Override
    public String toString() {
        return grado;
    }

    public ObservableList<Prioridad> getPrioridades() {
        ObservableList<Prioridad> obs = FXCollections.observableArrayList();
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
            ResultSet rs = st.executeQuery("select * from incidencias.prioridad");


            // recorro los resultados
            while(rs.next()){

                // Cojo los datos
                String grado = rs.getString("prioridad");

                // Creo el cliente
                Prioridad p = new Prioridad(grado);

                obs.add(p);

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
