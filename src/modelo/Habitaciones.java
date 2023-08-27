package modelo;

public class Habitaciones {
    private int id;
    private String tipo;
    private int disponible;
    private int valor;

    public Habitaciones(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }


}
