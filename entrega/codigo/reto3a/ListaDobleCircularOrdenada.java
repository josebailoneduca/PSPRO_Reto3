package reto3a;

import java.security.InvalidParameterException;

/**
 * Lista de elementos enlazados doblemente ciruclar. Esta constituida por una sucesion de nodos doblemente enlazados entre si, 
 * estando enlazados entre si el ultimo y el primer nodo. Los elementos se ordenan en el momento de la insercion. 
 * 
 * @author Jose Javier Bailon Ortiz
 * 
 * @param <T> Clase de los elementos a guardar en la lista
 */

public class ListaDobleCircularOrdenada<T extends Comparable<T>> implements ListaDobleCiruclarOrdenadaInterface<T> {

	// CLASE ANIDADA
	// CABECERA******************************************************************
	/**
	 * Clase cabecera Almacena referencias al primer nodo, el ultimo y el actual
	 */
	private class Cabecera {
		/**
		 * Nodo inicial
		 */
		private Nodo<T> first;

		/**
		 * Nodo final
		 */
		private Nodo<T> tail;

		/**
		 * Nodo en el puntero de lectura
		 */
		private Nodo<T> actual;

		/**
		 * Constructor
		 */
		public Cabecera() {
			this.first=null;
			this.tail=null;
			this.actual=null;
		}
		// GETTERS Y SETTERS
		/**
		 * Define el primer nodo
		 * @param nodo El nodo
		 */
		public void setFirst(Nodo<T> nodo) {
			this.first = nodo;
		}

		/**
		 * Define el ultimo nodo
		 * @param nodo El nodo
		 */
		public void setTail(Nodo<T> nodo) {
			this.tail = nodo;
		}

		/**
		 * Devuelve el primer nodo
		 * @return El nodo
		 */
		public Nodo<T> getFirst() {
			return this.first;
		}
		
		/**
		 * Devuelve el ultimo nodo
		 * @return El nodo
		 */
		public Nodo<T> getTail() {
			return this.tail;
		}

		/**
		 * Devuelve el nodo actual de lectura
		 * @return El nodo
		 */
		public Nodo<T> getActual() {
			return actual;
		}
		
		/**
		 * Establece el nodo actual de lectura
		 * @param nodo El nodo
		 */
		public void setActual(Nodo<T> nodo) {
			this.actual = nodo;
		}

	}

	// ATRIBUTOS
	// *********************************************************************

	/**
	 * Apunta al nodo inicial, final y actual
	 */
	private Cabecera cabecera;

	/**
	 * Almacena la cantidad de elementos
	 */
	private int cantidad;

