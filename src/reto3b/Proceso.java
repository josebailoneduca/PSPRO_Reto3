package reto3b;

import java.util.Objects;

public class Proceso {
	private int id;
	private int ciclos;
	public Proceso(int id, int ciclos) {
		super();
		this.id = id;
		this.ciclos = ciclos;
	}
	public int getCiclos() {
		return ciclos;
	}
	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
		if (this.ciclos<0) 
			this.ciclos=0;
	}
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
