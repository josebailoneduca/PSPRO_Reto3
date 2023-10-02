package reto3b;

import java.util.Random;

public class CPU {
	private Random r = new Random();
	private int min = 50;
	private int max = 200;
	
	
	public void procesar(Proceso p) {
		int ciclosAGastar=r.nextInt(min, max+1);
		p.setCiclos(p.getCiclos()-ciclosAGastar);
		//System.out.println("Gastados "+ciclosAGastar+" ciclos de "+p);
	}
}
