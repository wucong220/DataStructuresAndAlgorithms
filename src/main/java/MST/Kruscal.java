package MST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Kruscal {
	static int[] pre;//并查集
	static int kruscal(Graph G){
		List<Edge> A = new ArrayList<Edge>(G.V-1);
		pre =new int[G.V+1];
		for (int i = 0; i < pre.length; i++) {
			pre[i]=i;
		}
		Collections.sort(G.E,new Comparator<Edge>(){

			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				return o1.w-o2.w;
			}
			
		});
		
		for(Edge e:G.E){
			if(find(e.v1)!=find(e.v2)){
				A.add(e);
				union(e.v1,e.v2);
				if(A.size()==G.V-1){
					return e.w;
				}
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();//空地数
		int M = s.nextInt();//路径数
		Graph G = new Graph(N,new ArrayList<>());
		for(int i=0;i<M;i++){
			G.E.add(new Edge(s.nextInt(), s.nextInt(), s.nextInt()));
		}
		System.out.println(kruscal(G));
		s.close();
	}
	
	public static int find(int n){
		int m = n;
		while(pre[m]!=m){
			m=pre[m];
		}
		int i=n;
		while(pre[i]!=m){
			int temp = pre[i];
			pre[i]=m;
			i=temp;
		}
		return m;
	}
	
	public static void union(int x,int y){
		int a = find(x);
		int b = find(y);
		if(a!=b){
			pre[a]=b;
		}
	}
}

class Graph{
	int V;
	List<Edge> E;
	public Graph(int v, List<Edge> e) {
		super();
		V = v;
		E = e;
	}
}

class Edge{
	int v1;
	int v2;
	int w;
	public Edge(int v1, int v2, int w) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.w = w;
	}
}