	/**
	 * Constructor
	 */
	public ListaDobleCircularOrdenada() {
		this.cabecera = new Cabecera();
		this.cabecera.setActual(null);
		this.cabecera.setTail(null);
		this.cantidad = 0;
	}

	
	/**
	 * Agrega un elemento de manera ordenada en la lista.
	 * 
	 * Establece un caso para cuando es el primer elemento que se agrega a la lista y otro caso para los demas
	 * 
	 * Si es el primero se establece como first y tail de la cabecera
	 * 
	 * Si es otro caso se buscan los nodos que deben ponerse como anterior y como siguiente, se hacen los enlaces
	 * para insertar el nuevo nodo y se actualiza la cabecera segun sea necesario
	 */
	@Override
	public void add(T elemento) {
		Nodo<T> nuevo = new Nodo<T>(elemento, null, null);
		Nodo<T> anterior = null;
		Nodo<T> siguiente = null;
		
		
		// SI ES PRIMER ELEMENTO AGREGADO A LA LISTA
		if (this.cabecera.getFirst() == null) {
			this.cabecera.setFirst(nuevo);
			this.cabecera.setTail(nuevo);
			nuevo.setSiguiente(nuevo);
			nuevo.setAnterior(nuevo);
			this.cantidad++;
			return;

		}

		//SI ES AGREGADO A LA LISTA CUANDO YA TIENE ELEMENTOS
		
		//indice dentro de la lista
		int i = 0;
		
		
		//BUSQUEDA DE LA POSICION EN LA QUE INSERTAR EL ELEMENTO
		//nodo actual en el proceso de busqueda de la posicion adecuada de insercion
		Nodo<T> actual = this.cabecera.getFirst();

		//se recorre la lista para calcula cual sera el nodo anterior y cual el nodo posterior
		boolean posicionEncontrada = false;
		while (!posicionEncontrada) {
			// Si se ha llegado al final hay que agregarlo tras el ultimo
			if (i == this.size()) {
				anterior = this.cabecera.getTail();
				siguiente = this.cabecera.getFirst();
				posicionEncontrada = true;
				
				
				// si el compareTo devuelve positivo es que es mas grande comparativamente y 
				// por lo tanto hay que agregarlo justo antes del actual
			} else  if (actual.getContenido().compareTo(elemento) > 0){
				anterior = actual.getAnterior();
				siguiente = actual;
				posicionEncontrada = true;
			
				// si aun no se ha llegado al final y es menor o igual que el elemento 
				// entonces pasamos al siguiente nodo
			} else {
				actual = actual.getSiguiente();
				i++;
			}
		}

		//INSERTAR NODO
		anterior.setSiguiente(nuevo);
		nuevo.setAnterior(anterior);
		nuevo.setSiguiente(siguiente);
		siguiente.setAnterior(nuevo);
		
		//ACTUALIZAR CABECERA
		// si el anterior es tail y no lo estamos agregando al final
		//estamos introduciendo en primera posicion
		//por tanto hay que actualizar first en cabecera
		if (anterior.equals(this.cabecera.getTail())&&i!=this.cantidad)
			this.cabecera.setFirst(nuevo);
		
		//si se agrega al final hay que actualizar tail en cabecera
		if (i==this.cantidad)
			this.cabecera.setTail(nuevo);
		
		//AUMENTAR LA CANTIDAD
		this.cantidad++;
	}

	
	
	
	/**
	 * Elimina una posicion de la lista y actualiza la cabecera si es necesario
	 * @param indice Posicion a eliminar
	 * @return El valor almacenado en el nodo eliminado
	 * @throws IndexOutOfBoundsException Si el indice no existe
	 */
	@Override
	public T remove(int indice)  throws IndexOutOfBoundsException{
		// comprobacion de posicion valida
		if (indice >= this.cantidad || indice < 0)
			throw new IndexOutOfBoundsException("Indice "+indice+" no válido ");

		//elemento que se devolvera
		T elemento = null;

		// caso de solo 1 elemento en la lista
		if (this.cantidad == 1) {
			elemento = this.cabecera.getFirst().getContenido();
			this.cabecera.setFirst(null);
			this.cabecera.setTail(null);
			this.cabecera.setActual(null);

			// caso de 2 elementos o mas
		} else {
			//coger el nodo de la posicion
			Nodo<T> aBorrar = getNodoAt(indice);
			//guardar el elemento para el return del metodo
			elemento = aBorrar.getContenido();

			//Cambio en los enlaces de nodos anterior y siguiente para eliminar el nodo 
			Nodo<T> anterior = aBorrar.getAnterior();
			Nodo<T> siguiente = aBorrar.getSiguiente();
			anterior.setSiguiente(siguiente);
			siguiente.setAnterior(anterior);

			// si el borrado era el primero actualizamos el first en cabecera
			if (indice == 0)
				this.cabecera.setFirst(siguiente);

			// si el borrado era el ultimo actualizamos el tail en cabecera
			if (indice == this.cantidad - 1)
				this.cabecera.setTail(anterior);
		}
		// actualizar cantidad
		this.cantidad--;

		//devolver el elemento almacenado en el nodo eliminado
		return elemento;
	}

	@Override
	public int remove(T elemento) {
		int posicion = contains(elemento);
		if (posicion != -1)
			remove(posicion);
		return posicion;
	}

	
	
	
	
	
	/*
	 * Implementa la busqueda con dos hebras que recorren la lista en direcciones
	 * opuestas desda cada extremo comprobando cada una la mitad de la lista y
	 * esperando a que terminen. Cuando alguna hebra termina por haber encontrado el
	 * elemento se termina la espera y se paran ambas hebras. Si ambas hebras han
	 * terminado por si solas tambien se termina la espera aunque ninguna haya
	 * encontrado el elemento buscado.
	 * 
	 * @param elementoBusquda Elemento a buscar
	 * @return indice del elemento buscado o -1 si no se ha encontrado
	 */
	
	
	@Override
	public int contains(T elementoBusqueda) {

		// si esta vacia retornamos -1 directamente
		if (this.cantidad == 0)
			return -1;

		// posiciones a recorrer por el hilo ascendente
		int nPosBuscarAsc = this.size() / 2;
		// posiciones a recorrer por el hilo descend3ente
		int nPosBuscarDesc = this.size() - nPosBuscarAsc;

		// hilos buscadores
		Buscador<T> buscadorAscendente = new Buscador<T>(true, // ascendente
				this.cabecera.getFirst(), // nodo inicial
				nPosBuscarAsc, // posiciones a recorrer
				elementoBusqueda// elemento a buscar
		);// objeto en el que guardar el resultado de la busqueda

		Buscador<T> buscadorDescendente = new Buscador<T>(false, this.cabecera.getTail(), nPosBuscarDesc,
				elementoBusqueda);

		// inicio de los hilos
		buscadorAscendente.start();
		buscadorDescendente.start();

		// comprobacion de si hay que parar
		boolean pararEspera = false;
		while (!pararEspera) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// si ambos han terminado se termina la espera la espera
			if (buscadorAscendente.isTerminado() && buscadorDescendente.isTerminado())
				pararEspera = true;

			// si ascendete ha terminado y ademas ha encontrado se termina la espera
			else if (buscadorAscendente.isTerminado() && buscadorAscendente.isEncontrado())
				pararEspera = true;

			// si descendente ha terminado y ademas ha encontrado se termina la espera
			else if (buscadorDescendente.isTerminado() && buscadorDescendente.isEncontrado())
				pararEspera = true;
		}
		// obligar a parar a ambos hilos por si la espera anterior ha terminado por
		// haberse encontrado el resultado en alguno de los hilos
		buscadorAscendente.setTerminado(true);
		buscadorDescendente.setTerminado(true);

