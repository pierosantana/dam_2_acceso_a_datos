package vista;

import modelo.entidad.Coche;
import modelo.entidad.Motor;
import modelo.negocio.GestorCoche;
import modelo.persistencia.DaoImplement;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Interfaz {

	private GestorCoche gestor = GestorCoche.getInstance();
	static Scanner scannerString = new Scanner(System.in);
	static Scanner scannerNum = new Scanner(System.in);
	static InterfazPasajero interPas = new InterfazPasajero();

	public void menu() {

		boolean flag = false;
		String opcion = "";
		int id = 0;

		Coche coche = null;

		do {
			// Mostrar el menú
			System.out.println("\n== Menú de Gestión de Coches ==");
			System.out.println("[1] Añadir nuevo coche");
			System.out.println("[2] Borrar coche por ID");
			System.out.println("[3] Modificar coche por ID");
			System.out.println("[4] Buscar coche por ID");
			System.out.println("[5] Listar todos los coches");
			System.out.println("[6] Gestion de pasajeros");
			System.out.println("[0] Salir de la aplicación");
			System.out.print("Seleccione una opción: ");

			// Leer la opción del usuario
			opcion = scannerString.nextLine();

			// Manejar las opciones
			switch (opcion) {
			case "1":
				System.out.println("Opción seleccionada: Dar de alta coche.");
				coche = pedirDatos();

				int validar = isCocheValid(coche);

				switch (validar) {
				case 1:
					boolean add = gestor.addCoche(coche);
					System.out.println("El coche fue añadido.");
					break;

				default:
					System.out.println("Coche no es valido para añadir.");
					break;
				}

				break;

			case "2":
				System.out.println("Opción seleccionada: Dar de baja coche por ID.");

				id = 0;

				do {
					System.out.println("Ingrese el ID del coche:");
					flag = false;

					try {
						id = scannerNum.nextInt();
					} catch (Exception e) {
						System.out.println("Formato ID no valido, vuelve a ingresarlo.");
						flag = true;
					}
				} while (flag);

				boolean result = gestor.deleteCocheById(id);

				String borrado = (result == true) ? "Coche borrado" : "No se borro ningun coche";

				System.out.println(borrado);

				break;

			case "3":
				System.out.println("Opción seleccionada: Modificar coche por ID.");

				id = 0;

				do {
					System.out.println("Ingrese el ID del coche a modificar:");
					flag = false;

					try {
						id = scannerNum.nextInt();
					} catch (Exception e) {
						System.out.println("Formato ID no valido, vuelve a ingresarlo.");
						flag = true;
					}
				} while (flag);

				coche = gestor.getCocheById(id);

				if (coche != null) {
					coche = pedirDatos();
					coche.setId(id);
					gestor.updateCoche(coche);

				} else {
					System.out.println("Coche no encontrado.");
				}

				break;

			case "4":
				System.out.println("Opción seleccionada: Buscar coche por ID.");

				do {
					System.out.println("Ingrese el ID del coche a buscar:");
					flag = false;

					try {
						id = scannerNum.nextInt();
					} catch (Exception e) {
						System.out.println("Formato ID no valido, vuelve a ingresarlo.");
						flag = true;
					}
				} while (flag);

				coche = gestor.getCocheById(id);
				System.out.println(coche);

				break;

			case "5":
				System.out.println("Opción seleccionada: Listar todos los coches.");
				List<Coche> lista = gestor.getAllCoches();
				if (lista != null) {
					for (Coche c : lista) {
						System.out.println(c);
					}
				} else {
					System.out.println("No existen coches en la base de datos.");
				}

				break;

			case "6":
				System.out.println("Opción seleccionada: Gestion de pasajeros.");
				
				interPas.menuPasajero();

				break;

			case "0":
				System.out.println();
				System.out.println("Fin del programa.");
				break;

			default:
				System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
				break;
			}
		} while (!opcion.equals("0"));

	}

	/**
	 * Pide los datos de Coche, menos el ID.
	 * 
	 * @return Devuelve un Coche con su marca, modelo, motor y kilometros.
	 */
	private Coche pedirDatos() {

		Coche c = new Coche();
		String marca = "";
		String modelo = "";
		Motor motor = null;
		double km = 0;

		boolean flag = false;

		System.out.println("Ingrese la Marca:");
		marca = scannerString.nextLine();
		while (marca.isBlank()) {
			System.out.println("- La marca no puede estar vacia o contener solo espacios.");
			System.out.println("Ingrese la Marca:");
			marca = scannerString.nextLine();
		}

		System.out.println("Ingrese el Modelo:");
		modelo = scannerString.nextLine();
		while (marca.isBlank()) {
			System.out.println("- El modelo no puede estar vacia o contener solo espacios.");
			System.out.println("Ingrese El Modelo:");
			marca = scannerString.nextLine();
		}

		do {
			flag = false;

			System.out.println("Eligue el Motor:");

			int i = 0;

			for (Motor m : Motor.values()) {
				System.out.println("[" + ++i + "] " + m);
			}

			String opcion = scannerString.nextLine();

			switch (opcion) {
			case "1":
				motor = Motor.DIESEL;
				break;
			case "2":
				motor = Motor.GASOLINA;
				break;
			case "3":
				motor = Motor.ELECTRICO;
				break;
			default:
				System.out.println("Opción incorrecta.");
				flag = true;
				break;
			}

		} while (flag);

		do {
			flag = false;

			System.out.println("Ingrese los kilometros:");
			try {
				km = scannerNum.nextDouble();

			} catch (Exception e) {
				System.out.println("Debe ingresar kilometros validos.");
				flag = true;
			}
		} while (flag);

		c.setMarca(marca);
		c.setModelo(modelo);
		c.setMotor(motor);
		c.setKilometros(km);

		return c;
	}

	/**
	 * Valida si un Coche pasado por parametro es valido.
	 *
	 * @param coche a validar.
	 * @return 1 solo si el coche es valido, 0 si el coche es null, 2 la marca no es
	 *         valida, 3 el modelo no es valido, 4 el motor no es valido, 5 los
	 *         kilometros no son validos.
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
