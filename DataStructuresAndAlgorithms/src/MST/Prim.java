package MST;
/**
 * 
 * @author 1
 *
 */
public class Prim {

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