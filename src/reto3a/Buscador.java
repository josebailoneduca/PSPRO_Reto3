package reto3a;

public class Buscador<T extends Comparable<T>> extends Thread{
	
	boolean esAscendente;
	Nodo<T> actual;
	int nPosicionesBuscar;
	T busqueda;
	boolean terminar = false;
	boolean encontrado = false;
	ResultadoBusqueda resultado;
	/**
	 * 
	 * @param ascendente Define la direccion de la busqueda si es ascendente o descendente
	 * @param posicionInicial Referencia al nodo inicial
	 * @param nPosicionesBuscar Cantidad de posiciones a buscar contando el nodo inicial
	 * @param busqueda Elemento a buscar
	 * @param resultado Objeto en el que guardar la pos localizada
	 */
	public Buscador(boolean ascendente, Nodo<T> posicionInicial, int nPosicionesBuscar, T busqueda, ResultadoBusqueda resultado) {
		this.esAscendente = ascendente;
		this.actual = posicionInicial;
		this.nPosicionesBuscar = nPosicionesBuscar;
		this.busqueda = busqueda;
		this.resultado = resultado;
		this.encontrado=false;
	}
	
	
	@Override
	public void run() {
		int i  =0;
		while (!terminar && i<=this.nPosicionesBuscar&&!this.encontrado) {
			if (this.actual.getContenido().equals(this.busqueda)) {
				this.resultado.setResultado(i);
				this.encontrado=true;
			}else {
				this.actual = (this.esAscendente)?actual.getSiguiente():actual.getAnterior();
				i++;
			}
		}
		this.terminar=true;
	}


	
	
	public boolean isEncontrado() {
		return encontrado;
	}


	public boolean isTerminar() {
		return terminar;
	}


	public void setParado(boolean parar) {
		this.terminar = parar;
	}
	
}
