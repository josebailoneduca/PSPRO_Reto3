package reto3a;

/**
 * Se encarga de buscar un elemento en una cadena de nodos yendo en la
 * direcccion adecuada y devolviendo a traves de un objeto externo la cantidad
 * de saltos que ha tenido que dar hasta encontrarlo
 * 
 * @param <T> El tipo de alemento que almacena el nodo
 */
public class Buscador<T extends Comparable<T>> extends Thread {

	/**
	 * direccion de la busqueda en la cadena de nodos
	 */
	boolean esAscendente;

	/**
	 * el nodo actual
	 */
	Nodo<T> actual;

	/**
	 * cantidad de posiciones a buscar en la cadena antes de dar el resultado por no
	 * encontrado
	 */
	int nPosicionesBuscar;

	/**
	 * Elemento que hay que localizar
	 */
	T busqueda;

	/**
	 * Objeto en el que almacenar el resultado
	 */
	ResultadoBusqueda resultado;

	/**
	 * Define si debe terminar
	 */
	boolean terminar = false;

	/**
	 * Define si ha encontrado o no el elemento buscado
	 */
	boolean encontrado = false;

	/**
	 * 
	 * @param ascendente        Define la direccion de la busqueda si es ascendente
	 *                          o descendente
	 * @param posicionInicial   Referencia al nodo inicial
	 * @param nPosicionesBuscar Cantidad de posiciones a buscar contando el nodo
	 *                          inicial
	 * @param busqueda          Elemento a buscar
	 * @param resultado         Objeto en el que guardar la pos localizada
	 */
	public Buscador(boolean ascendente, Nodo<T> posicionInicial, int nPosicionesBuscar, T busqueda,
			ResultadoBusqueda resultado) {
		this.esAscendente = ascendente;
		this.actual = posicionInicial;
		this.nPosicionesBuscar = nPosicionesBuscar;
		this.busqueda = busqueda;
		this.resultado = resultado;
		this.encontrado = false;
	}

	@Override
	public void run() {
		int i = 0;

		// mientras no haya que terminar y el indice sea menor o igual que el numero de
		// posiciones a buscar
		// y no se haya encontrado el bucle seguira procesando
		while (!terminar && i <= this.nPosicionesBuscar && !this.encontrado) {
			// si el contenido del nodo actual es el elemento buscado se almacena el
			// resultado y se marca como encontrado
			if (this.actual.getContenido().equals(this.busqueda)) {
				this.resultado.setResultado(i);
				this.encontrado = true;
			} else {
				// en caso contrario se actualiza el nodo actual para la siguiente vuelta
				// teniendo en cuenta
				// si se esta en modo ascendente o descendente
				this.actual = (this.esAscendente) ? actual.getSiguiente() : actual.getAnterior();
				i++;
			}
		}
		// marcar el hilo como que ha terminado
		this.terminar = true;
	}

	/**
	 * Devuelve si ha encontrado
	 * 
	 * @return true si enctontrado false si no encontrado
	 */
	public boolean isEncontrado() {
		return encontrado;
	}

	/**
	 * Devuelve si esta marcado para terminar
	 * 
	 * @return True si ha terminado y false si no lo ha hecho
	 */
	public boolean isTerminar() {
		return terminar;
	}

	/**
	 * Define si debe parar o no
	 * @param parar El estado en el que se quiere poner. True para parar, False para no parar
	 */
	public void setParado(boolean parar) {
		this.terminar = parar;
	}

}
