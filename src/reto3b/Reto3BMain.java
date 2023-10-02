package reto3b;

/**
 * Punto de entrada para el Reto 3 apartado B
 * 
 * RETO 3 B:
 * 
 * Simular el consumo de procesos round robin representado cada proceso con un numero entero que simula su consumo total de ciclos
 * de CPU. La cantidad de ciclos de CPU sera del rango{1000,10000} y cada vez que pasan por CPU consume ciclos de CPU en un rango de {50,200}
 * Por cada 100 nodos que terminen de procesarse se agrega 1 nuevo nodo
 * 	·Dispatcher:agrega procesos
 * 	·Scheduler: controla qué entra en cpu y qué proceso muere por estar terminado
 * 	·CPU: consume tiempo del proceso
 * 
 * Al quedarse la lista vacia termina el programa

 * 
 */
public class Reto3BMain {

	public static void main(String[] args) {
		//CPU  a usar
		CPU cpu = new CPU();
		//Dispatcher
		Dispatcher dispatcher = new Dispatcher();
		//Scheduler
		Scheduler scheduler = new Scheduler(dispatcher, cpu);
		//generacion de los procesos iniciales
		for (int i = 0; i < 500; i++) {
			dispatcher.generarProceso(scheduler);
		}
		//Iniciar el funcionamiento del scheduler
		scheduler.iniciar();
		
	}

}
