package reto3a;


public class MainReto3A {

	
	// preguntar si en la lista circular doble enlazada al acerla ordenada debemos permitir la insercion de elementos no ordenados
	
	public static void main(String[] args) {
		//creacion de la lista
		ListaDobleCircularOrdenada<String> miLista=new ListaDobleCircularOrdenada<String>();
		miLista.add("B");
		miLista.add("D");
		miLista.add( "V");
		miLista.add( "C");
		miLista.add("E");
		miLista.add("F");
		miLista.add( "V");
		miLista.add( "A");
		//listados internos
		System.out.println("\nLista desde la clase\n----------------");
		System.out.println(miLista.listar());
		System.out.println(miLista);

		
		//borrado
		System.out.println("\nBorrado\n----------------");
		System.out.println("Borrado de posicion primera");
		miLista.remove(0);
		System.out.println(miLista.listar());
		System.out.println("Borrado de posicion intermedia 2");
		miLista.remove(2);
		System.out.println(miLista.listar());
		System.out.println("Borrado de posicion final");
		miLista.remove(miLista.size()-1);
		System.out.println(miLista.listar());

		try {
		miLista.remove(10);
		}catch (Exception e) {
			System.out.println("Al intentar borrar 10:"+e.getMessage());
		}
		
		//recogida de posiciones
		System.out.println("\nRecogida de posiciones\n----------------");
		System.out.println("Posicion de 'B':"+miLista.contains("B"));
		System.out.println("Posicion de 'C':"+miLista.contains("C"));
		System.out.println("Posicion de 'E':"+miLista.contains("E"));
		System.out.println("Posicion de 'Z':"+miLista.contains("Z"));
 
		//listados externos
		System.out.println("\nListado externo\n--------------------");
		System.out.println("Al derecho");
		String elemento = miLista.getFirst();
		do {
			System.out.println(elemento);
			elemento=miLista.getNext();
		}while(!miLista.isFirst());
		
		System.out.println("\nListado al reves\n------------");
		String elemento2 = miLista.getLast();
		do {
			System.out.println(elemento2);
			elemento2=miLista.getPrevious();
		}while(!miLista.isLast());
	}

}
