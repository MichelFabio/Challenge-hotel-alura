package dao;

import conexiones.ConexionBaseDeDatos;
import modelo.Huespedes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class HuespedesDAO {

    final private Connection connection;

    public HuespedesDAO() {
        this.connection = new ConexionBaseDeDatos().obtenerConexion();
    }


    public void guardar(Huespedes huespedes) {
        try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO huespedes (nombre,apellido,fecha_nacimiento,nacionalidad,telefono) VALUES (?,?,?,?,?)")){
            stmt.setString(1,huespedes.getNombre());
            stmt.setString(2,huespedes.getApellido());
            stmt.setDate(3,huespedes.getFecha_nacimiento());
            stmt.setString(4,huespedes.getNacionalidad());
            stmt.setString(5,huespedes.getTelefono());
            stmt.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("Conexion desde guardar huesped");
    }
//obtiene el id del objeto huesped recien guardado en la base de datos
    public Integer obtenerIdGenerado() {
        int id = 0;
        try(PreparedStatement statement = connection.prepareStatement("SELECT LAST_INSERT_ID() AS id")){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                id = resultSet.getInt("id");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("Conexion desde obtener id generado huesped");
        return id;
    }

    //Devuelve el objeto huesped con el  id generado
    public Huespedes asignarHuesped(Integer id){
        Huespedes huespedes = new Huespedes();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM huespedes WHERE id = ?")){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                huespedes.setId(resultSet.getInt("id"));
                huespedes.setNombre(resultSet.getString("nombre"));
                huespedes.setApellido(resultSet.getString("apellido"));
                huespedes.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
                huespedes.setNacionalidad(resultSet.getString("nacionalidad"));
                huespedes.setTelefono(resultSet.getString("telefono"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("Conexion desde asignar huesped");
        return huespedes;
    }

    public List<Huespedes> listar() {
        List<Huespedes> listaHuespedes = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM huespedes")){
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Huespedes h = new Huespedes();
                    h.setId(resultSet.getInt("id"));
                    h.setNombre(resultSet.getString("nombre"));
                    h.setApellido(resultSet.getString("apellido"));
                    h.setTelefono(resultSet.getString("telefono"));
                    h.setNacionalidad(resultSet.getString("nacionalidad"));
                    h.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
                    listaHuespedes.add(h);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaHuespedes;
    }

    public void eliminar(Integer id) {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM huespedes WHERE id = ?")){
            statement.setInt(1,id);
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void editar(Huespedes huespede) {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE huespedes SET (nombre=?,apellido=?,fecha_nacimiento=?,nacionalidad=?,telefono=?) WHERE id=?")){
            statement.setString(1,huespede.getNombre());
            statement.setString(2,huespede.getApellido());
            statement.setDate(3,huespede.getFecha_nacimiento());
            statement.setString(4, huespede.getNacionalidad());
            statement.setString(5, huespede.getTelefono());
            statement.setInt(6,huespede.getId());
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
