package reto3b;

import java.util.Random;

/**
 * Simula una CPU. Procesa el gasto de ciclos de un proceso
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
	 * Simula el gasto de ciclos de un poroceso
	 * 
	 * @param p Proceso a procesar
	 */
	public void procesar(Proceso p) {
		int ciclosAGastar=r.nextInt(min, max+1);
		p.setCiclos(p.getCiclos()-ciclosAGastar);
		System.out.println("Gastados "+ciclosAGastar+" ciclos de "+p);
	}
}
