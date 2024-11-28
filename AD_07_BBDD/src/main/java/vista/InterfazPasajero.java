package vista;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorPasajero;
import modelo.persistencia.DaoPasajeroImplement;

public class InterfazPasajero {

	private GestorCoche gestorCoche = GestorCoche.getInstance();
	DaoPasajeroImplement daoPasajero = DaoPasajeroImplement.getInstance();
	// private GestorPasajero gestorPasajero = GestorPasajero.getInstance();
	static Scanner scannerString = new Scanner(System.in);
	static Scanner scannerNum = new Scanner(System.in);

	String opcion;

	public void menuPasajero() {
		do {
			System.out.println("\n== Menú de Pasajeros ==");
			System.out.println("[1] Crear nuevo pasajero");
			System.out.println("[2] Borrar pasajero por ID");
			System.out.println("[3] Consulta pasajero por ID");
			System.out.println("[4] Listar todos los pasajeros");
			System.out.println("[5] Añadir pasajero a Coche");
			System.out.println("[6] Eliminar pasajero de un Coche");
			System.out.println("[7] Listar todos los pasajeros de un Coche");
			System.out.println("[0] Volver atrás");
			System.out.print("Seleccione una opción: ");

			opcion = scannerString.nextLine();

			switch (opcion) {
			case "1" -> crearNuevoPasajero();
			case "2" -> borrarPasajeroPorId();
			case "3" -> consultarPasajeroPorId();
			case "4" -> listarTodosLosPasajeros();
			case "5" -> anadirPasajeroACoche();
			case "6" -> eliminarPasajeroDeCoche();
			case "7" -> listarPasajerosDeCoche();
			case "0" -> System.out.println();
			default -> System.out.println("Opción no válida. Inténtelo de nuevo.");
			}

		} while (!opcion.equals("0"));

	}

	// [1] Crear nuevo pasajero
	public void crearNuevoPasajero() {
		System.out.println("== Crear nuevo pasajero ==");

		Pasajero ps = new Pasajero();

		ps.setNombre("Piero");
		ps.setEdad(30);
		ps.setPeso(60.5);

		System.out.println(daoPasajero.insertPasajero(ps));

	}

	// [2] Borrar pasajero por ID
	public void borrarPasajeroPorId() {
		System.out.println("== Borrar pasajero por ID ==");
		// Lógica para borrar un pasajero por su ID

		int id = 0;
		boolean flag;

		do {
			System.out.println("Ingrese el ID del Pasajero:");
			flag = false;

			try {
				id = scannerNum.nextInt();
			} catch (Exception e) {
				System.out.println("Formato ID no valido, vuelve a ingresarlo.");
				flag = true;
			}
		} while (flag);

		boolean result = daoPasajero.deletePasajeroById(id);

		String borrado = (result == true) ? "Pasajero borrado" : "No se borro ningun Pasajero.";

		System.out.println(borrado);

	}

	// [3] Consultar pasajero por ID
	public void consultarPasajeroPorId() {

		Pasajero pasajero = null;

		System.out.println("== Consultar pasajero por ID ==");
		// Lógica para consultar un pasajero por su ID

		System.out.println("Ingrese el ID del pasajero:");
		int idPasajero = scannerNum.nextInt();

		pasajero = daoPasajero.getPasajeroById(idPasajero);

		System.out.println(pasajero);
		System.out.println();
	}

	// [4] Listar todos los pasajeros
	public void listarTodosLosPasajeros() {
		System.out.println("== Listar todos los pasajeros ==");
		for (Pasajero pasajero : daoPasajero.getAllPasajeros()) {
			System.out.println(pasajero);
		}
	}

	// [5] Añadir pasajero a Coche
	public void anadirPasajeroACoche() {
		Pasajero pasajero = null;

		System.out.println("== Añadir pasajero a Coche ==");
		System.out.println("Ingrese el ID del pasajero:");
		int idPasajero = scannerNum.nextInt();

		pasajero = daoPasajero.getPasajeroById(idPasajero);

		System.out.println(pasajero);
		System.out.println();

		List<Coche> listaCoches = gestorCoche.getAllCoches();

		for (Coche coche : listaCoches) {
			System.out.println(coche);
		}

		System.out.println("Ingrese el ID del coche:");
		int idCoche = scannerNum.nextInt();

		Coche c = gestorCoche.getCocheById(idCoche);

		if (c != null && pasajero != null) {

			if (pasajero.getIdCoche() != 0) {
				Coche c2 = gestorCoche.getCocheById(pasajero.getIdCoche());
				c2.setNumeroPasajeros(c2.getNumeroPasajeros() - 1);
				gestorCoche.updateCoche(c2);
			}

			pasajero.setIdCoche(c.getId());
			c.setNumeroPasajeros(c.getNumeroPasajeros() + 1);

			daoPasajero.updatePasajero(pasajero);
			gestorCoche.updateCoche(c);

			System.out.println("~ Pasajero añadido.");

		}

	}

	// [6] Eliminar pasajero de un Coche
	public void eliminarPasajeroDeCoche() {
		System.out.println("== Eliminar pasajero de un Coche ==");
		// Lógica para eliminar un pasajero de un coche

		List<Coche> listaC = gestorCoche.getAllCoches();

		List<Pasajero> listaP = daoPasajero.getAllPasajeros();

		int i = 1;
		for (Coche c : listaC) {
			if (c.getNumeroPasajeros() > 0) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println("[" + i++ + "]");
				System.out.println(c);

				for (Pasajero pasajero : listaP) {
					if (pasajero.getIdCoche() == c.getId()) {
						System.out.println(pasajero);
					}
				}
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			}
		}

	}

	// [7] Listar todos los pasajeros de un Coche
	public void listarPasajerosDeCoche() {
		System.out.println("== Listar todos los pasajeros de un Coche ==");
		// Lógica para listar los pasajeros de un coche
	}

}
