package sortAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class HeapSort<T> {
	private Comparator<T> comp;

	public HeapSort() {
		super();
	}

	public HeapSort(Comparator<T> comp) {
		super();
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public int compare(T a, T b) {
		if (comp != null) {
			return comp.compare(a, b);
		} else {
			Comparable<T> c = (Comparable<T>) a;
			return c.compareTo(b);
		}
	}

	private void swap(T[] array, int a, int b) {
		T t = array[a];
		array[a] = array[b];
		array[b] = t;
	}

	private void shiftDown(T[] array, int p, int heapSize) {
		int half =( heapSize >>> 1) - 1; // 最后一个非叶子节点下标（2n+2）/2或(2n+3)/2 ==2n+1,所以 heapsize/2-1;
		int k = p;
		T value = array[k];
		while (k <= half) {
			int child = (k << 1) + 1;
			T c = array[child];
			int right = (k << 1)+ 2;
			if (right < heapSize && compare(array[right], c) > 0) { //得到最大孩
				c = array[(child = right)];
			}
			if (compare(value, c) >= 0)  //若该节点的值大于等于最小孩的值，停止递归
				break;
			array[k] = c;
			k = child;
		}
		array[k] = value;
	}
	
	//后序递归建堆，当子树是小顶堆时，将根节点shiftdown。
	private void heapifyPostOrder(T[] array,int p,int heapSize){
		if(p>=heapSize)return;
		else{	
			heapifyPostOrder(array,(p<<1)+1,heapSize);
			heapifyPostOrder(array,(p<<1)+2,heapSize);
			shiftDown(array, p, heapSize);
		}
	}
	public void heapSort(T[] array){
		heapifyPostOrder(array, 0, array.length);
		swap(array, array.length-1, 0);
		for(int i=array.length-1;i>=1;i--){
			shiftDown(array, 0, i);
			swap(array, i-1, 0);
		}
	}
	
	
	
	private final static int MA1 = 1000;
	public static void main(String[] args) {
		Integer[] array = new Integer[MA1];
		Random random = new Random(123456);
		for(int i=0;i<MA1;i++){
			array[i] = random.nextInt(MA1);
		}
		System.out.println(Arrays.toString(array));
		HeapSort<Integer> sorter = new HeapSort<Integer>();
		sorter.heapSort(array);
		Arrays.sort(array);
		System.out.println(Arrays.toString(array));
		for(int i=1;i<MA1;i++){
			if(array[i]<array[i-1]){System.out.println("error!!!!");};
		}
		
		List<Integer> l = new ArrayList<>();
	}
	
}
