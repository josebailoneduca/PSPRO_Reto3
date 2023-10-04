package reto3a;

public class Buscador<T extends Comparable<T>> extends Thread{
	
	boolean esAscendente;
	Nodo<T> actual;
	int nPosicionesBuscar;
	T busqueda;
	boolean parado = false;
	ResultadoBusqueda sincro;
	/**
	 * 
	 * @param ascendente Define si es ascendente o descendente
	 * @param posicionInicial Referencia al nodo inicial
	 * @param nPosicionesBuscar Cantidad de posiciones a buscar
	 * @param busqueda Elemento a buscar
	 * @param posicionLocalizada Objeto en el que guardar la pos localizada
	 */
	public Buscador(boolean ascendente, Nodo<T> posicionInicial, int nPosicionesBuscar, T busqueda, ResultadoBusqueda sincro) {
		this.esAscendente = ascendente;
		this.actual = posicionInicial;
		this.nPosicionesBuscar = nPosicionesBuscar;
		this.busqueda = busqueda;
		this.sincro = sincro;
	}
	
	
	@Override
	public void run() {
		int i  =0;
		while (!parado && i<this.nPosicionesBuscar) {
			if (this.actual.getContenido().equals(this.busqueda)) {
				this.sincro.setResultado(i);
				this.parado=true;
			}else {
				this.actual = (this.esAscendente)?actual.getSiguiente():actual.getAnterior();
				i++;
			}
		}
		
	}


	public boolean isParado() {
		return parado;
	}


	public void setParado(boolean parar) {
		this.parado = parar;
	}
	
}
