package dao;

import conexiones.ConexionBaseDeDatos;
import modelo.Habitaciones;
import modelo.Huespedes;
import modelo.Reservas;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservasDAO {
    private Connection connection;
    public ReservasDAO(){
        this.connection = new ConexionBaseDeDatos().obtenerConexion();
        System.out.println("Conexion daoREservas");
    }
    public void guardar(Reservas reserva) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO reservas (fecha_entrada,fecha_salida,valor,forma_pago,huesped_id,habitacion) VALUES (?,?,?,?,?,?)")){
            preparedStatement.setDate(1,reserva.getFecha_entrada());
            preparedStatement.setDate(2,reserva.getFecha_salida());
            preparedStatement.setInt(3,reserva.getValor());
            preparedStatement.setString(4,reserva.getForma_pago());
            preparedStatement.setString(5,reserva.getHuesped().getId());
            preparedStatement.setInt(6,reserva.getHabitacion().getId());
            preparedStatement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("conexion desde guardar reserva");
    }

    public List<Reservas> buscar() {
        List<Reservas> reservas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT r.*, h.id as huesped_id ,h.nombre from reservas as r " +
                "inner join huespedes as h on r.huesped_id = h.id");
             ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()){
                Reservas r = new Reservas();
                r.setIdReservas(resultSet.getInt("id"));
                r.setFecha_entrada( resultSet.getDate("fecha_entrada"));
                r.setFecha_salida(resultSet.getDate("fecha_salida"));
                r.setValor(resultSet.getInt("valor"));
                r.setForma_pago(resultSet.getString("forma_pago"));
                Huespedes huespedes = new Huespedes();
                huespedes.setId(resultSet.getString("huesped_id"));
                huespedes.setNombre(resultSet.getString("nombre"));
                r.setHuesped(huespedes);
                Habitaciones habitacion = new Habitaciones();
                habitacion.setId(resultSet.getInt("habitacion"));
                r.setHabitacion(habitacion);
                reservas.add(r);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("conexion desde buscar(cambiar a listar) reserva");
        return reservas;
    }

    public void editar(Reservas reservas) {
        try(PreparedStatement stmt = connection.prepareStatement("UPDATE reservas SET fecha_entrada=?,fecha_salida=?,valor=?,forma_pago=? WHERE id=? ")){
            stmt.setDate(1,reservas.getFecha_entrada());
            stmt.setDate(2,reservas.getFecha_salida());
            stmt.setInt(3,reservas.getValor());
            stmt.setString(4,reservas.getForma_pago());
            stmt.setInt(5,reservas.getIdReservas());
            stmt.execute();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("Conexion desde editar reserva");
    }
//Primero se debe eliminar la reserva para poder eliminar el huesped
    public void eliminar(Integer id) {
        try(PreparedStatement stmt = connection.prepareStatement("DELETE FROM reservas WHERE id = ?")){
            stmt.setInt(1,id);
            stmt.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("Conexion desde eliminar reserva");
    }

    public Reservas buscar(Integer id) {
        Reservas r = null;
        try(PreparedStatement stmt = connection.prepareStatement("SELECT r.*, h.id as huesped_id ,h.nombre from reservas as r inner join huespedes as h on r.huesped_id = h.id WHERE r.id = ?")){
            stmt.setInt(1,id);
            try(ResultSet resultSet = stmt.executeQuery()){
                if(resultSet.next()) {
                        r = new Reservas();
                          r.setIdReservas(resultSet.getInt("id"));
                          r.setFecha_entrada(resultSet.getDate("fecha_entrada"));
                          r.setFecha_salida(resultSet.getDate(("fecha_salida")));
                          r.setValor(resultSet.getInt("valor"));
                          r.setForma_pago(resultSet.getString("forma_pago"));
                          Huespedes h = new Huespedes();
                          h.setId(resultSet.getString("huesped_id"));
                          h.setNombre(resultSet.getString("nombre"));
                          r.setHuesped(h);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("conexion desde buscar reservas por id");
        return r;
    }

    public boolean buscar(String id) {
        boolean flag = false;
        try(PreparedStatement statement = connection.prepareStatement("select * from reservas where huesped_id=?")){
            statement.setString(1,id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                flag = true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println(flag);
        return flag;
    }
//Cada vez que cargue el menu usuario elimine las reservas que ya pasaron
    public void cargar() {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM reservas WHERE fecha_salida < ?") ){
            Calendar fechaActual = Calendar.getInstance();
            statement.setDate(1,
                    new Date (fechaActual.getTimeInMillis()));
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
