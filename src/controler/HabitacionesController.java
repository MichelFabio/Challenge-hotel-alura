package controler;

import dao.HabitacionesDAO;

public class HabitacionesController {
    private HabitacionesDAO habitacionesDAO;
    public  HabitacionesController(){
        this.habitacionesDAO = new HabitacionesDAO();
    }
    //De no haber habitaciones disponibles retorn false
    public boolean verificarDisponibilidadHabitaciones(int id){
        return habitacionesDAO.verificarDisponibilidad(id);
    }

    //si reflector es menor que 0 resta uno de disponible, de lo contrario suma 1
    public void modificarCuposHabitaciones(int id, int reflector) {
        habitacionesDAO.modificar(id,reflector);
    }
}
