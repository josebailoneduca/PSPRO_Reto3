package reto3a;

/**
 * Su metodo run se encarga de buscar un elemento en una cadena de nodos yendo en la 
 * direcccion indicada y almacenando en su variable resultado la cantidad de saltos que ha 
 * tenido que dar hasta encontrar el elemento buscado. Si no lo encuentra esa variable permanece en -1
 * 
 * @author Jose Javier Bailon Ortiz
 * 
 * @param <T> El tipo de alemento a buscar
 * 
 */
public class Buscador<T extends Comparable<T>> extends Thread {

	/**
	 * direccion de la busqueda en la cadena de nodos
	 */
	private boolean esAscendente;

	/**
	 * el nodo actual
	 */
	private Nodo<T> actual;

	/**
	 * cantidad de posiciones a buscar en la cadena antes de dar el resultado por no encontrado
	 */
	private int nPosicionesBuscar;

	/**
	 * Elemento que hay que localizar
	 */
	private T busqueda;

	/**
	 * Posicion en la que se ha encontrado el elemento buscado. -1 significa no encontrado
	 */
	private int resultado =-1;
	
	/**
	 * Define si debe terminar. Puede ser establecido desde fuera
	 */
	private boolean terminado = false;
 
	
	
	/**
	 * Constructor
	 * 
	 * @param ascendente        Define la direccion de la busqueda si es ascendente
	 *                          o descendente
	 * @param posicionInicial   Referencia al nodo inicial
	 * @param nPosicionesBuscar Cantidad de posiciones en las que buscar contando el nodo
	 *                          inicial
	 * @param busqueda          Elemento a buscar
	 */
	public Buscador(boolean ascendente, Nodo<T> posicionInicial, int nPosicionesBuscar, T busqueda) {
		this.esAscendente = ascendente;
		this.actual = posicionInicial;
		this.nPosicionesBuscar = nPosicionesBuscar;
		this.busqueda = busqueda;
		this.resultado = -1;
	}

	@Override
	public void run() {
		int i = 0;

		// Bucle mientras no haya que terminar y el indice sea menor o igual que el numero de
		// posiciones a buscar
		while (!terminado && i <= this.nPosicionesBuscar) {
			// si el contenido del nodo actual es el elemento buscado se almacena el
			// resultado, se marca como como terminado
			if (this.actual.getContenido().equals(this.busqueda)) {
				this.resultado=i;
				this.terminado=true;
			//si es hilo ascendente y se ha llegado a un elemento que es mayor que el elemento buscado	termina la busqueda
			} else if (esAscendente&&this.actual.getContenido().compareTo(this.busqueda)>0){
				this.terminado=true;
			//si es hilo ascendente y se ha llegado a un elemento que es menor que el elemento buscado	termina la busqueda
			}else if (!esAscendente&&this.actual.getContenido().compareTo(this.busqueda)<0){
				this.terminado=true;
			}
			else {
				// en caso contrario se actualiza el nodo actual para la siguiente vuelta
				// teniendo en cuenta
				// si se esta en modo ascendente o descendente
				this.actual = (this.esAscendente) ? actual.getSiguiente() : actual.getAnterior();
				i++;
			}
		}
		//asegurar que esta marcado como terminado por si el bucle termina por llegar al final 
		//sin encontrar resultado
		this.terminado=true;
	}

	/**
	 * Devuelve si ha encontrado. Lo hace mirando si el resultado es mayor de -1 o no
	 * 
	 * @return True si enctontrado y False si no encontrado
	 */
	public boolean isEncontrado() {
		return this.resultado>-1;
	}

	/**
	 * Devuelve si esta marcado para terminar
	 * 
	 * @return True si ha terminado y false si no lo ha hecho
	 */
	public boolean isTerminado() {
		return terminado;
	}

	/**
	 * Define si debe parar o no
	 * @param parar El estado en el que se quiere poner. True para parar, False para no parar
	 */
	public void setTerminado(boolean parar) {
		this.terminado = parar;
	}

	/**
	 * Devuelve la posicion en la que se ha encontrado el elemento
	 * @return El resultado (si es descendente sera la posicion desde el final). -1 si no se ha encontrado
	 */
	public int getResultado() {
		return resultado;
	}
	
}
