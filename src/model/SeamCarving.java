package model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

	public static void main (String[] args) {


	}

	public static void writepgm(int [][] image, String filename) {


		try{

			int  width = image.length;
			int height = image[0].length;
			//FileWriter fstream = new FileWriter("img/"+filename+".pgm");
			FileWriter fstream = new FileWriter(filename);
			//FileWriter fstream = new FileWriter("img/"+filename);
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
		   
		   
		   // relie le sommet initial 0 a la premiere ligne
		   for (int i =0 ; i < largeur; i++) {
			   a.addEdge( new Edge(compteur, i + 1, 0) ); 
		   }
		   
		   
		   // parcours du tableau entre la premiere et derniere ligne
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
		   
		   // derniere ligne qui rejoint le sommet fin
		   for (int i = 0; i < largeur; i++ ) {
			   a.addEdge( new Edge(compteur + i, largeur * hauteur + 1 , itr[hauteur-1][i]) );
		   }
		   
		   return a;
	   }
	
	
	
	public static Graph toGraph2(int [][] itr) {
		   
		   int largeur = itr[0].length;
		   int hauteur = itr.length;
			//System.out.println("hauteur" + itr[hauteur -1][largeur -1]);
		   Graph a = new Graph(largeur*2 + largeur*2 +2);
		   int compteur = 0;
		   
		  
		   // relie le sommet initial 0 a la premiere ligne
		   for (int i =0 ; i < largeur; i++) {
			   a.addEdge( new Edge(compteur, i + 1, 0) ); 
		   }
		   
		   
		   // creation des 2 ligne de sommets reliés par une arete de poids nul
		   compteur = 1;
		   for (int i = 1; i < 2; i++) {
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
		   
		   
		   for (int i = 0; i < 1; i++) {
			   for (int j = 0; j < largeur; j++) {
				   if (j == 0) {
					   a.addEdge( new Edge(compteur, compteur + largeur, 0 ) );
						  
				   }
				   else if (j == largeur -1) {
					   a.addEdge( new Edge(compteur, compteur + largeur , 0 ) );
						  
				   } 
				   
				   else {
					   a.addEdge( new Edge(compteur, compteur + largeur , 0 ) );
				   }
				   compteur++;
			   }
		   }
		   //---------------------------
		   
		   for (int i = hauteur - 1; i <  hauteur; i++) {
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
		   
		   
		   // derniere ligne qui rejoint le sommet fin
		   for (int i = 0; i < largeur; i++ ) {
			   a.addEdge( new Edge(compteur + i, largeur*2 + largeur*2 +1 , itr[hauteur-1][i]) );
		   }
		   
		   return a;
	   }
	
	
	
	// SUUUUUUUUUUUUUUUUUUUUUUUUURRRRRRRRRRRRRRRRRRRRRRRBBBBBBBBBBBBBBBAAAAAAAAAAAAAAALLLLLLLLLLLLLLEEEEE
	public static Graph suurballe(int [][] itr){
		Graph g = SeamCarving.toGraph2(itr);
		int sb[][] = new int [4][itr[0].length];
		int tab[] = new int [4*itr[0].length + 2];
		int compt = 1;
		
		
		
		// Calcul de la somme des sommets avec leur chemin le plus court
		
		for (int i = 1; i < 5; i++) {
			int somme = 0;
			for (int j = 0; j < itr[0].length ; j++) {
				somme = SeamCarving.coutDijkstra(g, 0, compt);
				sb[i-1][j] = somme;
				tab[compt] = somme;
				compt++;
			}
		}
		
		// ajout de la derniere ligne vers le dernier point fictif
		
		for (int i = 0; i < itr[0].length; i++ ) 
		{
			int somme = SeamCarving.coutDijkstra(g, 0, compt);
			tab[compt] = somme;
		}
		
		
		// 1er chemin le plus court
		ArrayList<Integer> suurb = SeamCarving.Dijkstra(g, 0, 4*itr[0].length + 1 );
		compt = 0;
		
		// Inversion du chemin le plus court
		
		for (Edge e : g.edges()) {
			e.setCout(e.cout() + ( tab[e.depart()] - tab[e.getTo()]  ) );
			if (compt < suurb.size()-1 && e.depart() == suurb.get(compt) && e.getTo() == suurb.get(compt+1) ) {
				int dep = e.depart();
				e.setDepart(e.getTo());
				e.setTo(dep);
				compt++;
			}
		}
		
		// Dijsktra pour chercher le 2eme chemin le plus court
		
		ArrayList<Integer>  suurb2 = SeamCarving.Dijkstra(g, 0, 4*itr[0].length + 1 );


		// taille du chemin des" courts
		int i = 0;
		if (suurb.size() > 0) i = 1;



		// Suppression des aretes jaunes

		ArrayList<Integer>  areteJaune = new ArrayList<Integer>();
		ArrayList<Integer>  saut = new ArrayList<Integer>();

		while(i < suurb.size() - 1)  {
			if (suurb2.contains(suurb.get(i) )   ) {

				// recuperation de la position dans suurb2

				int index = suurb2.indexOf(suurb.get(i));
				
				// On regarde si l'une des aretes du djikstra2 et aussi dans djisktra1
				if ( suurb2.get(index +1) == suurb.get(i + 1 ) ){
					areteJaune.add( suurb.get(i) );
					areteJaune.add( suurb.get(i+1) );
				}
				if ( suurb2.get(index - 1) == suurb.get(i + 1 ) ){
					areteJaune.add( suurb.get(i) );
					areteJaune.add( suurb.get(i+1) );
					
				}

			}
			i++;
		}
		
		// enlevage arete jaune
		
		for (i= 0; i < areteJaune.size() - 1 ; i+=2){
			g.removeEdge(areteJaune.get(i), areteJaune.get(i+1));
			g.removeEdge(areteJaune.get(i+1), areteJaune.get(i));
			
			for (int j = 0; j < suurb.size() ; j++) {
				
				if (suurb.get(j) == areteJaune.get(i) && suurb.get(j+1) == areteJaune.get(i+1)) {
					
					// ajout des sommets à ne pas inverser avec leur precedent sommet
					saut.add(j);
					//System.out.println(" TEST SUURBATIOOn " + suurb.get(j) + "  " +  areteJaune.get(i)   +"     " + suurb.get(j+1)  + "   " + areteJaune.get(i+1) );
				/*	
					suurb.remove(j+1); 
					suurb.remove(j);*/
				}
			}
		}
		
		
		// re inversion des aretes
		
		// deplacement dans le djikstra du 1er chemin
		compt = 1;
		
		// deplacement dans la liste de sommet a ne pas inverser car supprimer
		i = 0;
		
		for (Edge e : g.edges()) {
			
			
			if (compt < suurb.size() && e.depart() == suurb.get(compt) && e.getTo() == suurb.get(compt-1) ) {
				
				if (i < saut.size() && compt == saut.get(i)) {
					compt++; 
					i++; 
				}
				int dep = e.depart();
				e.setDepart(e.getTo());
				e.setTo(dep);
				compt++;
				
			}
		}
		return g;
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
	
	public static int coutDijkstra(Graph graph, int s , int  t){
		
		int cout =  0;
		
		Heap heap = new Heap(graph.vertices());
		ArrayList<Integer> list = new ArrayList<>();
		int[] precedent = new int[graph.vertices()];
		int [] p = new int[graph.vertices()];
		heap.decreaseKey(s, 0);
		while(!heap.isEmpty()){
			int elem = heap.pop();

			for(Edge edge: graph.adj(elem)){
				
				if(heap.priority(elem) + edge.cout() < heap.priority(edge.arrive())){
					int costtemp = heap.priority(elem) + edge.cout();
					heap.decreaseKey(edge.arrive(),costtemp);
					precedent[edge.arrive()] = elem;  
					p[edge.arrive()] = edge.cout();
				}
				
			}
		}
		//t = arrive
		int s1 = 0;
		while( s != t){
			s1 = p[t];
			list.add(0, s1);
			t = precedent[t];
		}
		
		for(Integer k : list) {
			cout += k;
			
		}
		
		return cout;
	}
	
	
	
	public static void supprimerPixel(String filename,String outfile, int iteration) {
		
		//filename += "2";
		
		//int[][] fin = SeamCarving.readpgm(filename+".pgm");	
		int[][] fin = SeamCarving.readpgm(filename);	
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
		//outfile = "reduction_"+outfile;
		SeamCarving.writepgm(tab, outfile);
		System.out.println("DONE!!!!");
		
	}
	
	
	
	
	
}
