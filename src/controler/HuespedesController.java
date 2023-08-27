package controler;

import dao.HuespedesDAO;
import modelo.Huespedes;

import java.util.List;

public class HuespedesController {
    private HuespedesDAO huespedesDAO;

    public HuespedesController(){
        this.huespedesDAO = new HuespedesDAO();
    }


    public void guardar(Huespedes huespedes) {

        huespedesDAO.guardar(huespedes);
    }

    public Huespedes buscarHuesped(String id){
        return huespedesDAO.buscar(id);
    }

    public List<Huespedes> listar() {
        return huespedesDAO.listar();
    }



    public void eliminar(String id) {
        huespedesDAO.eliminar(id);
    }

    public void editar(Huespedes huespede) {
        huespedesDAO.editar(huespede);

    }
}
