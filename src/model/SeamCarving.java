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
	
	public static void main (String[] args) {


	}

	public static void writepgm(int [][] image, String filename) {


		try{

			int  width = image.length;
			int height = image[0].length ;
			//FileWriter fstream = new FileWriter("img/"+filename+".pgm");
			FileWriter fstream = new FileWriter(filename);
			//FileWriter fstream = new FileWriter("img/"+filename);
			BufferedWriter out = new BufferedWriter(fstream);
			//out.write("P2\n# CREATOR: MOI \n"+height+ " "+ width +"\n255\n");
			
			/*------------------GESTIOB COLOR-------------------------*/
			out.write("P3\n# CREATOR: MOI \n"+height+ " "+ width +"\n 255  #net en RGB\n");

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
	
	
	public static int[][] interestPPM(int[][][] image){
		int largeur = image[0].length;
		int hauteur = image.length;
		int [][] outTab = new int [hauteur][largeur];

		/*
		 * A B C
		 * D E F
		 * G H I
		 */
		int lum_a, lum_b, lum_c, lum_d, lum_f, lum_h, lum_g, lum_i, xEnergy, yEnergy;
		
		//parti normal sans les extremite ni coin
		for(int i = 1; i < hauteur -1; i++){
			for(int j = 1 ; j < largeur -1 ; j++ ){
				lum_a = image[i-1][j-1][0] +  image[i-1][j-1][1] +  image[i-1][j-1][2];
				lum_b = image[i-1][j][0] + image[i-1][j][1] + image[i-1][j][2];
				lum_c = image[i-1][j+1][0] + image[i-1][j+1][1] + image[i-1][j+1][2];
				lum_d = image[i][j-1][0] + image[i][j-1][1] + image[i][j-1][2];
				lum_g = image[i+1][j-1][0] + image[i+1][j-1][1] + image[i+1][j-1][2];
				lum_f = image[i][j+1][0] + image[i][j+1][1] + image[i][j+1][2];
				lum_h = image[i+1][j][0] + image[i+1][j][1] + image[i+1][j][2];
				lum_i = image[i+1][j+1][0] + image[i+1][j+1][1] + image[i+1][j+1][2];
				
				xEnergy = lum_a + 2 * lum_d + lum_g - lum_c - 2 * lum_f - lum_i;
				yEnergy = lum_a + 2 * lum_b + lum_c - lum_g - 2 * lum_h - lum_i;
				outTab[i][j] = (int)Math.sqrt(xEnergy * xEnergy  + yEnergy * yEnergy);
			}
		}

		for(int i = 0 ; i < largeur; i++ ){
			if(i == 0){
				// coin en haute a gauche
				lum_f = image[0][i + 1][0] +  image[0][i + 1][1] +  image[0][i + 1][2];
				lum_h = image[1][i][0] + image[1][i][1] + image[1][i][2];
				lum_i = image[1][i + 1][0] + image[1][i + 1][1] + image[1][i + 1][2];
				xEnergy = 2 * lum_f - lum_i;
				yEnergy = 2 * lum_h - lum_i;
				outTab[0][0] = (int)Math.sqrt(xEnergy * xEnergy  + yEnergy * yEnergy);
				
				// coin en bas a gauche
				lum_b = image[hauteur - 2][i][0] + image[hauteur - 2][i][1] + image[hauteur - 2][i][2];
				lum_c = image[hauteur - 2][1][0] + image[hauteur - 2][1][1] + image[hauteur - 2][1][2];
				lum_f = image[hauteur - 1][1][0] + image[hauteur - 1][1][1] + image[hauteur - 1][1][2];
				
				xEnergy = 0 - lum_c - 2 * lum_f ;
				yEnergy = 2 * lum_b + lum_c;
				outTab[hauteur - 1][0] = (int)Math.sqrt(xEnergy * xEnergy  + yEnergy * yEnergy);
				
			}
			
			else if(i == largeur - 1){
				// coin en haut a droit
				lum_d = image[0][i-1][0] + image[0][i-1][1] + image[0][i-1][2];
				lum_g = image[1][i-1][0] + image[1][i-1][1] + image[1][i-1][2];
				lum_h = image[1][i][0] + image[1][i][1] + image[1][i][2];
				
				xEnergy =  2 * lum_d + lum_g;
				yEnergy = 0 - lum_g - 2 * lum_h;
				outTab[0][i] = (int)Math.sqrt(xEnergy * xEnergy  + yEnergy * yEnergy);

				// coin en bas a droit
				lum_a = image[hauteur - 2][i-1][0] + image[hauteur - 2 ][i-1][1] + image[hauteur - 2][i-1][2];
				lum_b = image[hauteur - 2][i][0] + image[hauteur - 2][i-1][1] + image[hauteur - 2][i-1][2];
				lum_d = image[hauteur - 1][i - 1][0] + image[hauteur - 1][i -1][1] + image[hauteur - 1][i - 1][2];
				

				xEnergy = lum_a + 2 * lum_d ;
				yEnergy = lum_a + 2 * lum_b ;
				outTab[hauteur - 1][i] = (int)Math.sqrt(xEnergy * xEnergy  + yEnergy * yEnergy);
			} else{
				//line ou indice colonne = 0
				lum_d = image[0][i-1][0] + image[0][i-1][1] + image[0][i-1][2];
				lum_g = image[1][i - 1][0] + image[1][i - 1][1] + image[i - 1][1][2];
				lum_h = image[1][i][0] + image[1][i][1] + image[i][largeur-2][2];
				lum_i = image[1][i+1][0] + image[1][i+1][1] + image[1][i+1][2];
				lum_f = image[0][i+1][0] + image[0][i+1][1] + image[0][i+1][2];
				

				xEnergy = 2 * lum_d + lum_g - 2 * lum_f - lum_i;
				yEnergy =  lum_g - 2 * lum_h - lum_i;
				outTab[0][i] = (int)Math.sqrt(xEnergy * xEnergy  + yEnergy * yEnergy);
				
				//ligne ou colone = max
				lum_a = image[hauteur-2][i-1][0] + image[hauteur - 2][i-1][1] + image[hauteur - 2][i-1][2];
				lum_b = image[hauteur-2][i][0] + image[hauteur - 2][i][1] + image[hauteur - 2][i][2];
				lum_c = image[hauteur-2][i+1][0] + image[hauteur - 2][i+1][1] + image[hauteur - 2][i+1][2];
				lum_d = image[hauteur-1][i-1][0] + image[hauteur - 1][i-1][1] + image[hauteur - 1][i-1][2];
				lum_f = image[hauteur-1][i+1][0] + image[hauteur - 1][i+1][1] + image[hauteur - 1][i+1][2];
				
				xEnergy = lum_a + 2 * lum_d - lum_c - 2 * lum_f ;
				yEnergy = lum_a + 2 * lum_b + lum_c;
				outTab[hauteur - 1][i] = (int)Math.sqrt(xEnergy * xEnergy  + yEnergy * yEnergy);
			}
			
		}
		for(int i = 1; i < hauteur - 1; ++i){			
			
			//colonne ou largeur = 0
			lum_b = image[i - 1][0][0] + image[i - 1][0][1] + image[i - 1][0][2];
			lum_c = image[i - 1][1][0] + image[i - 1][1][1] + image[i - 1][1][2];
			lum_f = image[i][1][0] + image[i][1][1] + image[i][1][2];
			lum_h = image[i +  1][0][0] + image[i+1][0][1] + image[i+1][0][2];
			lum_i = image[i+1][1][0] + image[i+1][1][1] + image[i+1][1][2];
			
			xEnergy = 0 - lum_c - 2 * lum_f - lum_i;
			yEnergy =  2 * lum_b + lum_c - 2 * lum_h - lum_i;
			outTab[i][0] = (int)Math.sqrt(xEnergy * xEnergy  + yEnergy * yEnergy);
			
			//colonne ou largeur max
			lum_a = image[i-1][largeur - 2][0] + image[i-1][largeur - 2][1] + image[i-1][largeur-2][2];
			lum_b = image[i-1][largeur - 1][0] + image[i-1][largeur - 1][1] + image[i-1][largeur-1][2];
			lum_d = image[i][largeur - 2][0] + image[i][largeur - 2][1] + image[i][largeur-2][2];
			lum_g = image[i+1][largeur - 2][0] + image[i+1][largeur - 2][1] + image[i+1][largeur-2][2];
			lum_h = image[i+1][largeur - 1][0] + image[i+1][largeur - 1][1] + image[i+1][largeur-1][2];
			

			xEnergy = lum_a + 2 * lum_d + lum_g;
			yEnergy = lum_a + 2 * lum_b - lum_g - 2 * lum_h ;
			outTab[i][largeur - 1] = (int)Math.sqrt(xEnergy * xEnergy  + yEnergy * yEnergy);
			
			
		}
		
		return outTab;
	}
	/*
	public static int[][] interestEnergie(int[][] image){
		int largeur = image[0].length;
		int hauteur = image.length;
		int [][] outTab = new int [hauteur][largeur*2 + 2];
		
		for(int i = 0; i < hauteur - 1; i++){
			for(int j = 1 ; j < largeur - 1; j++ ){
				int k = 0;
				int res = (image[i][j+1] - image[i][j-1]);
				int res1 = (image[i][j+1] - image[i+1][j]);
				int res2 = (image[i][j-1] - image[i+1][j]);
				outTab[i][j] = Math.abs(res);
				outTab[i][j+1] = Math.abs(res1);
				outTab[i][j-1] = Math.abs(res2);
			}
		}
		for(int j = 0 ; j < hauteur; j++ ){
			outTab[j][0] = Math.abs(0 - image[j][1]);
			outTab[j][largeur- 1] = Math.abs(0- image[j][largeur - 2] );
		}
		return outTab;
	}
	*/
	
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
		   Graph a = new Graph(hauteur * largeur +2 + (  (hauteur - 2) * largeur ));
		   
		   int newhauteur =hauteur + hauteur - 2;
		   
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
		   
		   
		   
		   for (int i = 2; i < newhauteur - 1 ; i++) {
			   for (int j = 0; j < largeur; j++) {
					   a.addEdge( new Edge(compteur, compteur + largeur , 0 ) );
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
			   
			   a.addEdge( new Edge(compteur + i, newhauteur * largeur + 1 , itr[hauteur-1][i]) );
		   }
		   System.out.println(" hauteur " + hauteur + " largeur  " + largeur);
		   return a;
	   }
	
	
	
	// SUUUUUUUUUUUUUUUUUUUUUUUUURRRRRRRRRRRRRRRRRRRRRRRBBBBBBBBBBBBBBBAAAAAAAAAAAAAAALLLLLLLLLLLLLLEEEEE
	public static Graph suurballe(int [][] itr){
		Graph g = SeamCarving.toGraph2(itr);
		
		  int largeur = itr[0].length;
		  int hauteur = itr.length;
		  hauteur += hauteur -2;
		
		
		int sb[][] = new int [hauteur][largeur];
		int tab[] = new int [hauteur*largeur + 2];
		int compt = 1;
		
		
		
		// Calcul de la somme des sommets avec leur chemin le plus court
		
		for (int i = 1; i < hauteur+1; i++) {
			int somme = 0;
			for (int j = 0; j < largeur ; j++) {
				somme = SeamCarving.coutDijkstra(g, 0, compt);
				sb[i-1][j] = somme;
				tab[compt] = somme;
				
				
				compt++;
			}
		}
		
		// ajout de la derniere ligne vers le dernier point fictif
		
		for (int i = 0; i < largeur; i++ ) 
		{
			int somme = SeamCarving.coutDijkstra(g, 0, compt);
			tab[compt] = somme;
		}
		
		
		// 1er chemin le plus court
		ArrayList<Integer> suurb = SeamCarving.Dijkstra(g, 0, hauteur * largeur + 1 );
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
		
		ArrayList<Integer>  suurb2 = SeamCarving.Dijkstra(g, 0,  hauteur * largeur + 1 );


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
			
			for (int j = 0; j < suurb.size() - 1 ; j++) {
				
				if (suurb.get(j) == areteJaune.get(i) && suurb.get(j+1) == areteJaune.get(i+1)) {
					// ajout des sommets à ne pas inverser avec leur precedent sommet
					saut.add(j);
				}
			}
			
			
			for (int j = 0; j < suurb2.size() - 1; j++) {
			
				if (suurb2.get(j) == areteJaune.get(i+1) && suurb2.get(j+1) == areteJaune.get(i)) {
					//System.out.println(" TEST SUURBATIOOn " + suurb2.get(j) + "  " +  areteJaune.get(i+1)   +"     " + suurb2.get(j+1)  + "   " + areteJaune.get(i) );
					
					suurb2.remove(j+1); 
					suurb2.remove(j);
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
		
		// transfert des bonnes aretes
		for (int j = 1; j < suurb.size(); j++) {
			int diff = Math.abs( suurb2.get(j-1) - suurb2.get(j) ) ;
			boolean verif = diff > itr[0].length && suurb.get(j)% itr[0].length == 0 ;
			boolean verif2 = diff > itr[0].length + 1 && suurb.get(j)% itr[0].length != 0 ;
			if (verif || verif2 ) {
				int echange = suurb.get(j);
				suurb.set(j, suurb2.get(j));
				suurb2.set(j, echange);
			}
		}
		
		return g;
	}
	
	
	public static ArrayList<Integer> twopath(Graph g, int s, int t) {
		
		int[][] tab= null;
		
		
		
		
		
		return SeamCarving.Dijkstra(g, s, t);
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
		int[] precedent = new int[graph.vertices()+1];
		int [] p = new int[graph.vertices()+1];
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
