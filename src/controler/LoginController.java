package controler;

import conexiones.ConexionBaseDeDatos;

import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private Connection connection;
    private Map<String,String> users;

    public LoginController(){
        this.users = new HashMap<>();
    }
    public boolean verificarAcceso(String username, String password) {
        connection = new ConexionBaseDeDatos().obtenerConexion();
        try(final PreparedStatement preparedStatement = connection.prepareStatement("SELECT username,password FROM usuario")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    users.put(resultSet.getString("username"),resultSet.getString("password"));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        for(int i = 0; i < users.size();i++){
            if(users.get(username).equals(password)){
                return true;
            }
        }
        return false;
    }
}
