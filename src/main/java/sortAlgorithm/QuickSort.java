package sortAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

public class QuickSort<T> {
	private Comparator<T> comp = null;
	
	public QuickSort(){
		super();
	}
	
	public QuickSort(Comparator<T> comparator){
		this.comp = comparator;
	}
	
	@SuppressWarnings("unchecked")
	private int compare(T a,T b){
		if(comp!=null){
			return comp.compare(a, b);
		}
		else{
			Comparable<T> c = (Comparable<T>) a;
			return c.compareTo(b);
		}
	}
	
	
	public void quickSort(T[] array,int low,int high){
		if(low>=high)
			return;
		int i=low;
		int j=high;
		T key = array[i];
		while(i<j){
			while(i<j&&compare(array[j],array[i])>=0)j--;
			if(i<j)array[i]=array[j];
			while(i<j&&compare(array[i],array[j])<=0)i++;
			if(i<j)array[j]=array[i];
		}
		array[i] = key;
		quickSort(array,low,i-1);
		quickSort(array,i+1,high);
	}
	
	public static void main(String[] args) {
		QuickSort<Integer> qs =  new QuickSort<Integer>();
		Integer[] array = {1,3,5,7,9,2,4,6,8,10};
		System.out.println(Arrays.toString(array));
		qs.quickSort(array, 0, array.length-1);
		System.out.println(Arrays.toString(array));
	}
}
