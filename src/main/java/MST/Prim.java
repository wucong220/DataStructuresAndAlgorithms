package MST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author 1
 *
 */
public class Prim {
	static int[] minimumSpanningTree(List<List<Integer>> als,int[][] w){
		int cost[] = new int[als.size()];
		int prev[] = new int[als.size()];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[0]=0;
		Queue<Integer> q = new PriorityQueue<>((a,b)->cost[a]-cost[b]);
		for(int i=0;i<cost.length;i++){
			q.add(i);
		}
		int count=0;
		while(!q.isEmpty()){
			int v = q.poll();
			count++;
			if(count==als.size())break;
			List<Integer> al=als.get(v);
			for(Integer z:al){
				if(cost[z]>w[v][z]){   //prim和djkstra的唯一区别，dijkstra维护的是到源点的最短距离，prim维护的是到树的最短距离。
					cost[z] = w[v][z];
					prev[z] = v;
					q.offer(z);
				}
			}
		}
		return prev;
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
		
		System.out.println(Arrays.toString(minimumSpanningTree(als, w)));
		
		
	}
}

//算法思想和dijkstra很接近，选择一个初始点构成一个最小生成树集合，每次把离最小生成树集合最近的点加入集合。

/*伪代码   邻接表O(ElogV+VlogV); 邻接矩阵O(V^2)
procedure prim(G,w)
Input: A connected undirected graph G=(V,E) with edge weigts W_e
Output: A minimum spanning tree defined by the array prev

for all u∈V
	cost(u)=∞ //把点u加入最小生成树，所需要的距离开销
	prev(u)=null //点u的前驱节点
Pick any initial node u_0
cost(u_0)=0

H=makequeue(V) (priority queue, using cost-values as keys)
while(H is not empty)
	v = deletemin(H) //每次从优先队列中取出开销最小的点
	for each {v,z}∈E:  //遍历V所有的邻接点，更新他们的开销
		if cost(z) > w(v,z):
			cost(z) = w(v,z)
			prev(z) = v
			decreasekey(H,z)
		
*/