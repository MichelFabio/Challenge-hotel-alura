package controler;

import com.toedter.calendar.JDateChooser;
import dao.ReservasDAO;
import modelo.Reservas;

import java.util.List;

public class ReservasControler {
    ReservasDAO reservasDAO = new ReservasDAO();

    public void guardarReserva(Reservas reserva) {
        reservasDAO.guardar(reserva);
    }

    public List<Reservas> buscarReserva() {
        return reservasDAO.buscar();
    }

    public void editarReserva(Reservas reservas) {
        reservasDAO.editar(reservas);
    }

    public void eliminarReserva(Integer id) {
        reservasDAO.eliminar(id);
    }

    public Reservas buscarReserva(Integer id) {
        return reservasDAO.buscar(id);
    }

    public long calcularValorEstadia(JDateChooser entrada, JDateChooser salida) {
        //resta los valores en milisegundos y los convierte a dias, con una tasa fija de 50000 pesos colombianos el dia,
        long numeroDias = (salida.getDate().getTime() - entrada.getDate().getTime()) / 86400000;
        return numeroDias * 50000;

    }

    public boolean tieneHuesped(String id) {
        return reservasDAO.buscar(id);
    }
}