		// asegurar el fin de los hilos antes de comprobar resultado
		try {
			buscadorAscendente.join();
			buscadorDescendente.join();
		} catch (InterruptedException e) {
		}

		// si el ascendente ha encontrado devolvemos el resultado ascendente
		if (buscadorAscendente.isEncontrado())
			return buscadorAscendente.getResultado();

		// si el descendente ha encontrado devolvemos el resultado calculado de
		// descendente
		// el calculo es: tamano-1-(pasos dados por el hilo descendente)
		if (buscadorDescendente.isEncontrado())
			return this.size() - 1 - buscadorDescendente.getResultado();

		// si ninguna ha encontrado resultado devuelve -1
		return -1;
	}

	/**
	 * Devuelve un string con un listado de los elementos de la lista
	 */
	@Override
	public String listar() {
		String salida = "[";
		// si no esta vacio lo recorremos
		if (!this.isEmpty()) {
			Nodo<T> posicion = cabecera.getFirst();
			do {
				salida += posicion.getContenido() + " , ";
				posicion = posicion.getSiguiente();
			} while (posicion != cabecera.getFirst());
		}
		if (salida.length() > 3)
			salida = salida.substring(0, salida.length() - 3);
		salida += "]";
		return salida;
	}

	/**
	 * Devuelve el elemento del inicio de la lista y pone la cabeza lectora apuntando al inicio
	 */
	@Override
	public T getFirst() {
		if (!this.isEmpty()) {
			this.cabecera.setActual(cabecera.getFirst());
			return cabecera.getFirst().getContenido();
		} else
			return null;
	}

	/**
	 * Devuelve el elemento del final de la lista y pone la cabeza lectora apuntando al final
	 */
	@Override
	public T getLast() {
		if (!this.isEmpty()) {
			this.cabecera.setActual(cabecera.getTail());
			return cabecera.getTail().getContenido();
		} else
			return null;
	}

	/**
	 * Avanza el puntero de lectura y devuelve el valor 
	 */
	@Override
	public T getNext() {
		if (this.cabecera.getActual() != null) {
			this.cabecera.setActual(this.cabecera.getActual().getSiguiente());
		}
		return (this.cabecera.getActual() != null) ? this.cabecera.getActual().getContenido() : null;
	}

	/**
	 * Atrasa el puntero de lectura y devuelve el valor
	 */
	@Override
	public T getPrevious() {
		if (this.cabecera.getActual() != null) {
			this.cabecera.setActual(this.cabecera.getActual().getAnterior());
		}
		return (this.cabecera.getActual() != null) ? this.cabecera.getActual().getContenido() : null;
	}

	
	/**
	 * Vacia la lista poniendo los atributos de la cabecera a null
	 */
	@Override
	public void delete() {
		this.cabecera.setFirst(null);
		this.cabecera.setTail(null);
		this.cabecera.setActual(null);
		this.cantidad = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.cantidad == 0;
	}

	@Override
	public int size() {
		return this.cantidad;
	}

	@Override
	public String toString() {
		return "Lista [cantidad=" + cantidad + ", elementos=" + this.listar() + "]";
	}

	@Override
	public T getAt(int indice) throws IndexOutOfBoundsException{
		if (indice >= this.size() || indice < 0)
			throw new IndexOutOfBoundsException("Indice "+indice+" no válido");
		try {
			return this.getNodoAt(indice).getContenido();
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	/**
	 * Devuelve si el puntero de lectura apunta al inicio
	 */
	@Override
	public boolean isFirst() {
		Nodo<T> actual = this.cabecera.getActual();
		Nodo<T> first = this.cabecera.getFirst();
		boolean resultado = actual.equals(first);
		return resultado;
	}

	
	/**
	 * Devuelve si el puntero de lectura apunta al final
	 */
	@Override
	public boolean isLast() {
		Nodo<T> actual = this.cabecera.getActual();
		Nodo<T> tail = this.cabecera.getTail();
		boolean resultado = actual.equals(tail);
		return resultado;
	}

	/**
	 * Devuelve el nodo de la posicion indicada
	 * 
	 * @param posicion La posicion del nodo siendo 0 el primer elemento de la lista
	 * @return el nodo de la posicion o null
	 */
	private Nodo<T> getNodoAt(int posicion) {
		if (posicion >= this.size() || posicion < 0 || this.isEmpty())
			throw new InvalidParameterException("Indice no válido");
		Nodo<T> actual = cabecera.getFirst();
		int posActual = 0;
		while (posActual < posicion && posActual < this.cantidad - 1) {
			actual = actual.getSiguiente();
			posActual++;
		}
		return actual;
	}
}
