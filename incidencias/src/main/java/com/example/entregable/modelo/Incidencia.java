package com.example.entregable.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Incidencia {
    private String nifresponsable;
    private LocalDate fecha;
    private String descripcion;

    private String maqui;

    private String priori;

    private String responsab;

    public Incidencia() {

    }
    public Incidencia(String nifresponsable,LocalDate fecha, String descripcion, String maqui, String priori, String responsab) {
        this.nifresponsable = nifresponsable;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.maqui = maqui;
        this.priori = priori;
        this.responsab = responsab;
    }

    public String getNifresponsable() {
        return nifresponsable;
    }

    public void setNifresponsable(String nifresponsable) {
        this.nifresponsable = nifresponsable;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getMaqui() {
        return maqui;
    }

    public void setMaqui(String maqui) {
        this.maqui = maqui;
    }

    public String getPriori() {
        return priori;
    }

    public void setPriori(String priori) {
        this.priori = priori;
    }

    public String getResponsab() {
        return responsab;
    }

    public void setResponsab(String responsab) {
        this.responsab = responsab;
    }


    public boolean insertarIncidencia() throws SQLException {

        Connection con = null;
        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "CESJUANPABLOII!");
        // Abro la conexion
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/incidencias?enabledTLSProtocols=TLSv1.2", props);

        // Sentencia para introducir una incidencia
        String SQL = "INSERT INTO incidencias (nif_responsable,fecha_incidencia, descripcion, maquina, prioridad, responsable) "
                + "values("+"'"+this.nifresponsable+"',"+"'"+this.fecha.toString()+"',"+"'"+this.descripcion+"',"+"'"+this.maqui+"',"+"'"+this.priori+"',"+ "'"+this.responsab+"'"+")";

        // Ejcuto la consulta y Devuelvo el numero de filas afectadas
        Statement st = con.createStatement();
        int filas = st.executeUpdate(SQL);

        con.close();

        // Si deuvelve mas de 0, es que hemos insertado registros

        if (filas > 0) {
            return true;
        } else {
            return false;
        }

    }

    public ObservableList<Incidencia> getIncidencias() {
        //Sobrecarga
        return this.getIncidencias(null,null, null, null, null, null);
    }

    public ObservableList<Incidencia> getIncidencias(String nifresponsable,LocalDate fecha, String descripcion, String maqui, String priori, String responsab) {
        ObservableList<Incidencia> obs = FXCollections.observableArrayList();
        try {
            Connection con = null;
            Properties props = new Properties();
            props.put("user", "root");
            props.put("password", "CESJUANPABLOII!");
            // Abro la conexion
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/incidencias?enabledTLSProtocols=TLSv1.2", props);

            // realizo la consulta
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select r.nif,v.nombre_maquina, s.fecha_incidencia,s.prioridad,s.descripcion,CONCAT(r.nombre,' ',r.apellido) as nya from incidencias s, maquinas v, responsable r\n" +
                    "where s.maquina = v.nombre_maquina and s.nif_responsable=r.nif;");

            // recorro los resultados
            while(rs.next()) {
                // Cojo los datos
                String niff = rs.getString("nif");
                LocalDate fechaa = rs.getDate("fecha_incidencia").toLocalDate();
                String descripcionn = rs.getString("descripcion");
                String maquinaa = rs.getString("nombre_maquina");
                String prioridadd = rs.getString("prioridad");
                String responsablee = rs.getString("nya");


                // Creo la incidencia
                Incidencia s = new Incidencia(niff,fechaa, descripcionn, maquinaa, prioridadd, responsablee);

                obs.add(s);
            }

            // Cierro la conexion
            rs.close();
            st.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obs;

    }
}
