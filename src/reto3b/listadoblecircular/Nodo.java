package reto3b.listadoblecircular;

public class Nodo <T> {
	private T contenido;
	private Nodo<T> siguiente;
	private Nodo<T> anterior;
	
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
