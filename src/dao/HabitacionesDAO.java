package dao;


import conexiones.ConexionBaseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HabitacionesDAO {
    private Connection connection;
    public HabitacionesDAO(){
        this.connection = new ConexionBaseDeDatos().obtenerConexion();
    }

    public boolean verificarDisponibilidad(int id){
        boolean flag = false;
        try(PreparedStatement statement = connection.prepareStatement("SELECT disponible FROM habitaciones WHERE id =?")){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt("disponible"));
                if (resultSet.getInt("disponible") > 0){
                    flag = true;
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return flag;
    }

    public void modificar(int id,int flag) {
        String sentencia = "";
        if(flag > 0) {
            sentencia = "UPDATE habitaciones SET disponible = disponible + 1 WHERE  id = ?";
        }else if (flag < 0){
            sentencia = "UPDATE habitaciones SET disponible = disponible - 1 WHERE  id = ?";
        }
            try (PreparedStatement statement = connection.prepareStatement(sentencia)) {
                statement.setInt(1, id);
                statement.execute();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        System.out.println("update");
    }
}
