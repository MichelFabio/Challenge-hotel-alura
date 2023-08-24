package controler;

import com.toedter.calendar.JDateChooser;
import dao.ReservasDAO;
import modelo.Reservas;

import java.text.DecimalFormat;
import java.util.List;

public class ReservasControler {
    ReservasDAO reservasDAO = new ReservasDAO();

    public void guardar(Reservas reserva) {
        reservasDAO.guardar(reserva);
    }

    public List<Reservas> buscar() {
        return reservasDAO.buscar();
    }

    public void editar(Reservas reservas) {
        reservasDAO.editar(reservas);
    }

    public void eliminar(Integer id) {
        reservasDAO.eliminar(id);
    }

    public Reservas buscar(Integer id) {
        return reservasDAO.buscar(id);
    }

    public long calcularValorEstadía(JDateChooser entrada, JDateChooser salida) {
        //resta los valores en milisegundos y los convierte a dias, con una tasa fija de 50000 pesos colombianos el dia,
        long numeroDias = (salida.getDate().getTime() - entrada.getDate().getTime()) / 86400000;
        return numeroDias * 50000;

    }
}
