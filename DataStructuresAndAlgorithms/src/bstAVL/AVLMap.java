package bstAVL;

import java.util.Comparator;
import java.util.Iterator;

public class AVLMap<K, V> implements Iterable<AVLEntry<K, V>>{
	private int size;
	private AVLEntry<K,V> root;
	private Comparator<K> comp;
	@SuppressWarnings({ "unused", "unchecked" })
	private int compare(K a,K b){
		if(comp!=null){
			return comp.compare(a, b);
		}
		else{
			Comparable<K> o = (Comparable<K>) a;
			return o.compareTo(b);
		}
	}
	public AVLMap(Comparator<K> comp) {
		super();
		this.comp = comp;
	}
	public AVLMap() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size==0;
	}
	
	public V put(K key,V value){
		if(root==null){
			root = new AVLEntry<K, V>(key, value);
			size++;
		}else{
			AVLEntry<K, V> p = root;
			while(p!=null){
				int compareResult = compare(key,p.key);
				if(compareResult ==0){
					p.setValue(value);
					break;
				}
				else if(compareResult<0){
					if(p.left==null){
						p.left = new AVLEntry<K,V>(key, value);
						size++;
						break;
					}else{
						p=p.left;
					}
				}else{
					if(p.right==null){
						p.right = new AVLEntry<K,V>(key, value);
						size++;
						break;
					}else{
						p=p.right;
					}
				}
			}
		}
		return value;
	}
	@Override
	public Iterator<AVLEntry<K, V>> iterator() {
		// TODO Auto-generated method stub
		return new AVLIterator<>(root);
	}
	
	public AVLEntry<K, V> getEntry(K key){
		AVLEntry<K, V> p = root;
		while(p!=null){
			int compareResult = compare(key, p.key);
			if(compareResult < 0){
				p=p.left;
			}
			else if(compareResult>0){
				p=p.right;
			}
			else{
				return p;
			}
		}
		return null;
	}
	
	public boolean containsKey(K key){
		return getEntry(key)!=null;
	}
	
	public boolean containsValue(V value){
		Iterator<AVLEntry<K, V>> it = iterator();
		while(it.hasNext()){
			if(it.next().getValue().equals(value))return true;
		}
		return false;
	}
	
	public V get(K key){
		AVLEntry<K, V> p = getEntry(key);
		return p==null?null:p.getValue();
	}
}
