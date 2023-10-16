package reto3b;

import reto3b.listadoblecircular.ListaDobleCircular;


/**
 * Se encarga de simular la administracion de entrada de procesos en CPU según el algoritmo
 * Round-robin
 */
public class Scheduler {
	/**
	 * Almacena los procesos
	 */
	private ListaDobleCircular<Proceso> listaProcesos=new ListaDobleCircular<Proceso>();
	
	/**
	 * Almacena los procesos que ha eliminado. Sirve para determinar cuando pedir mas procesos
	 */
	private int eliminados=0;
	
	/**
	 * Refernica al dispacher para ordenarle crear mas procesos
	 */
	private Dispatcher dispatcher;
	
	/**
	 * CPU que consumira los procesos
	 */
	private CPU cpu;

	
	/**
	 * Constructor
	 * 
	 * @param dispatcher Dispatcher creador de procesos
	 * @param cpu CPU consumidora de procesos
	 */
	public Scheduler(Dispatcher dispatcher, CPU cpu) {
		super();
		this.dispatcher = dispatcher;
		this.cpu = cpu;
	}


	/**
	 * Agrega un proceso a la lista
	 * @param proceso El proceso a agregar
	 */
	public void addProceso(Proceso proceso) {
		this.listaProcesos.addBeforeCurrent(proceso);
		System.out.println("Agregado: "+proceso+ " TOTAL:"+this.listaProcesos.size());
	}
 

	/**
	 * Inicia el funcionamiento del scheduler
	 */
	public void iniciar() {
		//mientras haya procesos
		while(this.listaProcesos.size()>0) {
			
			//recoger siguiente proceso en la lista
			Proceso procesoAEjecutar=listaProcesos.getNext();
			
			//si es nulo es que no se ha inicializado entonces coger el primero
			if (procesoAEjecutar==null)
				procesoAEjecutar=listaProcesos.getFirst();
			
			//procesar el proceso
			this.cpu.procesar(procesoAEjecutar);
			
			//comprobar si hay que eliminar
			if (procesoAEjecutar.getCiclos()<1) {
				this.listaProcesos.remove(procesoAEjecutar);
				this.eliminados++;
				System.out.println("El proceso "+procesoAEjecutar+" ha terminado");
				System.out.println("Quedan "+this.listaProcesos.size()+" por terminar");
			}
			
			//comprobar si hay que generar
			if (this.eliminados==100) {
				this.dispatcher.generarProceso(this);
				this.eliminados=0;
			}
		}
	}
	
}
