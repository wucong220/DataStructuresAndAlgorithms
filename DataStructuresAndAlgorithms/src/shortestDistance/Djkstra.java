package shortestDistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Djkstra {
	


	static int[] shosestPath(List<List<Integer>> als, int[][] w){
		int[] shortest = new int[als.size()];
		int[] prev = new int[als.size()];
		Arrays.fill(shortest, Integer.MAX_VALUE);
		shortest[0]=0;
		Queue<Integer> q = new PriorityQueue<>((a,b)->(shortest[a]-shortest[b]));
		for(int i=0;i<als.size();i++)q.add(i);
		int count=0;
		while(!q.isEmpty()){
			int v = q.poll();
			count++;
			if(count==als.size())break;
			for(int z:als.get(v)){
				if(shortest[z]>shortest[v]+w[v][z]){
					shortest[z] = shortest[v]+w[v][z];
					prev[z] = v;
					q.offer(z);
				}
			}
		}
		
		return shortest;
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
		
		System.out.println(Arrays.toString(shosestPath(als, w)));
	}

}

//算法思想 贪心算法，每次把离原点最近的点加入，确定最小距离点集，并更新其邻接点的最短距离。 缺点无法处理负权边

/*伪代码 O(|E|+|V|log|V|)
procedure dijkstra(G,l,s)
Input:  graph G=(V,E), directed or undirected; 
		with positive edge lengths {l_e:e∈E}; vertex s∈V
Output: For all vertices u reachable from s, dist(u) is set to distance from s to u.

for all u∈V
	dist(u)=∞ //点u离原点的距离
	prev(u)=null //点u的前驱节点
dist(u)=0;

H=makequeue(V) (priority queue, using dist-values as keys) 优先队列
while(H is not empty)
	v = deletemin(H) //每次从优先队列中取出开销最小的点
	for each {v,z}∈E:  //遍历V所有的邻接点，更新他们的开销
		if dist(z) > dist(z)+l(v,z):
			dist(z) = dist(z)+l(v,z)
			prev(z) = v
			decreasekey(H,z)
*/
