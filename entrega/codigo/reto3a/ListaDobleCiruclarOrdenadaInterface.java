package reto3a;

/**
 * Interface para la lista doblemente enlazada circular y ordenada
 * 
 * @author Jose Javier Bailon Ortiz
 * 
 * @param <T> Tipo de elemento que almacenara la lista
 */
public interface ListaDobleCiruclarOrdenadaInterface<T extends Comparable<T>> {
 
	
	/**
	 * Agrega un elemento ordenado
	 * @param elemento Elemento a agregar a la lista
	 */
	public void add(T elemento);
 
	
	/**
	 * Eliminar posicion indicada
	 * @param posicion Posicion a eliminar
	 * @return El elemento que contenia el nodo eliminado 
	 * @throws IndexOutOfBoundsException Si no existe la posicion
	 */
	public T remove(int posicion) throws IndexOutOfBoundsException;
	
	/**
	 * Eliminar una ocurrencia del elemento
	 * @param elemento Elemento a eliminar
	 * @return La posicion borrada o -1 si no existia
	 */
	public int remove(T elemento);
	
	/**
	 * Devuelve el indice del elemento en la lista. -1 si no existe
	 * @param elemento Busqueda
	 * @return Indice si lo encuentra. -1 si no lo encuentra
	 */
	public int contains (T elemento);

	/**
	 * Devuelve el elemento en el indice.
	 * @param indice del elemento de la lista
	 * @return el elemento
	 * @throws IndexOutOfBoundsException Si no existe la posicion
	 */
	public T getAt (int indice)throws IndexOutOfBoundsException;

	/**
	 * Devuelve un string formateado con el contenido de la lista
	 * @return La lista formateada como string
	 */
	public String listar();
	
	/**
	 * Devuelve el primero
	 * @return El primer elemento o null si está vacia
	 */
	public T getFirst();
	/**
	 * Devuelve el ultimo
	 * @return El ultimo elemento o null si está vacia
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
	 * @throws IllegalStateException si aun no se ha ejecutado isFirst ni isLast
	 */
	public T getNext() throws IllegalStateException;

	/**
	 * Devuelve el elemento anterior
	 * @return El elemento anterior
	 * @throws IllegalStateException si aun no se ha ejecutado isFirst ni isLast
	 */
	public T getPrevious() throws IllegalStateException;

	
	/**
	 * Vacia la lista
	 */
	public void delete();
	
	/**
	 * Devuelve si esta vacia
	 * @return True si esta vacia, False si contiene algun elemento
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve la cantidad de elementos
	 * @return La cantidad
	 */
	public int size();
	
	
}
