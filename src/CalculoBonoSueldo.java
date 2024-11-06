import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class CalculoBonoSueldo {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String[][] haberes = {
                    {"100", "Presentismo", "9"},
                    {"101", "Titulo Profesional", "9"},
                    {"102", "Horas Extraordinarias", "M"},
                    {"103", "Horas Nocturnas", "M"},
                    {"104", "Otros Haberes", "M"}
            };

            String[][] deducciones = {
                    {"200", "Obra Social", "3"},
                    {"201", "Jubilacion", "11"},
                    {"202", "Sindicato", "2"},
                    {"203", "Seguro", "1.5"},
                    {"204", "Otros", "M"}
            };

            List<Integer> codigosIngresados = new ArrayList<>();
            boolean continuar = true;
            Empleado empleado = crearEmpleado(scanner);
            while (continuar) {
                BonoSueldo bono = new BonoSueldo(empleado, 0, 0);

                //Solicitar ingresos de haberes
                double sumaHaberes = ingresarHaberes(scanner, haberes, codigosIngresados);
                // Solicitar ingresos de deducciones
                double sumaDeducciones = ingresarDeducciones(scanner, deducciones, codigosIngresados);
                //Calcular monto a liquidar
                double montoLiquidacion = calcularMontoLiquidacion(empleado, sumaHaberes, sumaDeducciones);
                bono.setMontoLiquidacion(montoLiquidacion);
                empleado.getBonos().add(bono);

                // Paso 8: Preguntar si se desea generar un nuevo bono
                System.out.print("¿Desea generar un nuevo bono de sueldo? (si/no): ");
                continuar = scanner.nextLine().equalsIgnoreCase("si");
            }

            // Mostrar todos los bonos cargados
            mostrarBonos(empleado);

            scanner.close();
        }

        private static Empleado crearEmpleado(Scanner scanner) {
            String nombre;
            long cuil;
            int anioIngreso;
            double sueldoBasico;

            System.out.print("Ingrese el nombre del empleado: ");
            nombre = scanner.nextLine();

            System.out.print("Ingrese el CUIL del empleado: ");
            cuil = Long.parseLong(scanner.nextLine());

            // Validar año de ingreso
            do {
                System.out.print("Ingrese el año de ingreso (no superior al año actual): ");
                anioIngreso = Integer.parseInt(scanner.nextLine());
                if (anioIngreso > 2024) {
                    System.out.println("El año de ingreso no puede ser mayor que 2024.");
                }
            } while (anioIngreso > 2024);

            // Validar sueldo básico
            System.out.print("Ingrese el sueldo básico: ");
            sueldoBasico = Double.parseDouble(scanner.nextLine());

            return new Empleado(nombre, cuil, anioIngreso, sueldoBasico);
        }

        private static double ingresarHaberes(Scanner scanner, String[][] haberes, List<Integer> codigosIngresados) {
            double sumaHaberes = 0;
            String[][] bonoCalculado = new String[10][4];

            System.out.println("Ingrese los Haberes del Empleado");
            while (true) {
                System.out.print("Ingrese el código del ítem (000 para finalizar): ");
                String codigo = scanner.nextLine();
                if (codigo.equals("000")) {
                    if (codigosIngresados.isEmpty()) {
                        System.out.println("Debe ingresar al menos 1 haber");
                        continue;
                    }
                    break;
                }

                int codigoNum = Integer.parseInt(codigo);
                if (codigosIngresados.contains(codigoNum)) {
                    System.out.println("El código ya ha sido cargado, ingrese otro código.");
                    continue;
                }

                boolean encontrado = false;
                for (String[] item : haberes) {
                    if (item[0].equals(codigo)) {
                        encontrado = true;
                        bonoCalculado[codigosIngresados.size()][0] = item[0];
                        bonoCalculado[codigosIngresados.size()][1] = item[1];

                        if (item[2].equals("M")) {
                            System.out.print("Ingrese el valor del haber: ");
                            bonoCalculado[codigosIngresados.size()][2] = scanner.nextLine();
                            sumaHaberes += Double.parseDouble(bonoCalculado[codigosIngresados.size()][2]);
                        } else {
                            double porcentaje = Double.parseDouble(item[2]) / 100;
                            double valor = (50000 * porcentaje); // Cambiar a sueldo básico de empleado
                            bonoCalculado[codigosIngresados.size()][2] = String.valueOf(valor);
                            sumaHaberes += valor;
                        }

                        codigosIngresados.add(codigoNum);
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("El código ingresado es incorrecto.");
                }
            }
            return sumaHaberes;
        }

        private static double ingresarDeducciones(Scanner scanner, String[][] deducciones, List<Integer> codigosIngresados) {
            double sumaDeducciones = 0;
            String[][] bonoCalculado = new String[10][4];

            System.out.println("Ingrese las Deducciones del Empleado");
            while (true) {
                System.out.print("Ingrese el código del ítem (000 para finalizar): ");
                String codigo = scanner.nextLine();
                if (codigo.equals("000")) {
                    if (codigosIngresados.isEmpty()) {
                        System.out.println("Debe ingresar al menos 1 deducción");
                        continue;
                    }
                    break;
                }

                int codigoNum = Integer.parseInt(codigo);
                if (codigosIngresados.contains(codigoNum)) {
                    System.out.println("El código ya ha sido cargado, ingrese otro código.");
                    continue;
                }

                boolean encontrado = false;
                for (String[] item : deducciones) {
                    if (item[0].equals(codigo)) {
                        encontrado = true;
                        bonoCalculado[codigosIngresados.size()][0] = item[0];
                        bonoCalculado[codigosIngresados.size()][1] = item[1];

                        if (item[2].equals("M")) {
                            System.out.print("Ingrese el valor de la deducción: ");
                            bonoCalculado[codigosIngresados.size()][2] = scanner.nextLine();
                            sumaDeducciones += Double.parseDouble(bonoCalculado[codigosIngresados.size()][2]);
                        } else {
                            double porcentaje = Double.parseDouble(item[2]) / 100;
                            double valor = (50000 * porcentaje); // Cambiar a sueldo básico de empleado
                            bonoCalculado[codigosIngresados.size()][2] = String.valueOf(valor);
                            sumaDeducciones += valor;
                        }

                        codigosIngresados.add(codigoNum);
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("El código ingresado es incorrecto.");
                }
            }
            return sumaDeducciones;
        }

        private static double calcularMontoLiquidacion(Empleado empleado, double sumaHaberes, double sumaDeducciones) {
            return (empleado.getSueldoBasico() + empleado.getMontoAntiguedad() + sumaHaberes) - sumaDeducciones;
        }

        private static void mostrarBonos(Empleado empleado) {
            System.out.println("Los bonos de sueldo cargados son:");
            for (BonoSueldo bono : empleado.getBonos()) {
                System.out.println("Bono de sueldo a liquidar: " + bono.getMontoLiquidacion());
            }
        }
    }
