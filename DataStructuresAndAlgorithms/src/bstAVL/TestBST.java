package bstAVL;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TestBST {
	private Random random = new Random();	
	private final int MAX1 = 16;
	@Test
	public void testPutAndItr(){
		AVLMap<Integer,String> map = new AVLMap<>();
		for(int i=0;i<MAX1;i++){
			map.put(random.nextInt(MAX1), random.nextInt(MAX1)+"");
		}
		Iterator<AVLEntry<Integer, String>> it = map.iterator();
		while(it.hasNext()){
			System.out.print(it.next().key+" ");
		}
		System.out.println();
	}
	private final int  MAX2 = 65535;
	@Test
	public void testPutAndItrWithJDK(){
		AVLMap<Integer,String> map1 = new AVLMap<>();
		TreeMap<Integer,String> map2 = new TreeMap<>();
		for(int i=0;i<MAX2;i++){
			Integer key = random.nextInt(MAX2);
			String value = random.nextInt(MAX2)+"";
			map1.put(key, value);
			map2.put(key, value);
		}
		System.out.println(map1.size());
		Assert.assertTrue(map1.size()==map2.size());
		
		
		Iterator<AVLEntry<Integer, String>> it1 = map1.iterator();
		Iterator<Entry<Integer, String>> it2 = map2.entrySet().iterator();
		while(it1.hasNext()&&it2.hasNext()){
			Assert.assertTrue(it1.next().getKey().equals(it2.next().getKey()));
		}
		Assert.assertTrue(!it1.hasNext()&&!it2.hasNext());
	}
	@Test
	public void testQuery(){
		AVLMap<Integer, String> map = new AVLMap<>();
		map.put(1, "a");
		map.put(2, "b");
		map.put(3, "c");
		map.put(4, "d");
		map.put(5, "e");
		map.put(6, "f");
		map.put(7, "g");
		map.put(8, "h");
		map.put(2, "z");
		Assert.assertEquals(map.get(1),("a"));
		Assert.assertEquals(map.get(2), "z");
		Assert.assertTrue(map.containsKey(2));
		Assert.assertTrue(map.containsValue("g"));
	}
	@Test
	public void testQueryWithJDK(){
		AVLMap<Integer,String> map1 = new AVLMap<>();
		TreeMap<Integer,String> map2 = new TreeMap<>();
		for(int i=0;i<MAX2;i++){
			Integer key = random.nextInt(MAX2);
			String value = random.nextInt(MAX2)+"";
			map1.put(key, value);
			map2.put(key, value);
		}
		for(int i=0;i<MAX2;i++){
			int key = random.nextInt(MAX2);
			Assert.assertEquals(map1.get(key),map2.get(key));
			//if(map1.get(key)==null)Assert.assertTrue(map2.get(key)==null);
		}
		
		for(int i=0;i<255;i++){
			String value = random.nextInt(MAX2)+"";
			Assert.assertTrue(map1.containsValue(value)==map2.containsValue(value));
		}
	}
}
