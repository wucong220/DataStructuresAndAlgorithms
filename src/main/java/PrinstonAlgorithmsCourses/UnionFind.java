package PrinstonAlgorithmsCourses;

import base.Base;
import org.junit.Test;

import java.io.PrintStream;
import java.io.StringReader;
import java.util.*;


public class UnionFind extends Base {
    int[] unionFindSet = null;

    public UnionFind(int N){
        unionFindSet = new int[N];
        for (int i = 0; i < unionFindSet.length; i++) {
            unionFindSet[i]=i;
        }
    }

    public void union(int p,int q){
        int unionP = find(p);
        int unionQ = find(q);
        if(unionP!=unionQ){
            unionFindSet[unionP]=unionQ;
        }
    }

    public int find(int p){
        int t = p;
        while(p!=unionFindSet[p]) {
            p = unionFindSet[p];
        }
        while(t!=unionFindSet[t]){
            int old_t = t;
            t = unionFindSet[t];
            unionFindSet[old_t]=p;
        }
        return p;
    }

    public boolean connected(int p,int q){
        return find(p)==find(q);
    }

    public int count(){
        Set<Integer> set = new HashSet<>();
        for (int i : unionFindSet) {
            set.add(i);
        }
        return set.size();
    }

    public static void main(String[] args) {
        String input="10\n" +
                "4 3\n" +
                "3 8\n" +
                "6 5\n" +
                "9 4\n" +
                "2 1\n" +
                "8 9\n" +
                "5 0\n" +
                "7 2\n" +
                "6 1\n" +
                "1 0\n" +
                "6 7";
        StdIn = new Scanner(input);
        int N = StdIn.nextInt();
        UnionFind uf = new UnionFind(N);
        while(StdIn.hasNext()){
            int p = StdIn.nextInt();
            int q = StdIn.nextInt();
            if(!uf.connected(p,q)){
                uf.union(p,q);
                StdOut.println(p+" "+q);
            }
        }
        StdOut.println(uf.count());
    }

}
