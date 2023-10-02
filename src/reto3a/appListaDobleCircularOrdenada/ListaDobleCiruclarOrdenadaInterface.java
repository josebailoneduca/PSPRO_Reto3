package reto3a.appListaDobleCircularOrdenada;

public interface ListaDobleCiruclarOrdenadaInterface<T extends Comparable<T>> {
 
	
	/**
	 * Agrega un elemento ordenado
	 * @param posicion Posicion a eliminar
	 */
	public void add(T elemento);
 
	
	/**
	 * Eliminar posicion
	 * @param posicion Posicion a eliminar
	 */
	public void remove(int posicion);
	
	/**
	 * Devuelve si la lista contiene el elemento
	 * @param elemento
	 * @return True si lo contiene, False si no lo contiene. Usa la funcion Equals
	 */
	public int contains (T elemento);

	/**
	 * Devuelve el elemento en el indice
	 * @param indice el indice dentro de la lista
	 * @return el lemento
	 */
	public T getAt (int indice);

	/**
	 * Devuelve un string formateado con el contenido de la lista
	 * @return La lista formateada como string
	 */
	public String listar();
	
	/**
	 * Devuelve el primero
	 * @return El primer elemento
	 */
	public T getFirst();
	/**
	 * Devuelve el ultimo
	 * @return El ultimo elemento
	 */
	public T getLast();
	
	
	/**
	 * Devuelve si esta en la primera posicion
	 * @return true si esta en la primera posicion false si no lo esta
	 */
	public boolean isFirst();
	
	/**
	 * Devuelve si esta en la ultima posicion
	 * @return true si esta en la ultima posicion false si no lo esta
	 */
	
	public boolean isLast(); 
	
	/**
	 * Devuelve el siguiente elemento
	 * @return El siguiente elemento
	 */
	public T getNext();

	/**
	 * Devuelve el elemento anterior
	 * @return El elemento anterior
	 */
	public T getPrevious();

	
	/**
	 * Borra la lista
	 */
	public void delete();
	
	/**
	 * Devuelve si esta vacia
	 * @return True si esta vacia, False si contiene algun elemento
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve la cantidad de elementos
	 * @return
	 */
	public int size();
	
	
}
