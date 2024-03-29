package modelo;

import java.sql.Date;

public class Reservas {
    private Integer idReservas;
    private Date fecha_entrada;
    private Date fecha_salida;
    private Integer valor;
    private String forma_pago;
    private Huespedes huesped;
    private Habitaciones habitacion;

    public Reservas() {

    }

    public Integer getIdReservas() {
        return idReservas;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public Integer getValor() {
        return valor;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public Huespedes getHuesped() {
        return this.huesped;
    }

    public void setIdReservas(Integer idReservas) {
        this.idReservas = idReservas;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public void setHuesped(Huespedes huesped) {
        this.huesped = huesped;
    }

    public Habitaciones getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitaciones habitacion) {
        this.habitacion = habitacion;
    }
    public boolean tieneAsignado(){
        return this.huesped != null;
    }

    @Override
    public String toString(){
        return this.fecha_entrada + "\n" + this.fecha_salida + "\n" + this.valor + "\n" + this.forma_pago + "\n";
    }
}
