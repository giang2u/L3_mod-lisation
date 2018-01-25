import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;
import java.util.*;

import sun.invoke.empty.Empty;
public class SeamCarving
{

	public static int[][] readpgm(String fn)
	{		
		try {
			System.out.println("NOM FICHIER " + fn);
			InputStream f = ClassLoader.getSystemClassLoader().getResourceAsStream(fn);
			BufferedReader d = new BufferedReader(new InputStreamReader(f));
			String magic = d.readLine();
			String line = d.readLine();
			while (line.startsWith("#")) {
				line = d.readLine();
			}
			Scanner s = new Scanner(line);
			int width = s.nextInt();
			int height = s.nextInt();		   
			line = d.readLine();
			s = new Scanner(line);
			int maxVal = s.nextInt();
			int[][] im = new int[height][width];
			s = new Scanner(d);
			int count = 0;
			while (count < height*width) {
				im[count / width][count % width] = s.nextInt();
				count++;
			}
			return im;
		}

		catch(Throwable t) {
			t.printStackTrace(System.err) ;
			return null;
		}
	}

	public static void main (String[] args) {


	}

	public static void writepgm(int [][] image, String filename) {


		try{

			int  width = image.length;
			int height = image[0].length;
			FileWriter fstream = new FileWriter("img/"+filename+".pgm");
			//FileWriter fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("P2\n# CREATOR: MOI \n"+height+ " "+ width +"\n255\n");

			for(int i = 0 ; i<width;i++) {
				for(int j = 0 ; j<height;j++) {
					if ( j < height-1 ) out.write(image[i][j]+" ");
					else out.write(image[i][j]+"\n");
					
				}
			}
			out.close();
		}
		catch (Exception e){
			System.err.println("Error : " + e.getMessage());
		}

	}
	
	public static int[][] interest(int[][] image){
		int largeur = image[0].length;
		int hauteur = image.length;
		int [][] outTab = new int [hauteur][largeur];
		
		for(int i = 0; i < hauteur; i++){
			for(int j = 1 ; j < largeur - 1; j++ ){
				int moyen = (image[i][j-1] + image[i][j+1])/2;
				outTab[i][j] = Math.abs(image[i][j] - moyen);
			}
		}
		for(int j = 0 ; j < hauteur; j++ ){
			outTab[j][0] = Math.abs(image[j][0] - image[j][1]);
			outTab[j][largeur- 1] = Math.abs(image[j][largeur - 1]- image[j][largeur - 2] );
		}
		return outTab;
	}
	
	
	public static Graph toGraph(int [][] itr) {
		   
		   int largeur = itr[0].length;
		   int hauteur = itr.length;

			//System.out.println("hauteur" + itr[hauteur -1][largeur -1]);
		   Graph a = new Graph(largeur*hauteur +2);
		   int compteur = 0;
		   
		   for (int i =0 ; i < largeur; i++) {
			   a.addEdge( new Edge(compteur, i + 1, 0) ); 
		   }
		   
		   compteur = 1;
		   for (int i = 1; i < hauteur; i++) {
			   for (int j = 0; j < largeur; j++) {
				   if (j == 0) {
					   a.addEdge( new Edge(compteur, compteur + largeur, itr[i-1][j] ) );
					   a.addEdge( new Edge(compteur, compteur + largeur + 1, itr[i-1][j] ) );
						  
				   }
				   else if (j == largeur -1) {
					   a.addEdge( new Edge(compteur, compteur + largeur - 1 , itr[i-1][j] ) );
					   a.addEdge( new Edge(compteur, compteur + largeur , itr[i-1][j] ) );
						  
				   } 
				   
				   else {
					   a.addEdge( new Edge(compteur, compteur + largeur - 1 , itr[i-1][j] ) );
					   a.addEdge( new Edge(compteur, compteur + largeur , itr[i-1][j] ) );
					   a.addEdge( new Edge(compteur, compteur + largeur + 1 , itr[i-1][j] ) );
				   }
				   compteur++;
			   }
		   }
		   
		   for (int i = 0; i < largeur; i++ ) {
			   a.addEdge( new Edge(compteur + i, largeur * hauteur + 1 , itr[hauteur-1][i]) );
		   }
		   
		   return a;
	   }
	
	
	/*
	public static ArrayList<Integer> Dijkstra(Graph g, int s, int t) {
		
		ArrayList<Integer> tab = new ArrayList<Integer>();
		 ArrayList<Edge>[] test = g.getAdj();
		Iterable<Edge> ite = g.next(t);
		
		int i = 0;
		boolean start = false;
		
		for (Edge e : ite) {
			
			Edge ed = test.get(e.to);
			
			if ( ed.cost > e.cost) ed.cost += e.cost;
			
			if ( start &&  !tab.contains(ed.to) )  tab.add(ed.to);
			
			else if ( ed.cost < tab.get(tab.size()-1) ) {
				int last = tab.size() - 1; 
				tab.set(last, ed.to);
			}
			
			if ( e.from == s) tab.add(e.from); start = true;
			
		}
		
		return tab;
	}
	*/

	
	public static ArrayList<Integer> Dijkstra(Graph graph, int s , int  t){
		
		Heap heap = new Heap(graph.vertices());
		ArrayList<Integer> list = new ArrayList<>();
		int[] precedent = new int[graph.vertices()];
		heap.decreaseKey(s, 0);
		while(!heap.isEmpty()){
			int elem = heap.pop();

			for(Edge edge: graph.adj(elem)){
				
				if(heap.priority(elem) + edge.cout() < heap.priority(edge.arrive())){
					int costtemp = heap.priority(elem) + edge.cout();
					heap.decreaseKey(edge.arrive(),costtemp);
					precedent[edge.arrive()] = elem;  
				}
				
			}
		}

		//t = arrive

		while( s != t){
			list.add(0, t);
			t = precedent[t];
		}

		list.add(0,t);
		return list ;
	}
	
	
	
