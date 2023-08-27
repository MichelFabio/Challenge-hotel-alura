package dao;

import conexiones.ConexionBaseDeDatos;
import modelo.Huespedes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HuespedesDAO {

    final private Connection connection;

    public HuespedesDAO() {
        this.connection = new ConexionBaseDeDatos().obtenerConexion();
        System.out.println("Abriendo daoHuesped");
    }


    public void guardar(Huespedes huespedes) {
        try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO huespedes (id,nombre,apellido,fecha_nacimiento,nacionalidad,telefono) VALUES (?,?,?,?,?,?)")){
            stmt.setString(1, huespedes.getId());
            stmt.setString(2,huespedes.getNombre());
            stmt.setString(3,huespedes.getApellido());
            stmt.setDate(4,huespedes.getFecha_nacimiento());
            stmt.setString(5,huespedes.getNacionalidad());
            stmt.setString(6,huespedes.getTelefono());
            stmt.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("Conexion desde guardar huesped");
    }

    public Huespedes buscar(String id){
        Huespedes huespedes = new Huespedes();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM huespedes WHERE id = ?")){
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                huespedes.setId(resultSet.getString("id"));
                huespedes.setNombre(resultSet.getString("nombre"));
                huespedes.setApellido(resultSet.getString("apellido"));
                huespedes.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
                huespedes.setNacionalidad(resultSet.getString("nacionalidad"));
                huespedes.setTelefono(resultSet.getString("telefono"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("Conexion desde buscar huesped");
        return huespedes;
    }

    public List<Huespedes> listar() {
        List<Huespedes> listaHuespedes = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM huespedes")){
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Huespedes h = new Huespedes();
                    h.setId(resultSet.getString("id"));
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
        System.out.println("Conexion desde listar huesped");
        return listaHuespedes;
    }

    public void eliminar(String id) {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM huespedes WHERE id = ?")){
            statement.setString(1,id);
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("Conexion desde eliminar huesped");

    }

    public void editar(Huespedes huespede) {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE huespedes SET nombre=?,apellido=?,fecha_nacimiento=?,nacionalidad=?,telefono=? WHERE id=?")){
            statement.setString(1,huespede.getNombre());
            statement.setString(2,huespede.getApellido());
            statement.setDate(3,huespede.getFecha_nacimiento());
            statement.setString(4, huespede.getNacionalidad());
            statement.setString(5, huespede.getTelefono());
            statement.setString(6,huespede.getId());
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("Conexion desde editar huesped");

    }
}
