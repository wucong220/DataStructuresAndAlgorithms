package dataStructures;

public class FranwickTree {
	
	
	int lowBit(int n){
		return n&(-n);
	}
	
	void update(int[] sum,int i, int delta){
		while(i<sum.length){
			sum[i]+=delta;
			i+=lowBit(i);
		}
	}
	
	int query(int[] sum,int i){
		int s = 0;
		while(i>0){
			s+=sum[i];
			i-=lowBit(i);
		}
		return s;
	}
}
