package reto3b;

import reto3b.listadoblecircular.ListaDobleCircular;

public class Scheduler {
	private ListaDobleCircular<Proceso> listaProcesos=new ListaDobleCircular<Proceso>();
	private int eliminados=0;
	private Dispatcher dispatcher;
	private CPU cpu;

	
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
		System.out.println("Agregado: "+proceso);
	}
 

	public void iniciar() {
		listaProcesos.getLast();
		//mientras haya procesos
		while(this.listaProcesos.size()>0) {
			//ejecutar proceso
			Proceso procesoAEjecutar=listaProcesos.getNext();
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
