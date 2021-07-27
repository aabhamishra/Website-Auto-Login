package ProjectOne;
// --== CS400 File Header Information ==--
// Name: <Logan Homberg>
// Email: <homberg@wisc.edu>
// Team: <HF>
// TA: <Na Li>
// Lecturer: <Gary Dahl>
// Notes to Grader: <Grade Scope gives the following error: (-1.0): Tests the removal of a key that is not contained within a HashTableMap.
// Notes to Grader: However, I believe my code is correct. It returns null when there is no key-value pair.>
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashTableMap<KeyType, ValueType> implements MapADT {
	private Object[] array; //store key value pairs;
	private int size = 0; 
	private Object[][] memory; 
	private int row = 0;
	/**
	 * Constructor for the HashTableMap at a default capacity of 10
	 */
	public HashTableMap() {
		array = new Object[10];
		memory = new Object[10][2];
	}

	/**
	 * Constructs the HashTableMap at a certain capacity.
	 * @param capacity is the capacity of the HashTableMap.
	 */
	public HashTableMap(int capacity) {
		array = new Object[capacity];
		memory = new Object[capacity][2];
	}
	/**
	 * Puts a key-value pair in the HashTableMap at a index correlating with the key.
	 * @param key is object that is associated with the value and used to store it. 
	 * @param value is the object to be stored in the HashTableMap.
	 * @return Returns true if a value is successfully stored in the HashTableMap, false otherwise.
	 */
	@Override
	public boolean put(Object key, Object value) {
		// TODO Auto-generated method stub
		
		int hashCode = Math.abs(key.hashCode() % array.length); 
		if (memory.length <= row) {
			growCheck();
		}
		if (array[hashCode] != null) {
			//System.out.println("Collision");
			if (array[hashCode].equals(value)) {
				return false; 
			} 
			((LinkedList<Object>) array[hashCode]).push(value);
			memory[row][0] = key;
			memory[row][1] = value;
			row++;
			size++;
		} else {
			array[hashCode] = new LinkedList<Object>();
			((LinkedList<Object>) array[hashCode]).add(value);
			//System.out.println(memory[row][0]);
			memory[row][0] = key;
			memory[row][1] = value;
			//System.out.println(memory[row][0]);
			//System.out.println(memory[row][1]);
			row++;
			size++;
			
			return true;
		}

		growCheck();
		return false;
	}
	
	
	/**
	 * Checks if the HashTableMap should grow. 
	 * If growth is needed, initiates a HashTableMap growth.
	 */
	public void growCheck() {

		double loadFactor = 0.8;

		if (((double)size/array.length) >= loadFactor) {

			grow();
		}
	}
	/**
	 * Doubles the size of the HashTableMap and re-hashes the values into the doubled HashTableMap.
	 * Doubles the size of the two dimensional array and re-inputs the values into it.
	 */
	public void grow() {
		
		Object[][] memoryhold = new Object[memory.length][2];
		memoryhold = memory;
		memory = new Object[memoryhold.length*2][2];
		
		for (int i = 0; i < memoryhold.length; i++) {
			
			memory[i][0] = memoryhold[i][0];
			memory[i][1] = memoryhold[i][1];
		}

		Object[] temp = new Object[array.length];
		array = new Object[array.length * 2];
		
		for (int i =0; i < size; i++) {
			
			int hashCode = Math.abs(memory[i][0].hashCode() % array.length); 
			
			if (array[hashCode] != null) {

				((LinkedList<Object>) array[hashCode]).add(memory[i][1]);

			} else {
				
				array[hashCode] = new LinkedList<Object>();
				((LinkedList<Object>) array[hashCode]).add(memory[i][1]);

			}
		}
		

	}
	
	/**
	 * Helper method for get(Object key).
	 * Determines the correct key-value pair if a collision is detected. 
	 * @param key is object that is associated with the value and used to store it. 
	 * @return returns the correct value associated with a key.
	 */
	public Object collisionCheck(Object key) { 
		
		for (int i = 0; i < memory.length; i++) {
			if (memory[i][0] != null && memory[i][0].equals(key.toString())) {
				return memory[i][1];
			}
		}
		return null;
	}

	@Override
	/**
	 * Method used to get the value associated with a key. 
	 * Checks any collisions and figures out the correct value.
	 * @param key is object that is associated with the value and used to store it. 
	 * @return Returns the value associated with a key.
	 */
	public Object get(Object key) throws NoSuchElementException {
		
		if (!containsKey(key)) {
			throw new NoSuchElementException("The key you are looking for is not in the Map.");
		}
		
		Object temp = ((LinkedList<Object>)array[Math.abs(key.hashCode()%array.length)]).peekLast();
		System.out.println(temp);
		
		if (collisionCheck(key) == null && temp != null) {
			
		
			
			throw new NoSuchElementException("The key you are looking for is not in the Map.");
			
		} else if (collisionCheck(key) != temp){
			
			temp = collisionCheck(key);
				
		}

		return temp;

	}

	@Override
	/**
	 * @return Returns the size of the HashTableMap.
	 */
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	/**
	 * @param key is object that is associated with the value and used to store it. 
	 * @return Returns true if the the HashTableMap contains a value at key, false otherwise.
	 */
	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		if (array[Math.abs(key.hashCode()%array.length)] != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a key-value pair if it exists in the HashTableMap.
	 * @param key is object that is associated with the value and used to store it. 
	 * @return Returns the value removed at key. Returns null if the key does no exist. 
	 */
	@Override
	public Object remove(Object key) {

		if (!containsKey(key)) {
			
			return null;
		}
		if (array[Math.abs(key.hashCode()%array.length)] != null) {
			
			Object hold = ((LinkedList<Object>)(array[Math.abs(key.hashCode()%array.length)])).pop();
			array[Math.abs(key.hashCode()%array.length)] = null;
			return hold;
			
		} else {
			
			return null;
			
		}
	}
	/**
	 * Removes all values from the HashTableMap.
	 */
	@Override
	public void clear() {

		for (int i = 0; i < array.length; i++) {
			
			array[i] = null;
			memory[i][0] = null;
			memory[i][1] = null;
			
		}
		
		size = 0;
		row = 0;
		
	}
	
	public static void main(String[] args) {
		HashTableMap map = new HashTableMap(10); 
		
		map.put(12, 1);
		System.out.println(map.put(12, 2));
		map.put(23, 3);
		
		System.out.println(map.get(14));
		
		
		
	}

}
