package model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.*;
import java.util.*;

import sun.invoke.empty.Empty;
//import sun.org.mozilla.javascript.ast.WithStatement;
public class SeamCarving
{

	public static int[][] readpgm(String fn)
	{		
		try {
			System.out.println("NOM FICHIER " + fn);
			InputStream f = ClassLoader.getSystemClassLoader().getResourceAsStream(fn);
			BufferedReader d = new BufferedReader(new InputStreamReader(f));

			//FileReader flot = new FileReader(fn);
			//BufferedReader d = new BufferedReader(flot);
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

	public static int[][][] readppm(String fn)
	{		
		try {
			System.out.println("NOM FICHIER " + fn);
			InputStream f = ClassLoader.getSystemClassLoader().getResourceAsStream(fn);
			BufferedReader d = new BufferedReader(new InputStreamReader(f));

			//FileReader flot = new FileReader(fn);
			//BufferedReader d = new BufferedReader(flot);
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
			int[][][] im = new int[height][width][3];
			s = new Scanner(d);
			for(int lig = 0; lig < height ; lig++ ){
				for(int col = 0 ; col < width ; col++){
					for(int i = 0; i < 3 ; i++){
						im[lig][col][i] = s.nextInt();
					}
				}
				//im[count / width][count % width] = s.nextInt();
			}
			return im;
		}

		catch(Throwable t) {
			t.printStackTrace(System.err) ;
			return null;
		}
	}
	


	public static void writepgm(int [][] image, String filename) {


		try{

			int  width = image.length;
			int height = image[0].length ;
			//FileWriter fstream = new FileWriter("img/"+filename+".pgm");
			FileWriter fstream = new FileWriter(filename);
			//FileWriter fstream = new FileWriter("img/"+filename);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("P2\n# CREATOR: MOI \n"+height+ " "+ width +"\n255\n");
			
			/*------------------GESTIOB COLOR-------------------------*/
			//out.write("P3\n# CREATOR: MOI \n"+height+ " "+ width +"\n 255  #net en RGB\n");

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
	
	public static void writeppm(int [][][] image, String filename) {


		try{

			int  width = image.length;
			int height = image[0].length ;
			//FileWriter fstream = new FileWriter("img/"+filename+".pgm");
			FileWriter fstream = new FileWriter(filename);
			//FileWriter fstream = new FileWriter("img/"+filename);
			BufferedWriter out = new BufferedWriter(fstream);
			//out.write("P2\n# CREATOR: MOI \n"+height+ " "+ width +"\n255\n");
			
			/*------------------GESTIOB COLOR-------------------------*/
			out.write("P3\n# CREATOR: MOI \n"+height+ " "+ width +"\n255  #net en RGB\n");

			for(int i = 0 ; i<width;i++) {
				for(int j = 0 ; j<height;j++) {
					if( j < height-1 ){
						out.write(image[i][j][0]+" ");
						out.write(image[i][j][1]+" ");
						out.write(image[i][j][2]+" ");
					}
					else{
						out.write(image[i][j][0]+" ");
						out.write(image[i][j][1]+" ");
						out.write(image[i][j][2]+"\n");
					}
					
				}
			}
			out.close();
		}
		catch (Exception e){
			System.err.println("Error : " + e.getMessage());
		}

	}
	
	
	
 public static int[][] twopath(Graph g, int s, int t) {
		
		
		ArrayList<Integer> dij1 = SeamCarving.Dijkstra(g, s, t);
		
		ArrayList<Integer> cout = new ArrayList<Integer>();
		
	
		for (int i= 0; i < dij1.size() - 1; i++){
			/*g.removeEdge(dij1.get(i), dij1.get(i+1));
			System.out.println(" COUPLE EDGE : " + dij1.get(i)  +"    " + dij1.get(i+1));*/
			
			// modification du cout du 1er chemin le plus court a une tres grande valeur
			cout.add(g.getEdge(dij1.get(i),  dij1.get(i+1)).cout() ) ;
			g.getEdge(dij1.get(i),  dij1.get(i+1)).setCout(50000);
			}

		
		// calcul du 2eme chemin le plus court
		ArrayList<Integer> dij2 = SeamCarving.Dijkstra(g, s, t);
		

		
		for (int i= 0; i < cout.size(); i++){
			
			// remet les couts du 1er chemin a la normale
			g.getEdge(dij1.get(i),  dij1.get(i+1)).setCout(cout.get(i));
		}
		
		
		
		System.out.print("\nCHEMIN  1 twopath AVANT:");
		for (Integer p: dij1) {
			System.out.print("   " + p);
		}
		
		System.out.print("\nCHEMIN 2 :");
		for (Integer p: dij2) {
			System.out.print("   " + p);
		}
		
		
		// transfert des bonnes aretes
					for (int j = 1; j < dij2.size(); j++) {
						int diff = Math.abs( dij2.get(j-1) - dij2.get(j) ) ;
						
						boolean verif = diff > g.getLargeur() && dij1.get(j)% g.getLargeur() == 0 ;
						boolean verif2 = diff > g.getLargeur() + 1 && dij1.get(j)%g.getLargeur() != 0 ;

						if (verif || verif2 ) {
							int echange = dij1.get(j);
							dij1.set(j, dij2.get(j));
							dij2.set(j, echange);
						}
					}
					
					
					System.out.print("\nCHEMIN  1 Twopath APRES:");
					for (Integer p: dij1) {
						System.out.print("   " + p);
					}
					
					System.out.print("\nCHEMIN 2 :");
					for (Integer p: dij2) {
						System.out.print("   " + p);
					}
		
		int[][] tab = new int[2][dij1.size()];
		
		// stocke les chemins des 2 dijkstra dans un tableau a 2 dimensions
		for (int i = 0; i < 2; i++) {
			for (int j =0; j < dij1.size(); j++)  {
				if ( i == 0) tab[i][j] = dij1.get(j);
				else {
					System.out.print("\n VALEUR POS " +  dij2.get(j) + " VALEUR QUOTIENT " + (dij2.get(j) / g.getLargeur()) + " TEST " + ( dij2.get(j) - dij2.get(j)/ g.getLargeur() ) );
					int diff = dij2.get(j)/g.getLargeur();
					if (  dij2.get(j) != 0) {
						 diff = dij2.get(j)/g.getLargeur() ;
					}
					System.out.println("\n RES "+ diff +  " res " + dij2.get(j));
					tab[i][j] = dij2.get(j) - diff ;
				}
			}
		}
		
		System.out.print("\ntest " );
		for (int i = 0; i < 2; i++) {
			for (int j =0; j < dij1.size(); j++)  {
				System.out.print(" " + tab[i][j] );
				}
		}
		
		
		
		
		return tab;
	}
	
	
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
	
	public static void main (String[] args) {


	}
}
