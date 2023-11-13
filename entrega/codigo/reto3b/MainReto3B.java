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

 * @author Jose Javier Bailon Ortiz
 */
public class MainReto3B {

	public static void main(String[] args) {
		
		
		//PARAMETROS DE CONFIGURACION
		//consumos realizados por la CPU
		int minConsumo=50;
		int maxConsumo=200;
		
		//Vida de los procesos
		int minVida=1_000;
		int maxVida=10_000;
		
		//Numero de procesos iniciales
		int nProcesosIniciales=1000;
		
		//Tasa de renovacion de procesos. Cuando hayan terminado estos procesos Scheduler pedira un nuevo proceso.
		int tasaRenovacion=100;
		
		
		
		
		//CPU  a usar
		CPU cpu = new CPU(minConsumo,maxConsumo);
		//Dispatcher
		Dispatcher dispatcher = new Dispatcher(minVida,maxVida);
		//Scheduler
		Scheduler scheduler = new Scheduler(dispatcher, cpu, tasaRenovacion);
		
		//generacion de los procesos iniciales
		for (int i = 0; i < nProcesosIniciales; i++) {
			dispatcher.generarProceso(scheduler);
		}
		
		//Iniciar el funcionamiento del scheduler
		scheduler.iniciar();
		
	}

}
