package reto3a;

import java.util.Random;
import java.util.Scanner;

/**
 * Punto de entrada para el Reto 3 apartado A
 * 
 * RETO 3 A:
 * 
 * Crear una lista doblemente enlazada circular ordenada que implemente la
 * busqueda de un elemento usando 2 hebras que busquen cada una desde el inicio y
 * desde el final de la lista respectivamente
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class MainReto3A {

	/**
	 * Main del programa
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		// creacion de la lista
		ListaDobleCircularOrdenada<String> miLista = new ListaDobleCircularOrdenada<String>();
		miLista.add("B");
		miLista.add("D");
		miLista.add("V");
		miLista.add("C");
		miLista.add("E");
		miLista.add("F");
		miLista.add("D");
		miLista.add("J");
		miLista.add("A");
		miLista.add("I");
		miLista.add("G");
		miLista.add("H");
		System.out.println("Contenido de la lista tras agregar y en este orden B,D,V,C,E,F,D,J,A,I,G,H:");
		System.out.println(miLista.listar());

		// listados internos
		System.out.println("\n--------------------------------\nLista con toString\n--------------------------------");
		System.out.println(miLista);
		System.out.println(
				"\n--------------------------------\nLista con metodo listar()\n--------------------------------");
		System.out.println(miLista.listar());

		// borrado por indice
		System.out.println("\n--------------------------------\nBorrado por indice\n--------------------------------");
		System.out.println("Estado previo a los borrados:");
		System.out.println(miLista.listar());
		System.out.println("\nBorrado de posicion primera 0"); 
		System.out.println("Devuelve: "+miLista.remove(0));
		System.out.println(miLista.listar());
		System.out.println("\nBorrado de posicion intermedia 4");
		System.out.println( "Devuelve: "+miLista.remove(4));
		System.out.println(miLista.listar());
		System.out.println("\nBorrado de posicion final(" + (miLista.size() - 1) + ")");
		System.out.println( "Devuelve: "+miLista.remove(miLista.size() - 1));
		System.out.println(miLista.listar());
		try {
			miLista.remove(10);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("\nAl intentar borrar el indice 10(que está fuera de rango) salta excepcion con mensaje: "
					+ e.getMessage());
		}
		System.out.println(miLista.listar());

		// borrado por elemento
		System.out
				.println("\n--------------------------------\nBorrado por elemento\n--------------------------------");
		System.out.println("Estado previo a los borrados:");
		System.out.println(miLista.listar());
		System.out.println("\nBorrado de elemento B estando primero:");
		System.out.println("Devuelve posicion:" + miLista.remove("B"));
		System.out.println(miLista.listar());
		System.out.println("\nBorrado de elemento F estando intermedio:");
		System.out.println("Devuelve posicion:" + miLista.remove("F"));
		System.out.println(miLista.listar());
		System.out.println("\nBorrado de elemento J estando en posicion final:");
		System.out.println("Devuelve posicion:" + miLista.remove("J"));
		System.out.println(miLista.listar());
		System.out.println("\nBorrado de elemento D estando repetido:");
		System.out.println("Devuelve posicion:" + miLista.remove("D"));
		System.out.println(miLista.listar());
		System.out.println("\nBorrado de elemento W no existente:");
		System.out.println("Devuelve posicion: " + miLista.remove("W"));
		System.out.println(miLista.listar());

		// recogida de posiciones
		System.out.println(
				"\n--------------------------------\nRecogida de posiciones (-1 si no está)\n--------------------------------");
		System.out.println("Posicion de 'A':" + miLista.contains("A"));
		System.out.println("Posicion de 'B':" + miLista.contains("B"));
		System.out.println("Posicion de 'C':" + miLista.contains("C"));
		System.out.println("Posicion de 'D':" + miLista.contains("D"));
		System.out.println("Posicion de 'E':" + miLista.contains("E"));
		System.out.println("Posicion de 'F':" + miLista.contains("F"));
		System.out.println("Posicion de 'G':" + miLista.contains("G"));
		System.out.println("Posicion de 'H':" + miLista.contains("H"));
		System.out.println("Posicion de 'I':" + miLista.contains("I"));
		System.out.println("Posicion de 'Z':" + miLista.contains("Z"));

		// listados externos
		System.out.println(
				"\n--------------------------------\nListado con bucle externo\n------------------------------------");
		System.out.println("Listado al derecho:");
		String elemento = miLista.getFirst();
		do {
			System.out.println(elemento);
			elemento = miLista.getNext();
		} while (!miLista.isFirst());

		System.out.println("\nListado al reves:");
		String elemento2 = miLista.getLast();
		do {
			System.out.println(elemento2);
			elemento2 = miLista.getPrevious();
		} while (!miLista.isLast());
		System.out.println("\n(PULSE INTRO PARA CONTINUAR)");
		s.nextLine();

		
		
		
		/*
		 * Creacion de lista para pruebas
		 */
		ListaDobleCircularOrdenada<Integer> lista = new ListaDobleCircularOrdenada<Integer>();
		
		System.out.println("Se va a crear una lista con numeros enteros aleatorios.");
		boolean recogido=false;
		int cantidad = 30000;
		while(!recogido) {
			System.out.println("Introduzca la cantidad de elementos que quiere agregar o no especifique ninguno para agregar 30000 elementos:");
			String entrada = s.nextLine();
			if (entrada.equals("")) {
				recogido=true;
				cantidad=30000;
			}
			else {
				try {
					cantidad=Integer.parseInt(entrada);
					if (cantidad>=0) 
						recogido=true;
					}
				catch(NumberFormatException e) {}
			}
		}

		//cantidad de elementos a agregar a la lista
		System.out.println("Creando lista con " + cantidad + " elementos");
		Random r = new Random();
		for (int i = 0; i < cantidad; i++) {
			lista.add(r.nextInt());
		}
		System.out.println(cantidad + " elementos insertados.\n (Pulse intro para continuar.)");
		s.nextLine();

		
		while (true) {
			System.out.println(lista.listar());
			System.out.println("OPCIONES:");
			System.out.println("(0)Agregar elemento");
			System.out.println("(1)getFirst");
			System.out.println("(2)getLast");
			System.out.println("(3)getNext");
			System.out.println("(4)getPrevious");
			System.out.println("(5)Buscar");
			System.out.println("(6)Salir");
			System.out.println("Elija una opcion:");
			String opcion = s.nextLine();
			switch (opcion) {
			case "0": 
				System.out.println("Introduzca un numero entero: ");
				try {
					lista.add(Integer.parseInt(s.nextLine()));
				}catch(NumberFormatException e) {System.out.println("Numero no valido");}
				break;
			case "1": 
				System.out.println("Elemento actual: "+lista.getFirst());
				break;
			case "2": 
				System.out.println("Elemento actual: "+lista.getLast());
				break;
			case "3": 
				try{System.out.println("Elemento actual: "+lista.getNext());}catch(IllegalStateException e) {System.out.println("Excepcion capturada:"+e.getMessage());}
				break;
			case "4": 
				try{System.out.println("Elemento actual: "+lista.getPrevious());}catch(IllegalStateException e) {System.out.println("Excepcion capturada:"+e.getMessage());}
				break;
			case "5": 
				try {
					System.out.println("Introduzca un numero entero:");
					int busqueda = Integer.parseInt(s.nextLine().trim());
					int indice = lista.contains(busqueda);
					if (indice==-1)
						System.out.println("La lista no tiene el elemento");
					else 
						System.out.println("El elemento esta en la posicion: "+indice);
				}
				catch(NumberFormatException e) {System.out.println("La busqueda debe ser un numero entero");}
				break;
			case "6": 
				System.exit(0);
				break;

			}
			System.out.println("\n(PULSE INTRO PARA CONTINUAR)");
				s.nextLine();
			
		}

	}

}

