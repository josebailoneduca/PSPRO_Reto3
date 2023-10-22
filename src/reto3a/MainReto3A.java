package reto3a;


/**
 * Punto de entrada para el Reto 3 apartado A
 * 
 * RETO 3 A:
 * 
 * Crear una lista doblemente enlazada circular ordenada que implemente la busqueda de un elemento usando 2 hebras que busquen 
 * cada una dese el inicio y desde el final de la lista respectivamente
 */
public class MainReto3A {

	
	 
	
	public static void main(String[] args) {
 		//creacion de la lista
		ListaDobleCircularOrdenada<String> miLista=new ListaDobleCircularOrdenada<String>();
		miLista.add("B");
		miLista.add("D");
		miLista.add( "V");
		miLista.add( "C");
		miLista.add("E");
		miLista.add("F");
		miLista.add( "D");
		miLista.add( "J");
		miLista.add( "A");
		miLista.add( "I");
		miLista.add( "G");
		miLista.add( "H");
		System.out.println("Contenido de la lista tras agregar B,D,V,C,E,F,D,J,A,I,G,H:");
		System.out.println(miLista.listar());
		//listados internos
		System.out.println("\nLista con toString\n----------------");
		System.out.println(miLista);
		System.out.println("\nLista con metodo listar()\n----------------");
		System.out.println(miLista.listar());

		
		//borrado por indice
		System.out.println("\nBorrado por indice\n----------------");
		System.out.println("Borrado de posicion primera:");
		miLista.remove(0);
		System.out.println(miLista.listar());
		System.out.println("Borrado de posicion intermedia 2:");
		miLista.remove(2);
		System.out.println(miLista.listar());
		System.out.println("Borrado de posicion final:");
		miLista.remove(miLista.size()-1);
		System.out.println(miLista.listar());
		
		try {
			miLista.remove(10);
		}catch (IndexOutOfBoundsException e) {
			System.out.println("Al intentar borrar el indice 10: "+e.getMessage());
		}
		
		//borrado por elemento
		System.out.println("\nBorrado por elemento\n----------------");
		System.out.println("Borrado de elemento B estando primero:");
		miLista.remove("B");
		System.out.println(miLista.listar());
		System.out.println("Borrado de elemento F estando intermedio:");
		miLista.remove("F");
		System.out.println(miLista.listar());
		System.out.println("Borrado de elemento J estando en posicion final:");
		miLista.remove("J");
		System.out.println(miLista.listar());
		System.out.println("Al intentar borrar un elemento(W) no existente devuelve: "+miLista.remove("W"));
		System.out.println(miLista.listar());
		

		
		//recogida de posiciones
		System.out.println("\nRecogida de posiciones (-1 si no está)\n----------------");
		System.out.println("Posicion de 'A':"+miLista.contains("A"));
		System.out.println("Posicion de 'B':"+miLista.contains("B"));
		System.out.println("Posicion de 'C':"+miLista.contains("C"));
		System.out.println("Posicion de 'D':"+miLista.contains("D"));
		System.out.println("Posicion de 'E':"+miLista.contains("E"));
		System.out.println("Posicion de 'F':"+miLista.contains("F"));
		System.out.println("Posicion de 'G':"+miLista.contains("G"));
		System.out.println("Posicion de 'H':"+miLista.contains("H"));
		System.out.println("Posicion de 'I':"+miLista.contains("I"));
		System.out.println("Posicion de 'Z':"+miLista.contains("Z"));
 
		//listados externos
		System.out.println("\nListado con bucle externo\n--------------------");
		System.out.println("Listado al derecho:");
		String elemento = miLista.getFirst();
		do {
			System.out.println(elemento);
			elemento=miLista.getNext();
		}while(!miLista.isFirst());
		
		System.out.println("\nListado al reves:");
		String elemento2 = miLista.getLast();
		do {
			System.out.println(elemento2);
			elemento2=miLista.getPrevious();
		}while(!miLista.isLast());
	}

}
