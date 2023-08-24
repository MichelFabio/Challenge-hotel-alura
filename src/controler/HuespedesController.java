package controler;

import dao.HuespedesDAO;
import modelo.Huespedes;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class HuespedesController {
    private HuespedesDAO huespedesDAO;

    public HuespedesController(){
        this.huespedesDAO = new HuespedesDAO();
    }


    public void guardar(Huespedes huespedes) {

        huespedesDAO.guardar(huespedes);
    }
    private Integer obtenerIdGenerado(){
        return huespedesDAO.obtenerIdGenerado();
    }
    public Huespedes asignarHuesped(){
        Integer id = obtenerIdGenerado();
        return huespedesDAO.asignarHuesped(id);
    }

    public List<Huespedes> listar() {
        return huespedesDAO.listar();
    }

    public Huespedes buscar(Integer i) {
        return huespedesDAO.asignarHuesped(i);
    }

    public void eliminar(Integer id) {
        huespedesDAO.eliminar(id);
    }

    public void editar(Huespedes huespede) {
        huespedesDAO.editar(huespede);

    }
}
