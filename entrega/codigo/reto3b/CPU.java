package reto3b;

import java.util.Random;

/**
 * Simula una CPU. Procesa el gasto de ciclos de un proceso
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class CPU {
	
	/**
	 * Random para utilidad
	 */
	private Random r = new Random();
	
	/**
	 * Minimo de tiempo a consumir
	 */
	private int min = 50;
	
	/**
	 * Maximo de tiempo a consumir
	 */
	private int max = 200;
	
	/**
	 * Constructor 
	 * @param min Minimo tiempo a consumir
	 * @param max Maximo tiempo a consumir
	 */
	public CPU(int min, int max) {
		this.min=min;
		this.max=max;
	}
	
	/**
	 * Simula el gasto de ciclos de un poroceso
	 * 
	 * @param p Proceso a procesar
	 */
	public void procesar(Proceso p) {
		int ciclosAGastar=r.nextInt(min, max+1);
		p.setCiclos(p.getCiclos()-ciclosAGastar);
		System.out.println("Gastados "+ciclosAGastar+" ciclos del "+p);
	}
}
