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
			Nodo<T> actual = this.cabecera.getFirst();
			while (!agregado) {
				//Si hay que agregarlo al final 
				if (i==this.size()) {
					Nodo<T> ultimo=this.cabecera.getTail();
					Nodo<T> primero=this.cabecera.getFirst();
					ultimo.setSiguiente(nuevo);
					nuevo.setAnterior(ultimo);
					nuevo.setSiguiente(primero);
					primero.setAnterior(nuevo);
					this.cabecera.setTail(nuevo);
					agregado=true;
				//si aun no se ha llegado al final buscar siguiente
				}else if (actual.getContenido().compareTo(elemento) <= 0) {
						actual = actual.getSiguiente();
						i++;
					//si no es menor o igual es que es mas grande
					//por lo tanto hay que agregarlo justo antes del actual
					}else {
						Nodo<T> anterior=actual.getAnterior();
						anterior.setSiguiente(nuevo);
						nuevo.setAnterior(anterior);
						nuevo.setSiguiente(actual);
						actual.setAnterior(nuevo);
						//si el anterior es tail estamos introduciendo en primera posicion
						if(anterior.equals(this.cabecera.getTail()))
							this.cabecera.setFirst(nuevo);
						agregado=true;
					}
			}
		}

		this.cantidad++;
	}

	@Override
	public void remove(int posicion) {
		//check de posicion valida
		if (posicion >= this.cantidad || posicion < 0)
			throw new InvalidParameterException("Indice no válido");
		
		
		try {
			
			// caso de solo 1 elemento en la lista
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
	public int contains(T elementoBusqueda) {
		// si esta vacia retornamos -1 directamente
		if (this.cantidad == 0)
			return -1;
		
		int nPosInicioAsc = this.size()/2;
		int nPosInicioDesc=this.size()-nPosInicioAsc;
		ResultadoBusqueda resultadoAsc = new ResultadoBusqueda();
		ResultadoBusqueda resultadoDesc = new ResultadoBusqueda();
		Buscador<T> buscadorAsc=new Buscador<T>(
				true, 
				this.cabecera.getFirst(),
				nPosInicioAsc,
				elementoBusqueda,
				resultadoAsc);
		Buscador<T> buscadorDesc=new Buscador<T>(
				false, 
				this.cabecera.getTail(),
				nPosInicioDesc,
				elementoBusqueda,
				resultadoDesc);
		buscadorAsc.start();
		buscadorDesc.start();
		boolean parar=false;
		while (!parar) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (buscadorAsc.isTerminar()&&buscadorDesc.isTerminar())
				parar=true;
			if (buscadorAsc.isTerminar()&&buscadorAsc.isEncontrado())
				parar=true;
			if (buscadorDesc.isTerminar()&&buscadorDesc.isEncontrado())
				parar=true;
		}
		buscadorAsc.setParado(true);
		buscadorDesc.setParado(true);

		if (buscadorAsc.isEncontrado())
			return resultadoAsc.getResultado();
		if (buscadorDesc.isEncontrado())
			return this.size()-1-resultadoDesc.getResultado();
		
		return -1;
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
