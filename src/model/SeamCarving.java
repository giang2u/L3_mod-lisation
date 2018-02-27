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
	public static int[][] im;
	
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
			im = new int[height][width];
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
		
		
		ArrayList<Integer> suurb = SeamCarving.Dijkstra(g, s, t);
		
		ArrayList<Integer> cout = new ArrayList<Integer>();
		
	
		for (int i= 0; i < suurb.size() - 1; i++){
			
			
			// modification du cout du 1er chemin le plus court a une tres grande valeur
			cout.add(g.getEdge(suurb.get(i),  suurb.get(i+1)).cout() ) ;
			g.getEdge(suurb.get(i),  suurb.get(i+1)).setCout(50000);
			}

		for (int i= 0; i < suurb.size() - 1; i++){
			
			//inversion
			
			Edge e = g.getEdge(suurb.get(i),  suurb.get(i+1));
			
			int dep = e.depart();
			e.setDepart(e.getTo());
			e.setTo(dep);
			
			}
		
		
		// calcul du 2eme chemin le plus court
		ArrayList<Integer> suurb2 = SeamCarving.Dijkstra(g, s, t);
		
		for (int i= 0; i < suurb.size() - 1; i++){
			
			//rinversion
			Edge e = g.getEdge(suurb.get(i+1),  suurb.get(i));
			
			int dep = e.depart();
			e.setDepart(e.getTo());
			e.setTo(dep);
		}

		
		for (int i= 0; i < cout.size(); i++){
			
			// remet les couts du 1er chemin a la normale
			g.getEdge(suurb.get(i),  suurb.get(i+1)).setCout(cout.get(i));
		}
		
		
		/*
		System.out.print("\nCHEMIN  1 twopath AVANT:");
		for (Integer p: suurb) {
			System.out.print("   " + p);
		}
		
		System.out.print("\nCHEMIN 2 :");
		for (Integer p: suurb2) {
			System.out.print("   " + p);
		}
		
		
		// transfert des bonnes aretes
					for (int j = 2; j < suurb2.size() - 1; j++) {
						int diff = Math.abs( suurb2.get(j-1) - suurb2.get(j) ) ;
						int diff2 = Math.abs( suurb.get(j-1) - suurb.get(j) ) ;
						
						boolean verif = diff > g.getLargeur() && suurb2.get(j)% g.getLargeur() == 0 ;
						boolean verif2 = diff > g.getLargeur() + 1 && suurb2.get(j)%g.getLargeur() != 0 ;
						//boolean verif3 = ( diff2 != g.getLargeur() ) && ( diff != g.getLargeur() );
						
						if (verif || verif2 ) {
							int echange = suurb.get(j);
							suurb.set(j, suurb2.get(j));
							suurb2.set(j, echange);
						}
					}
					
					
					System.out.print("\nCHEMIN  1 Twopath APRES:");
					for (Integer p: suurb) {
						System.out.print("   " + p);
					}
					
					System.out.print("\nCHEMIN 2 :");
					for (Integer p: suurb2) {
						System.out.print("   " + p);
					}
			
		int[][] tab = new int[2][suurb2.size()];
		
		int m = 0;
		// stocke les chemins des 2 dijkstra dans un tableau a 2 dimensions
		for (int i = 0; i < 2; i++) {
			for (int j =0; j < suurb.size(); j++)  {
				if ( i == 0) {
					tab[i][j] = suurb.get(j);
				}
				else {
					tab[i][j] = suurb2.get(j);
				}
			}
		}
		*/
		
		int[][] tab = new int[2][ 4 + ( (suurb2.size() - 4) / 2)];
		
		int m = 0;
		// stocke les chemins des 2 dijkstra dans un tableau a 2 dimensions
		for (int i = 0; i < 2; i++) {
			for (int j =0; j < 2; j++)  {
				if ( i == 0) {
					tab[i][j] = suurb.get(j);
				}
				else {
					
					//System.out.print("\n VALEUR POS " +  suurb2.get(j) + " VALEUR QUOTIENT " + (suurb2.get(j) / largeur) + " TEST " + ( suurb2.get(j) - suurb2.get(j)/ largeur ) );
					int diff = 0;
					/*if (suurb2.get(j) == 0) {
					}else if (  suurb2.get(j) < g.getLargeur()) {
						 diff = 1;
					} else if (suurb2.get(j)%g.getLargeur() == 1) {
						diff = suurb2.get(j)/g.getLargeur()-1;
					}else if (suurb2.get(j)%g.getLargeur() == 2) {
						diff = suurb2.get(j)/g.getLargeur();
					} else {
						diff = suurb2.get(j)/g.getLargeur()+1;
					}
					//System.out.println("\n RES "+ diff +  " res " + suurb2.get(j));
					tab[i][j] = suurb2.get(j) - diff ;
					
					int ligneCour = suurb2.get(j)/g.getLargeur() ;
					if ( ligneCour == 0 && suurb2.get(j) != 0 ) {
						diff = 1;
					} else if ( suurb2.get(j) != 0){
					diff =   ( (int)(ligneCour/2+0.5)*g.getLargeur()) ;
					}*/
					tab[i][j] = suurb2.get(j);
					
				}
			}
		}
		
		
		// saut de recuperation pour le premier chemin
		int j=2;
		// saut de recuperation pour le 2eme chemin
		m = 2;
		
		// remplissage du tableau avec les 2 chemins
		for (int p = 0; p < 2; p++) {
			m = 2;
			for (int k = 2 ; k < suurb2.size(); k+=2 ) {
				if (p%2==0 ) tab[p][m] = suurb.get(j); 
				else tab[p][m] = suurb2.get(k);
				j++;
				m++;
			}
		}
		tab[0][m] = suurb.get(suurb.size()-1);
		tab[1][m] = suurb2.get(suurb2.size()-1);

		// calcul des bonnes positions pour le chemin 2
		for (int p = 0; p < 2; p++) {
			//System.out.print(" \nVALEUR TABLEAU ");
			for (int k = 1; k < tab[0].length; k++ ) {

				if ( p == 1) {
					if ( tab[p][k] > g.getLargeur()*2 ) { 
						//int nbDePixelSupprAvant = ((tab[p][k]/g.getLargeur()) -1 )/2;
						int nbLigneDuplique =  (g.getLargeur() * (tab[p][k]/g.getLargeur()) -1 )/2;
						if (tab[p][k]%g.getLargeur() == 1) {
							//nbDePixelSupprAvant = ((tab[p][k]/g.getLargeur()) -1 )/2 - 1;
						}
						tab[p][k] = tab[p][k] -  nbLigneDuplique;// - nbDePixelSupprAvant; 
						
					}
				}
				//System.out.print("  "+  tab[p][k] );
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
