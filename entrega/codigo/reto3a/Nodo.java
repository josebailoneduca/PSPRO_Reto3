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

	/**
	 * Devuelve el contenido del nodo
	 * @return El contenido
	 */
	public T getContenido() {
		return contenido;
	}

	/**
	 * Define el contenido del nodo
	 * @param contenido El elemento a contener
	 */
	public void setContenido(T contenido) {
		this.contenido = contenido;
	}

	/**
	 * Devuelve el nodo siguiente
	 * @return El nodo
	 */
	public Nodo<T> getSiguiente() {
		return siguiente;
	}

	/**
	 * Define el nodo siguiente
	 * @param siguiente El nodo a definir
	 */
	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}

	/**
	 * Devuelve el nodo anterior
	 * @return El nodo
	 */
	public Nodo<T> getAnterior() {
		return anterior;
	}

	/**
	 * Define el nodo anterior
	 * @param anterior El nodo a definir
	 */
	public void setAnterior(Nodo<T> anterior) {
		this.anterior = anterior;
	}
	
	
}
