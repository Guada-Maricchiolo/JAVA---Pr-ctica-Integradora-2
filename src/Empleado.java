import java.util.ArrayList;
import java.util.List;

public class Empleado {
    private String nombreEmpleado;
    private long cuil;
    private int anioIngreso;
    private double montoAntiguedad;
    private double sueldoBasico;
    private List<BonoSueldo> bonos;

    public Empleado(String nombreEmpleado, long cuil, int anioIngreso, double sueldoBasico) {
        this.nombreEmpleado = nombreEmpleado;
        this.cuil = cuil;
        this.anioIngreso = anioIngreso;
        this.sueldoBasico = sueldoBasico;
        this.montoAntiguedad = calcularAntiguedad();
        this.bonos = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public long getCuil() {
        return cuil;
    }

    public void setCuil(long cuil) {
        this.cuil = cuil;
    }

    public int getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(int anioIngreso) {
        this.anioIngreso = anioIngreso;
        // Recalcula el monto de antigüedad cada vez que se cambia el año de ingreso
        this.montoAntiguedad = calcularAntiguedad();
    }

    public double getMontoAntiguedad() {
        return montoAntiguedad;
    }

    public double getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
        // Recalcula el monto de antigüedad cada vez que se cambia el sueldo básico
        this.montoAntiguedad = calcularAntiguedad();
    }

    public List<BonoSueldo> getBonos() {
        return bonos;
    }

    public void setBonos(List<BonoSueldo> bonos) {
        this.bonos = bonos;
    }

    // Metodo privado para calcular la antigüedad en base al año de ingreso y sueldo básico actual
    private double calcularAntiguedad() {
        int antiguedad = 2024 - this.anioIngreso; // Asumiendo el año actual es 2024
        return this.sueldoBasico * 0.02 * antiguedad;
    }


}