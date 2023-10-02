package reto3a;

import java.security.InvalidParameterException;

/**
 * Lista de lementos enlazados doblemente ciruclar
 * 
 * @param <T> Clase de los elementos a guardar en la lista
 */

public class ListaDobleCircularOrdenada<T extends Comparable<T>> implements ListaDobleCiruclarOrdenadaInterface<T> {

	/**
	 * Clase cabecera Almacena referencias al primero, el ultimo y el actual
	 */
	private class Cabecera {
		Nodo<T> first;
		Nodo<T> tail;
		Nodo<T> actual;

		void setFirst(Nodo<T> nodo) {
			this.first = nodo;
		}

		void setTail(Nodo<T> nodo) {
			this.tail = nodo;
		}

		Nodo<T> getFirst() {
			return this.first;
		}

		Nodo<T> getTail() {
			return this.tail;
		}

		public Nodo<T> getActual() {
			return actual;
		}

		public void setActual(Nodo<T> nodo) {
			this.actual = nodo;
		}

	}

	/**
	 * Atributos
	 */
	/**
	 * Apunta al nodo inicial, final y actual
	 */
	private Cabecera cabecera;

	/**
	 * Almacena la cantidad
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

	@Override
	public void add(T elemento) {
		Nodo<T> nuevo = new Nodo<T>(elemento, null, null);
		// caso de primer elemento
		if (this.cabecera.getFirst() == null) {
			this.cabecera.setFirst(nuevo);
			this.cabecera.setTail(nuevo);
			nuevo.setSiguiente(nuevo);
			nuevo.setAnterior(nuevo);

		} else {
			boolean agregado = false;
			int i = 0;
			int j = this.cantidad - 1;
			Nodo<T> menor = this.cabecera.getFirst();
			Nodo<T> mayor = this.cabecera.getTail();
			while (!agregado) {
				//comprobar desde fin
				if (mayor.getContenido().compareTo(elemento) > 0) {
					mayor = mayor.getAnterior();
					j--;
				} else {
					nuevo.setSiguiente(mayor.getSiguiente());
					nuevo.setAnterior(mayor);
					mayor.setSiguiente(nuevo);
					nuevo.getSiguiente().setAnterior(nuevo);
					//posicion final
					if (j==cantidad-1) {
						this.cabecera.setTail(nuevo);
						this.cabecera.getFirst().setAnterior(nuevo);
						nuevo.setSiguiente(this.cabecera.getFirst());
					}
					agregado = true;
				}
				//comprobar desde inicio
				if (!agregado) {
					if (menor.getContenido().compareTo(elemento) <= 0) {
						menor = menor.getSiguiente();
						i++;
					} else {
						nuevo.setSiguiente(menor);
						nuevo.setAnterior(menor.getAnterior());
						menor.setAnterior(nuevo);
						nuevo.getAnterior().setSiguiente(nuevo);
						if (i==0) {
							this.cabecera.setFirst(nuevo);
							this.cabecera.getTail().setSiguiente(nuevo);
							nuevo.setAnterior(this.cabecera.getTail());
						}
						agregado = true;
					}
				}
			}
		}

		this.cantidad++;
	}

	@Override
	public void remove(int posicion) {
		if (posicion >= this.cantidad || posicion < 0)
			throw new InvalidParameterException("Indice no válido");
		try {
			// caso de solo 1 elemento
			if (this.cantidad == 1) {
				this.cabecera.setFirst(null);
				this.cabecera.setTail(null);
				this.cabecera.setActual(null);

				// caso de 2 elementos o mas
			} else {
				Nodo<T> aBorrar = getNodoAt(posicion);
				Nodo<T> anterior = aBorrar.getAnterior();
				Nodo<T> siguiente = aBorrar.getSiguiente();
				anterior.setSiguiente(siguiente);
				siguiente.setAnterior(anterior);

				// si el borrado era el primero actualizamos el first en cabecera
				if (posicion == 0)
					this.cabecera.setFirst(siguiente);

				// si el borrado era el ultimo actualizamos el tail en cabecera
				if (posicion == this.cantidad - 1)
					this.cabecera.setTail(anterior);
			}

			// actualizar cantidad
			this.cantidad--;
		} catch (InvalidParameterException err) {
			throw err;
		}
	}

	@Override
	public int contains(T elemento) {
		// si esta vacia retornamos -1 directamente
		if (this.cantidad == 0)
			return -1;

		// Inicializa si es encontrado o no
		boolean encontrado = false;

		// recorrer la lista buscando el elemento
		Nodo<T> posicion = cabecera.getFirst();
		int indice = 0;
		do {
			encontrado = posicion.getContenido().equals(elemento);
			if (!encontrado) {
				posicion = posicion.getSiguiente();
				indice++;
			}
		} while (!posicion.equals(this.cabecera.getFirst()) && !encontrado);

		// retorno del resultado
		return (encontrado) ? indice : -1;
	}

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

	@Override
	public T getFirst() {
		if (!this.isEmpty()) {
			this.cabecera.setActual(cabecera.getFirst());
			return cabecera.getFirst().getContenido();
		} else
			return null;
	}

	@Override
	public T getLast() {
		if (!this.isEmpty()) {
			this.cabecera.setActual(cabecera.getTail());
			return cabecera.getTail().getContenido();
		} else
			return null;
	}

	@Override
	public T getNext() {
		if (this.cabecera.getActual() != null) {
			this.cabecera.setActual(this.cabecera.getActual().getSiguiente());
		}
		return (this.cabecera.getActual() != null) ? this.cabecera.getActual().getContenido() : null;
	}

	@Override
	public T getPrevious() {
		if (this.cabecera.getActual() != null) {
			this.cabecera.setActual(this.cabecera.getActual().getAnterior());
		}
		return (this.cabecera.getActual() != null) ? this.cabecera.getActual().getContenido() : null;
	}

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
	public T getAt(int indice) {
		if (indice >= this.size() || indice < 0)
			throw new IndexOutOfBoundsException("Indice no válido");
		try {
			return this.getNodoAt(indice).getContenido();
		} catch (NullPointerException e) {
			return null;
		}
	}

	@Override
	public boolean isFirst() {
		Nodo<T> actual = this.cabecera.getActual();
		Nodo<T> first = this.cabecera.getFirst();
		boolean resultado = actual.equals(first);
		return resultado;
	}

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
	 * @param posicion la posicion del nodo siendo 0 el primer elemento de la lista
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
