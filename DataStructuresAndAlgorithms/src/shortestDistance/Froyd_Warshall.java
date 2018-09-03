package shortestDistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Froyd_Warshall {
//http://wiki.jikexueyuan.com/project/easy-learn-algorithm/floyd.html
	/*e[i][j]为点i到点j的距离。缺点不能解决负权边
	for(k=1;k<=n;k++)
     	//每个循环更新矩阵，只允许经过k号节点，两点之间的最短距离矩阵
        for(i=1;i<=n;i++)
            for(j=1;j<=n;j++)
                if(e[i][j]>e[i][k]+e[k][j])
                     e[i][j]=e[i][k]+e[k][j];
	*/
	static int[][] shosestPath(List<List<Integer>> als, int[][] w){
		
		int n = w.length-1;
		for(int k=0;k<=n;k++)
	     	//每个循环更新矩阵，只允许经过k号节点，两点之间的最短距离矩阵
	        for(int i=0;i<=n;i++)
	            for(int j=0;j<=n;j++)
	                if(w[i][k]!=Integer.MAX_VALUE&&w[k][j]!=Integer.MAX_VALUE&&w[i][j]>w[i][k]+w[k][j])
	                     w[i][j]=w[i][k]+w[k][j];
		
		return w;
	}
	
	public static void main(String[] args) {
		int m = Integer.MAX_VALUE;
		int[][] w = new int[][]{
			{m,7,m,5,m,m,m},
			{7,m,8,9,7,m,m},
			{m,8,m,m,5,m,m},
			{5,9,m,m,15,6,m},
			{m,7,5,15,m,8,9},
			{m,m,m,6,8,m,11},
			{m,m,m,m,9,11,m}
		};
		List<List<Integer>> als = new ArrayList<>();
		for(int i=0;i<w.length;i++){
			List<Integer> al = new ArrayList<>();
			for(int j=0;j<w.length;j++){
				if(w[i][j]<Integer.MAX_VALUE){
					al.add(j);
				}
			}
			als.add(al);
		}
		
		System.out.println(Arrays.deepToString(shosestPath(als, w)));
	}
	
	
}
