package reto3b;

import java.util.Random;

/**
 * Genera procesos que manda a un scheduler
 */
public class Dispatcher {
	/**
	 * Almacena la id a usar para el siguiente proceso
	 */
	int id=0;
	
	/**
	 * Random utilidad
	 */
	Random r = new Random();
	
	/**
	 * Minimo de tiempo de vida en ciclos de CPU que tendran los procesos creados
	 */
	int min=1000;
	
	/**
	 * Maximo de tiempo de vida en ciclos de CPU que tendran los procesos creados
	 */
	int max=10000;
 
	
	
	/**
	 * Genera un proceso y lo manda al scheduler suministrado
	 * 
	 * @param scheduler Scheduler al que introducir el proceso creado
	 */
	public void generarProceso(Scheduler scheduler) {
		scheduler.addProceso(new Proceso(id++, r.nextInt(min,max+1)));
	}
	
	
}
