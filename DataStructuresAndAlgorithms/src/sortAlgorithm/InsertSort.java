package sortAlgorithm;

import java.util.Arrays;
import java.util.Random;

public class InsertSort {
	
	
	static public void insertSort(int[] array){
		for(int i=0;i<array.length;i++){
			for(int j = i;j>0;j--){
				if(array[j]<array[j-1]){
					int temp = array[j];
					array[j] = array[j-1];
					array[j-1] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] randoms = new int[10];
		Random random = new Random();
		for(int i=0;i<randoms.length;i++){
			randoms[i] = random.nextInt();
		}
		System.out.println(Arrays.toString(randoms));
		insertSort(randoms);
		System.out.println(Arrays.toString(randoms));
		
	}
}
