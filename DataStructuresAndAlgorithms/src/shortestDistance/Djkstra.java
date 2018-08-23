package shortestDistance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Djkstra {
	
	
		// A utility function to find the vertex with minimum distance value,
		// from the set of vertices not yet included in shortest path tree
		static final int V=9;
		int minDistance(int dist[], Boolean sptSet[])
		{
			// Initialize min value
			int min = Integer.MAX_VALUE, min_index=-1;

			for (int v = 0; v < V; v++)
				if (sptSet[v] == false && dist[v] <= min)
				{
					min = dist[v];
					min_index = v;
				}

			return min_index;
		}

		// A utility function to print the constructed distance array
		void printSolution(int dist[], int n)
		{
			System.out.println("Vertex Distance from Source");
			for (int i = 0; i < V; i++)
				System.out.println(i+" tt "+dist[i]);
		}

		// Funtion that implements Dijkstra's single source shortest path
		// algorithm for a graph represented using adjacency matrix
		// representation
		void dijkstra(int graph[][], int src)
		{
			int dist[] = new int[V]; // The output array. dist[i] will hold
									// the shortest distance from src to i

			// sptSet[i] will true if vertex i is included in shortest
			// path tree or shortest distance from src to i is finalized
			Boolean sptSet[] = new Boolean[V];

			// Initialize all distances as INFINITE and stpSet[] as false
			for (int i = 0; i < V; i++)
			{
				dist[i] = Integer.MAX_VALUE;
				sptSet[i] = false;
			}

			// Distance of source vertex from itself is always 0
			dist[src] = 0;

			// Find shortest path for all vertices
			for (int count = 0; count < V-1; count++)
			{
				// Pick the minimum distance vertex from the set of vertices
				// not yet processed. u is always equal to src in first
				// iteration.
				int u = minDistance(dist, sptSet);

				// Mark the picked vertex as processed
				sptSet[u] = true;

				// Update dist value of the adjacent vertices of the
				// picked vertex.
				for (int v = 0; v < V; v++)

					// Update dist[v] only if is not in sptSet, there is an
					// edge from u to v, and total weight of path from src to
					// v through u is smaller than current value of dist[v]
					if (!sptSet[v] && graph[u][v]!=0 &&
							dist[u] != Integer.MAX_VALUE &&
							dist[u]+graph[u][v] < dist[v])
						dist[v] = dist[u] + graph[u][v];
			}

			// print the constructed distance array
			printSolution(dist, V);
		}
	//This code is contributed by Aakash Hasija


//	static int[] shortestDistanceHeap(int[][] matrix, int src) {
//		Map<Integer,Map<Integer,Integer>> map = new HashMap<>();
//		for(int i=0;i<matrix.length;i++){
//			
//		}
//		
//		Queue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
//		int[] ret = new int[1];
//		pq.offer(new int[]{0,0});
//		
//		while(!pq.isEmpty()){
//			int v = pq.peek()[0];
//			int dis = pq.peek()[1];
//			pq.poll();
//			if(ret[v]<=dis){
//				continue;
//			}
//			ret[v]=dis;
//			
//			Map<Integer,Integer> edges  = map.get(v);
//			for(int vetex:edges.keySet()){
//				pq.offer(new int[]{vetex,edges.get(vetex)+dis});
//			}
//			
//			
//			
//		}
//
//
//	}
	
	
	public static void main(String[] args) {
		int graph[][] = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
				{ 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
				{ 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

		//System.out.println(Arrays.toString(array));
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
