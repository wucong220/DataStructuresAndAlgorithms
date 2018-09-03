package sortAlgorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class BubbleSort<T> {
	Comparator<T> comp;

	public BubbleSort(Comparator<T> comparator) {
		super();
		this.comp = comparator;
	}
	
	public BubbleSort(){	
	}
	
	@SuppressWarnings("unchecked")
	private int compare(T a,T b){
		if(comp!=null){
			return comp.compare(a, b);
		}
		else{
			return ((Comparable<T>)a).compareTo(b);
		}
		
	}
	
	public void bubbleSort(T[] Array){
		for(int i=0;i<Array.length;i++){
			for(int j=i;j<Array.length-i-1;j++){
				if(compare(Array[j],Array[j+1])>0){
					T temp = Array[j];
					Array[j] = Array[j+1];
					Array[j+1] = temp;
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		Integer[] randoms = new Integer[10];
		Random random = new Random();
		for(int i=0;i<randoms.length;i++){
			randoms[i] = random.nextInt();
		}
		System.out.println(Arrays.toString(randoms));
		new BubbleSort<>().bubbleSort(randoms);
		System.out.println(Arrays.toString(randoms));
		
	}
	
}
