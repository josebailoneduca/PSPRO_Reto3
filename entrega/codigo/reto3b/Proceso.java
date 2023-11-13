package reto3b;

import java.util.Objects;


/**
 * Simula in proceso. Contiene un identificador y la cantidad de ciclos restantes para terminarse
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Proceso {
	
	/**
	 * Id del proceso
	 */
	private int id;
	
	/**
	 * Cantidad de ciclos restantes para terminar
	 */
	private int ciclos;
	
	/**
	 * Constructor 
	 * 
	 * @param id Id del proceso
	 * @param ciclos Cantidad de cilos de CPU de vida
	 */
	public Proceso(int id, int ciclos) {
		this.id = id;
		this.ciclos = ciclos;
	}
	
	
	/**
	 * Devuelve los ciclos de vida restantes
	 * @return Los ciclos de vida
	 */
	public int getCiclos() {
		return ciclos;
	}
	
	/**
	 * Define los ciclos de vida restantes
	 * 
	 * @param ciclos Los ciclos de vida
	 */
	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
		if (this.ciclos<0) 
			this.ciclos=0;
	}
	
	/**
	 * Devuelve la id del proceso
	 * @return La id
	 */
	public int getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proceso other = (Proceso) obj;
		return id == other.id;
	}
	
	@Override
	public String toString() {
		return "Proceso [id=" + id + ", ciclos=" + ciclos + "]";
	}
 
	
	
 
}
