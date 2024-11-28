package modelo.interfaz;

import modelo.dao.DaoImplement;
import modelo.entidad.Coche;
import modelo.gestor.Gestor;

import java.util.Scanner;

public class Interfaz {

    private Gestor gestor;
    private DaoImplement dao = DaoImplement.getInstance();
    static Scanner scanner = new Scanner(System.in);

    public void menu() {

        int option;

        do {
            // Mostrar el menú
            System.out.println("\n===== Menú de Gestión de Coches =====");
            System.out.println("[1] Dar de alta coche");
            System.out.println("[2] Dar de baja coche por ID");
            System.out.println("[3] Modificar coche por ID");
            System.out.println("[4] Buscar coche por ID");
            System.out.println("[5] Buscar coches por marca");
            System.out.println("[6] Listar todos los coches");
            System.out.println("[0] Salir de la aplicación");
            System.out.print("Seleccione una opción: ");

            // Leer la opción del usuario
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            // Manejar las opciones
            switch (option) {
                case 1:
                    System.out.println("Opción seleccionada: Dar de alta coche.");
                    Coche coche = pedirDatos();

                    gestor.addCoche(coche);

                    break;

                case 2:
                    System.out.println("Opción seleccionada: Dar de baja coche por ID.");
                    //int id = pedirId();

                    break;

                case 3:
                    System.out.println("Opción seleccionada: Modificar coche por ID.");

                    break;

                case 4:
                    System.out.println("Opción seleccionada: Buscar coche por ID.");
                    // Aquí llamarías al método para buscar un coche por ID
                    break;

                case 5:
                    System.out.println("Opción seleccionada: Buscar coches por marca.");
                    // Aquí llamarías al método para buscar coches por marca
                    break;

                case 6:
                    System.out.println("Opción seleccionada: Listar todos los coches.");
                    // Aquí llamarías al método para listar todos los coches
                    break;

                case 0:
                    System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (option != 0);


    }

    private int pedirId() {
        return 0;
    }

    private Coche pedirDatos() {
        System.out.println("Ingrese la Marca:");
        String marca = scanner.nextLine();
        while (marca.isBlank()) {
            System.out.println("- La marca no puede estar vacia o contener solo espacios.");
            System.out.println("Ingrese la Marca:");
            marca = scanner.nextLine();
        }

        System.out.println("Ingrese el Modelo:");
        String modelo = scanner.nextLine();
        while (marca.isBlank()) {
            System.out.println("- El modelo no puede estar vacia o contener solo espacios.");
            System.out.println("Ingrese El Modelo:");
            marca = scanner.nextLine();
        }





        return null;
    }


    /**
     * Valida si un Coche pasado por parametro es valido.
     *
     * @param coche a validar.
     * @return 1 solo si el coche es valido, 0 si el coche es null, 2 la marca no es valida,
     * 3 el modelo no es valido, 4 el motor no es valido, 5 los kilometros no son validos.
     */
    public int isCocheValid(Coche coche) {
        if (coche == null) {
            return 0;
        }
        if (coche.getMarca() == null || coche.getMarca().isBlank()) {
            return 2;
        }
        if (coche.getModelo() == null || coche.getModelo().isBlank()) {
            return 3;
        }

        if (coche.getMotor() == null) {
            return 4;
        } else {
            switch (coche.getMotor()) {
                case DIESEL:
                case GASOLINA:
                case ELECTRICO:
                    break;
                default:
                    return 4;
            }
        }

        if (coche.getKilometros() < 0) {
            return 5;
        }

        return 1;
    }
}
