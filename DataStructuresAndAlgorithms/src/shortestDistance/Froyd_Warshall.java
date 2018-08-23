package shortestDistance;

public class Froyd_Warshall {
//http://wiki.jikexueyuan.com/project/easy-learn-algorithm/floyd.html
	/*e[i][j]为点i到点j的距离。缺点不能解决负权边
	for(k=1;k<=n;k++)
     	//没个循环更新矩阵，只允许经过k号节点，两点之间的最短距离矩阵
        for(i=1;i<=n;i++)
            for(j=1;j<=n;j++)
                if(e[i][j]>e[i][k]+e[k][j])
                     e[i][j]=e[i][k]+e[k][j];
	*/
}
