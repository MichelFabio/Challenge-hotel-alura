package conexiones;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBaseDeDatos {

    private DataSource dataSource;
    public ConexionBaseDeDatos(){
        var poolDataSource = new ComboPooledDataSource();
        poolDataSource.setMaxPoolSize(10);
        poolDataSource.setJdbcUrl("jdbc:mysql://localhost/alura?userTimeZone=true&serverTimeZone=UTC");
        poolDataSource.setUser("root");
        poolDataSource.setPassword("org.celia");
        this.dataSource = poolDataSource;

    }

    public Connection obtenerConexion(){
        try{
            return this.dataSource.getConnection();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
