import java.util.*;

public class Problem2 {
    static class Edge {
        String src;
        String dest;
        int weight;
        Edge(String src,String dest,int weight)
        {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
    static class Graph {
        int vertices;
        LinkedList<Edge>[] adj;

        int paths;

        int totalDist=0;
        Graph(int vertices) {
            this.vertices = vertices;
            adj = new LinkedList[vertices];
            for(int i = 0; i < vertices; i++) {
                adj[i] = new LinkedList<>();
            }
        }
        public void addEdge(String src,String dest,int weight)
        {
            Edge e1 = new Edge(src,dest,weight);
            Edge e2 = new Edge(dest,src,weight);
            int index1 = (int)(src.charAt(0))-65;
            int index2 = (int)(dest.charAt(0))-65;
            adj[index1].addFirst(e1);
            adj[index2].addFirst(e2);
        }
        public double solve(String a,String b)
        {
            boolean visited[]=new boolean[vertices];
            for(int i = 0; i < vertices; i++) {
                visited[i] = false;
            }
            paths = 0;
            totalDist = 0;
            visited[(int)(a.charAt(0))-65] = true;
            calculate(a,b,visited,0);
            return ((double)totalDist/(double) paths);
        }
        public void calculate(String a,String b,boolean visited[],int dis)
        {
            if(a.equals(b))
            {
                paths++;
                totalDist += dis;
                return;
            }
            int index = (int)(a.charAt(0))-65;
            for(int i = 0; i < adj[index].size(); i++)
            {
                String to = adj[index].get(i).dest;
                int indexOfdest = (int)(to.charAt(0))-65;
                if(visited[indexOfdest] == false)
                {
                    visited[indexOfdest] = true;
                    calculate(to,b, visited, dis+adj[index].get(i).weight);
                    visited[indexOfdest] = false;
                }
            }
            return;
        }
    }
    public static void main(String[] args)
    {
        Graph g = new Graph(5);
        g.addEdge("A","B",12);
        g.addEdge("A","C",13);
        g.addEdge("A","D",11);
        g.addEdge("A","E",8);
        g.addEdge("B","C",3);
        g.addEdge("C","E",4);
        g.addEdge("D","E",7);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Node One");
        String a = sc.next();
        System.out.println("Enter Node Two");
        String b = sc.next();
        double avgDis = g.solve(a,b);
        System.out.println("Average Distance between the two node is: "+avgDis);
    }
}