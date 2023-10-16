package reto3a;

/**
 * Nodo que forma la cadena de nodos de la lista
 * 
 * @param <T> Tipo de dato que almacena el nodo
 */
public class Nodo <T extends Comparable<T>> {
	
	/**
	 * Elemento almacenado en el nodo
	 */
	private T contenido;
	
	/**
	 * REferencia al nodo siguiente
	 */
	private Nodo<T> siguiente;
	
	/**
	 * Referencia al nodo anterior
	 */
	private Nodo<T> anterior;
	
	/**
	 * Constructor
	 * 
	 * @param contenido Elemento a almacenar
	 * 
	 * @param siguiente Refrencia al nodo siguiente
	 * @param anterior Referencia al nodo anterior
	 */
	public Nodo(T contenido, Nodo<T> siguiente,Nodo<T>anterior) {
		this.contenido=contenido;
		this.siguiente=siguiente;
		this.anterior = anterior;
	}

	public T getContenido() {
		return contenido;
	}

	public void setContenido(T contenido) {
		this.contenido = contenido;
	}

	public Nodo<T> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}

	public Nodo<T> getAnterior() {
		return anterior;
	}

	public void setAnterior(Nodo<T> anterior) {
		this.anterior = anterior;
	}
	
	
}
