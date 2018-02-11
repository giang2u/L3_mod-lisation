package model;
import java.util.ArrayList;
import java.io.*;

public class Graph
{
   private ArrayList<Edge>[] adj;
   private final int V;
   int E;
@SuppressWarnings("unchecked")
   public Graph(int N)
	 {
		this.V = N;
		this.E = 0;
		 adj = (ArrayList<Edge>[]) new ArrayList[N];
		for (int v= 0; v < N; v++)
		  adj[v] = new ArrayList<Edge>();
		
	 }

   public int vertices()
	 {
		return V;
	 }
   
   public void addEdge(Edge e)
	 {
		int v = e.from;
		int w = e.getTo();
		adj[v].add(e);
		adj[w].add(e);
	 }
   
   public Iterable<Edge> adj(int v)
	 {
		return adj[v];
	 }      
   
   public void removeEdge(int from, int to) {
	   
	   for (int i = 0; i < adj[from].size(); i++) {
		  if ( adj[from].get(i).getTo() == to) adj[from].remove(i);
	   }
   }
   
   public Edge getEdge(int from, int to) {
	   
	   Edge e = null;
	   for (int i = 0; i < adj[from].size(); i++) {
		  if ( adj[from].get(i).getTo() == to)  e = adj[from].get(i);
	   }
	   
	   return e;
   }
   

   public Iterable<Edge> next(int v)
	 {
		ArrayList<Edge> n = new ArrayList<Edge>();
		for (Edge e: adj(v))
		  if (e.getTo() != v)
			n.add(e);
		return n;
	 }      
   
   public Iterable<Edge> edges()
	 {
		ArrayList<Edge> list = new ArrayList<Edge>();
        for (int v = 0; v < V; v++)
            for (Edge e : adj(v)) {
                if (e.getTo() != v)
                    list.add(e);
            }
        return list;
    }
   
   
   public void writeFile(String s)
	 {
		try
		  {			 
			 PrintWriter writer = new PrintWriter(s, "UTF-8");
			 writer.println("digraph G{");
			 for (Edge e: edges())
			   writer.println(e.from + "->" + e.getTo() + "[label=\"" + e.cost + "\"];");
			 writer.println("}");
			 writer.close();
		  }
		catch (IOException e)
		  {
		  }						
	 }

public ArrayList<Edge>[] getAdj() {
	return adj;
}

public void setAdj(ArrayList<Edge>[] adj) {
	this.adj = adj;
}

   
   
   
   
}
