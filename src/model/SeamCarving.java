package model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.*;
import java.util.*;

public class SeamCarving
{
	public static int[][] im;
	
	public static int[][] readpgm(String fn)
	{		
		try {
			System.out.println("NOM FICHIER " + fn);
			FileReader flot = new FileReader(fn);
			BufferedReader d = new BufferedReader(flot);
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
			//InputStream f = ClassLoader.getSystemClassLoader().getResourceAsStream(fn);
			//BufferedReader d = new BufferedReader(new InputStreamReader(f));

			FileReader flot = new FileReader(fn);
			BufferedReader d = new BufferedReader(flot);
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
			FileWriter fstream = new FileWriter(filename);
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
	
	public static void writeppm(int [][][] image, String filename) {
		try{

			int  width = image.length;
			int height = image[0].length ;
			FileWriter fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);
			
			/*------------------GESTIOn COLOR-------------------------*/
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
	
	// SUUUUUUUUUUUUUUUUUUUUUUUUURRRRRRRRRRRRRRRRRRRRRRRBBBBBBBBBBBBBBBAAAAAAAAAAAAAAALLLLLLLLLLLLLLEEEEE
		public static int[][] twopath(Graph g, int s, int t) {

			int largeur = g.getLargeur();
			int hauteur = g.getHauteur();
			// taille du graphe
			int newhauteur = hauteur*2 - 2;

			
			int tab[] = new int [newhauteur*largeur + 2];
			int compt = 1;


			int somme = 0;
			// Calcul de la somme des sommets avec leur chemin le plus court

			// point fictif debut vers la 1ere ligne tout est a 0
			for (int i = 0; i < 1; i++) {
				somme = 0;
				for (int j = 0; j < largeur ; j++) {
					tab[compt] = somme;
					compt++;
				}
			}




			// parcours de la 1ere ligne a celle dapres ou on garde encore les liaisons
			for (int i = 1; i < 2 ; i++) {

				for (int j = 0; j < largeur ; j++) {
					if (j == 0) {
						somme = tab[compt - largeur] + g.getEdge(compt - largeur, compt).cout() ;

						if  ( tab[compt - largeur +1]  + g.getEdge(compt - largeur + 1, compt).cout()  < somme) somme =  tab[compt - largeur + 1] +
								g.getEdge(compt - largeur + 1, compt).cout() ;
					}
					else if (j == largeur -1 ) {

						somme =  tab[compt - largeur] + g.getEdge(compt - largeur, compt).cout() ;
						if  ( tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout()< somme) somme =  tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout(); 


					} 

					else {
						somme =  tab[compt - largeur] + g.getEdge(compt - largeur, compt).cout() ;
						if  ( tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout() < somme) somme =  tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout(); 
						if  ( tab[compt - largeur + 1] + g.getEdge(compt - largeur + 1, compt).cout() < somme) somme =  tab[compt - largeur + 1] + g.getEdge(compt - largeur + 1, compt).cout(); 
					}
					tab[compt] = somme;


					compt++;


				}
			}


			// parcours du milieu ou tout est a 0
			for (int i = 2; i <= newhauteur - 2 ; i++) {

				for (int j = 0; j < largeur ; j++) {

					if ( i%2 == 1) {
						if (j == 0) {
							somme = tab[compt - largeur] + g.getEdge(compt - largeur, compt).cout() ;

							if  ( tab[compt - largeur +1]  + g.getEdge(compt - largeur + 1, compt).cout()  < somme) {
								somme =  tab[compt - largeur + 1] + g.getEdge(compt - largeur + 1, compt).cout() ;
							}
						}
						else if (j == largeur -1 ) {

							somme =  tab[compt - largeur] + g.getEdge(compt - largeur, compt).cout() ;
							if  ( tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout()< somme) {
								somme =  tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout(); 		   
							}
						} 
						else {
							somme =  tab[compt - largeur] + g.getEdge(compt - largeur, compt).cout() ;
							if  ( tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout() < somme) {
								somme =  tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout(); 
							}
							if  ( tab[compt - largeur + 1] + g.getEdge(compt - largeur + 1, compt).cout() < somme) {
								somme =  tab[compt - largeur + 1] + g.getEdge(compt - largeur + 1, compt).cout(); 
							}
						}
						tab[compt] = somme;
						compt++;
						
					}else {
						somme = tab[compt - largeur]+ g.getEdge(compt - largeur, compt).cout();

						tab[compt] = somme;
						compt++;
					}
				} 
			}

			// parcours de lavant derniere ligne  vers la derniere ligne 
			for (int i = newhauteur - 1; i < newhauteur; i++) {

				for (int j = 0; j < largeur ; j++) {
					if (j == 0) {

						somme = tab[compt - largeur] + g.getEdge(compt - largeur, compt).cout() ;

						if  ( tab[compt - largeur +1]  + g.getEdge(compt - largeur + 1, compt).cout()  < somme) {
							somme =  tab[compt - largeur + 1] +g.getEdge(compt - largeur + 1, compt).cout() ;
						}
					}
					else if (j == largeur -1 ) {

						somme =  tab[compt - largeur] + g.getEdge(compt - largeur, compt).cout() ;
						if  ( tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout()< somme) {
							somme =  tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout(); 
						}

					} 

					else {
						
						somme =  tab[compt - largeur] + g.getEdge(compt - largeur, compt).cout() ;

						if  ( (tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout() )< somme) {
							somme =  tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout(); 

						}
						if  ( (tab[compt - largeur + 1] + g.getEdge(compt - largeur + 1, compt).cout() ) < somme) {
							somme =  tab[compt - largeur + 1] + g.getEdge(compt - largeur + 1, compt).cout(); 

							}
					}
					tab[compt] = somme;


					compt++;
				}
			}

			// derniere ligne vers le dernier point fictif

			somme = 500000;

			for (int i = 0; i < largeur; i++ ) 
			{
				int add = tab[compt - largeur + i] + g.getEdge(compt - largeur + i, compt).cout();
				if (somme > add) { 
					tab[compt] = add; 
					somme = add;
				}
			}

			// 1er chemin le plus court
			ArrayList<Integer> suurb = SeamCarving.Dijkstra(g, s, t);
			compt = 0;


			// Inversion du chemin le plus court

			for (Edge e : g.edges()) {
				e.setCout(  e.cout() + tab[e.depart()] - tab[e.arrive()]);

				if (compt < suurb.size()-1 && e.depart() == suurb.get(compt) && e.getTo() == suurb.get(compt+1) ) {
					int dep = e.depart();
					e.setDepart(e.getTo());
					e.setTo(dep);
					compt++;
				}
			}
			// Dijsktra pour chercher le 2eme chemin le plus court


			ArrayList<Integer> suurb2 = SeamCarving.Dijkstra(g, s, t);

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

					boolean verif = diff > g.getLargeur() && suurb.get(j)% g.getLargeur() == 0 ;
					boolean verif2 = diff > g.getLargeur() + 1 && suurb.get(j)% g.getLargeur() != 0 ;
					if (verif || verif2 ) {
						int echange = suurb.get(j);
						suurb.set(j, suurb2.get(j));
						suurb2.set(j, echange);
					}
				}
				
				// tavleau qui stockera les bonnes positions sans les duplicata
				int[][] tabi = new int[2][ 4 + ( (suurb2.size() - 4) / 2)];
				
				// stocke les chemins des 2 dijkstra dans un tableau a 2 dimensions
				for (i = 0; i < 2; i++) {
					for (int j =0; j < 3; j++)  {
						if ( i == 0) {
							tabi[i][j] = suurb.get(j);
						}
						else {
							tabi[i][j] = suurb2.get(j);
						}
					}
				}
				
				

				int m = 0;

				// stockage dans le tableau des bonnes positions
				for (int p = 0; p < 2; p++) {
					m = 3;
					for (int k = 4; k < (suurb.size()); k+=2 ) {
						if (p == 0) tabi[p][m] = suurb.get(k);
						else tabi[p][m] = suurb2.get(k);
						m++;
					}
				}
				
				
				// on met dernier sommet fictif quon a cree
				tabi[0][tabi[0].length - 1] = suurb.get(suurb.size()-1);
				tabi[1][tabi[0].length - 1] = suurb2.get(suurb2.size()-1);
				

				
				// calcul des bonnes positions pour le chemin 2
							for (int p = 0; p < 2; p++) {
								for (int k = 1; k < tabi[0].length; k++ ) {

										if ( tabi[p][k] > g.getLargeur()*2 ) { 
											int nbLigneDuplique = 0;
											if (k == tabi[0].length - 1) {
												nbLigneDuplique =  (int) (g.getLargeur() *   (  (int)Math.ceil((double)tabi[p][k]/(double)g.getLargeur()-2.0)/2 ) );
											} else {
												nbLigneDuplique =  (int) (g.getLargeur() *   (  (int)Math.ceil((double)tabi[p][k]/(double)g.getLargeur()-1.0)/2 ) );
											}
											tabi[p][k] = tabi[p][k] -  nbLigneDuplique;
										}
								}
							}


			g.setLargeur(largeur);
			g.setHauteur(hauteur);
			return tabi;
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
