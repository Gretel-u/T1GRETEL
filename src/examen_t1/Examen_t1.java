/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examen_t1;

/**
 *
 * @author YOSELY
 */
public class Examen_t1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         // Fuerza salida en UTF-8 para que tildes y ñ se vean bien en cualquier consola.
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
        ControladorAlumnos controlador = new ControladorAlumnos();
        boolean salir = false;

        System.out.println("==========================================");
        System.out.println(" SISTEMA DE GESTIÓN DE ALUMNOS - INSTITUTO INNOVA");
        System.out.println("==========================================");

        while (!salir) {
            mostrarMenu();
            String opcion = sc.nextLine().trim();

            try {
                switch (opcion) {
                    case "1":
                        registrarAlumno(sc, controlador);
                        break;
                    case "2":
                        controlador.listarAlumnos();
                        break;
                    case "3":
                        salir = true;
                        System.out.println("Saliendo del sistema. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor elige 1, 2 o 3.");
                }
            } catch (Exception e) {
                // Red de seguridad final: cualquier error inesperado se informa
                // y el programa sigue funcionando en vez de cerrarse de golpe.
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        }

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n----- MENÚ -----");
        System.out.println("1. Registrar alumno");
        System.out.println("2. Listar alumnos");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void registrarAlumno(Scanner sc, ControladorAlumnos controlador) {
        try {
            System.out.print("Nombre completo del alumno: ");
            String nombre = sc.nextLine();

            System.out.print("Tipo de documento (DNI / CARNET): ");
            String tipoDoc = sc.nextLine();

            System.out.print("Número de documento: ");
            String numDoc = sc.nextLine();

            System.out.print("Nivel socioeconómico (A / B / C): ");
            String nivel = sc.nextLine();

            System.out.print("Tipo de beca (NINGUNA / PARCIAL / TOTAL): ");
            String beca = sc.nextLine();

            Alumno alumno = new Alumno(nombre, tipoDoc, numDoc, nivel, beca);
            controlador.agregarAlumno(alumno);

            System.out.println("¡Alumno registrado con éxito!");
            System.out.printf("Pensión final calculada: S/ %.2f%n", alumno.calcularPensionFinal());

        } catch (DocumentoInvalidoException e) {
            // Validaciones de negocio (documento, nivel, beca, duplicados)
            System.out.println("No se pudo registrar el alumno: " + e.getMessage());
        }
    }
       
    }
    
}
