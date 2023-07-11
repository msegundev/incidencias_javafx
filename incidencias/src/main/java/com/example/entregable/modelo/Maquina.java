package com.example.entregable.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Properties;

public class Maquina {
    String modelo;

    public Maquina(String modelo) {
        this.modelo = modelo;
    }

    public Maquina() {

    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    @Override
    public String toString() {
        return modelo;
    }

    public ObservableList<Maquina> getMaquinas() {
        ObservableList<Maquina> obs = FXCollections.observableArrayList();
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
            ResultSet rs = st.executeQuery("select * from incidencias.maquinas");


            // recorro los resultados
            while(rs.next()){

                // Cojo los datos
                String modeloo = rs.getString("nombre_maquina");

                // Creo el cliente
                Maquina c = new Maquina(modeloo);

                obs.add(c);

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
