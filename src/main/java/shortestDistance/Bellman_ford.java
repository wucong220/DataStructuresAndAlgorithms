package shortestDistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bellman_ford {
	
	static int[] shosestPath(List<List<Integer>> als, int[][] w){
		int[] shortest = new int[als.size()];
		int[] prev = new int[als.size()];
		Arrays.fill(shortest, Integer.MAX_VALUE);
		shortest[0]=0;
		
		for(int i=0;i<als.size()-1;i++){
			for(int v=0;v<als.size();v++){
				for(Integer z:als.get(v)){
					if(shortest[v]!=Integer.MAX_VALUE&&shortest[z]>shortest[v]+w[v][z]){
						shortest[z] = shortest[v]+w[v][z];
						prev[z] = v;
					}
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

/*
//O(|E|*|V|)
procedure shortest-paths(G,l,s)
input:  ->Directed<- graph G = (V,E);
		edge lengths {l_e:e∈E} with no negative cycles
		vertex s∈V
output: For all vertices u reachable from s, dist(u) is set
		to the distance from s to u;

for all u∈V:
	dist(u) = ∞
	prev(u) = null
	
dist(s) = 0;
repeat |V|-1 times:
	for all e∈E:
		update(e)

precedure update((u,v)∈E)
dist(v) = min{dist(v),dist(u)+l(u,v)}
*/