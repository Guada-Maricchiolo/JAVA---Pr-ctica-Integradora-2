public class BonoSueldo {

        private Empleado empleado;
        private int mesLiquidacion;
        private int anioLiquidacion;
        private double montoLiquidacion;

        public BonoSueldo(Empleado empleado, int mesLiquidacion, int anioLiquidacion) {
            this.empleado = empleado;
            this.mesLiquidacion = mesLiquidacion;
            this.anioLiquidacion = anioLiquidacion;
        }

        // Getters y Setters
        public Empleado getEmpleado() { return empleado; }
        public int getMesLiquidacion() { return mesLiquidacion; }
        public int getAnioLiquidacion() { return anioLiquidacion; }
        public double getMontoLiquidacion() { return montoLiquidacion; }

        public void setMontoLiquidacion(double montoLiquidacion) {
            this.montoLiquidacion = montoLiquidacion;
        }
}