	public static void supprimerPixel(String filename, int iteration) {
		
		//filename += "2";
		
		int[][] fin = SeamCarving.readpgm(filename+".pgm");	
		//int[][] fin = SeamCarving.readpgm(filename);	
		int[][] outTAb = SeamCarving.interest(fin);
		Graph g = SeamCarving.toGraph(outTAb);
		ArrayList<Integer> list = SeamCarving.Dijkstra(g, 0, fin.length*fin[0].length + 1);
		
		
		int  width = outTAb[0].length;
		int height = outTAb.length;
		
		int[][] tab = new int[fin.length][fin[0].length-1] ;
		
		int indicePixelASuppr = 1;
		
		
		//System.out.println("TAILE " + width);
		/*
		for (int lk : list) {
			System.out.println(lk);
		}*/
		
		
		for(int i = 0 ; i<tab.length;i++) {
			int z = 0;
			for(int j = 0 ; j<tab[0].length ;j++) {
				
				
				int longu = j+1;
				
				if (list.get(indicePixelASuppr) == (  (i* outTAb[0].length )  + longu)  )  {
					indicePixelASuppr++;
					z++; tab[i][j] = fin[i][z]; 
				} 
				else tab[i][j] = fin[i][z];
				
				z++;
			}
		}
		
		
		for (int k = 0; k < iteration - 1; k++) {
			indicePixelASuppr = 1;
			
			fin = tab;
			outTAb = SeamCarving.interest(fin);
			g = SeamCarving.toGraph(outTAb);
			list = SeamCarving.Dijkstra(g, 0, fin.length*fin[0].length + 1);
			 
			tab = new int[fin.length][fin[0].length-1] ;

			for(int i = 0 ; i<tab.length;i++) {
				int z = 0;
				for(int j = 0 ; j<tab[0].length ;j++) {
					
					
					int longu = j+1;
					
					if (list.get(indicePixelASuppr) == (  (i* outTAb[0].length )  + longu)  )  {
						indicePixelASuppr++;
						 z++; tab[i][j] = fin[i][z]; 
					} 
					else tab[i][j] = fin[i][z];
					
					z++;
				}
			}
			
			
			
			
		}
		filename += "2";
		SeamCarving.writepgm(tab, filename);
		System.out.println("DONE!!!!");
		
	}
	/*
	public static void writepgm2(int [][] image, String filename, ArrayList<Integer> list) {


		try{
			
			int indicePixelASuppr = 1;
			int  width = image[0].length;
			int height = image.length;
			int largeur = width -1;
			FileWriter fstream = new FileWriter("./img/"+filename+".pgm");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("P2\n# CREATOR: MOI \n"+ largeur + " "+ height +"\n255\n");

			
			for(int i = 0 ; i<height;i++) {
				for(int j = 0 ; j<width;j++) {
					
					
					int longu = j+1;
					
					if (list.get(indicePixelASuppr) == (  (i* width )  + longu)  )  {
						int test = (i* width )  + longu;
						System.out.println( " PIXEL A SUPPR  " + list.get(indicePixelASuppr) + "\n" +  test);
						if (j == width -1)  out.write("\n");
						indicePixelASuppr++;
					} 
					else if ( j < width-1 ) out.write(image[i][j]+" ");  
					else out.write(image[i][j]+"\n");
					
				}
			}
			out.close();
		}
		catch (Exception e){
			System.err.println("Error : " + e.getMessage());
		}

	}*/
	
	
	
	
	
	
}
