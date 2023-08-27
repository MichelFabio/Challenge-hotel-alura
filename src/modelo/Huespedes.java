package modelo;

import java.sql.Date;

public class Huespedes {
    private String  id;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private String nacionalidad;
    private String telefono;

    public Huespedes(){}

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public java.sql.Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
