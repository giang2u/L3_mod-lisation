package model;

import java.util.ArrayList;

public class GraphTraitement {

	public static Graph toGraph(int [][] itr) {

		int largeur = itr[0].length;
		int hauteur = itr.length;

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
		Graph a = new Graph(hauteur * largeur +2 + (  (hauteur - 2) * largeur ));

		int newhauteur = hauteur + hauteur - 2;

		int compteur = 0;


		// relie le sommet initial 0 a la premiere ligne
		for (int i =0 ; i < largeur; i++) {
			a.addEdge( new Edge(compteur, i + 1, 0) ); 
		}


		// creation des 2 ligne de sommets reliÃ©s par une arete de poids nul
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

		int ite = 1;

		// parcours du milieu du graphe et creation des duplications
		for (int i = 2; i < newhauteur; i++) {

			for (int j = 0; j < largeur; j++) {
				if (i%2 == 0) {
					a.addEdge( new Edge(compteur, compteur + largeur , 0 ) );
				} else {if (j == 0) {
					a.addEdge( new Edge(compteur, compteur + largeur, itr[ite][j] ) );
					a.addEdge( new Edge(compteur, compteur + largeur + 1, itr[ite][j] ) );
				}
				else if (j == largeur -1) {
					a.addEdge( new Edge(compteur, compteur + largeur - 1 , itr[ite][j] ) );
					a.addEdge( new Edge(compteur, compteur + largeur , itr[ite][j] ) );

				} 
				else {
					a.addEdge( new Edge(compteur, compteur + largeur - 1 , itr[ite][j] ) );
					a.addEdge( new Edge(compteur, compteur + largeur , itr[ite][j] ) );
					a.addEdge( new Edge(compteur, compteur + largeur + 1 , itr[ite][j] ) );
				}
				}
				compteur++;
			}
			if ( i%2 == 1) ite++;
		}


		// derniere ligne qui rejoint le sommet fin
		for (int i = 0; i < largeur; i++ ) {
			a.addEdge( new Edge(compteur + i, newhauteur * largeur + 1 , itr[hauteur-1][i]) );
		}
		a.setLargeur(largeur);
		a.setHauteur(hauteur);
		return a;
	}



	

}
