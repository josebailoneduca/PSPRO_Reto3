package reto3b;

import java.util.Random;

public class Dispatcher {
	int id=0;
	Random r = new Random();
	int min=1000;
	int max=10000;
 
	
	public void generarProceso(Scheduler scheduler) {
		scheduler.addProceso(new Proceso(id++, r.nextInt(min,max+1)));
	}
	
	
}
