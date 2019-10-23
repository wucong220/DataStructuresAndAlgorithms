package LRU;

import java.util.LinkedHashMap;

public class LRU<K,V> {
	private LinkedHashMap<K,V> map;
	private final int size;
	
	public LRU(final int size) {
		this.size = size;
		map = new LinkedHashMap<K,V>(16,0.75f,true){
			/**
			 * 
			 */
			private static final long serialVersionUID = 2693220996629331047L;

			@Override
			protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
				// TODO Auto-generated method stub
				return size()>size;
			}
		};
	}
	
	public V get(K key){
		return map.get(key);
	}
	
	public void put(K key,V value){
		map.put(key, value);
	}
	
	public static void main(String[] args) {
		LRU<String,String> lru = new LRU<String,String>(3);
		lru.put("1","2");
		lru.put("2","3");
		lru.put("3","4");
		System.out.println(lru.map);
		lru.put("4","5");
		System.out.println(lru.map);
		lru.get("3");
		System.out.println(lru.map);
		lru.map.forEach((k,v)->System.out.println(k+"="+v));
		//尾部永远是LRU的节点。。。。。。。，如果节点数超出size就会删除头部节点。。。。
	}
}
