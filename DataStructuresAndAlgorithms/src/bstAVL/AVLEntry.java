package bstAVL;

import java.util.Map;

public class AVLEntry<K,V> implements Map.Entry<K,V>{
	public K key;
	public V value;
	public AVLEntry<K, V> left;
	public AVLEntry<K, V> right;
	
	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public V setValue(V value) {
		// TODO Auto-generated method stub
		return this.value = value;
	}

	public AVLEntry(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AVLEntry [key=" + key + ", value=" + value + "]";
	}
		
}
