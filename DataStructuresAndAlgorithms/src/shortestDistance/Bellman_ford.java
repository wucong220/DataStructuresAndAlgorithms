package shortestDistance;

public class Bellman_ford {

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