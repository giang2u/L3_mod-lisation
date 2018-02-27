package model;

import java.util.ArrayList;

public class GraphTraitement {

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

		//---------------------------
		/*
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
		   }*/


		// derniere ligne qui rejoint le sommet fin
		for (int i = 0; i < largeur; i++ ) {
			a.addEdge( new Edge(compteur + i, newhauteur * largeur + 1 , itr[hauteur-1][i]) );
		}
		// System.out.println(" hauteur " + hauteur + " largeur  " + largeur);
		a.setLargeur(largeur);
		return a;
	}



	// SUUUUUUUUUUUUUUUUUUUUUUUUURRRRRRRRRRRRRRRRRRRRRRRBBBBBBBBBBBBBBBAAAAAAAAAAAAAAALLLLLLLLLLLLLLEEEEE
	public static int[][] suurballe(int [][] itr){
		Graph g = GraphTraitement.toGraph2(itr);

		boolean bool = true;
		int largeur = itr[0].length;
		int hauteur = itr.length;
		// taille du graphe
		int newhauteur = hauteur*2 - 2;

		//System.out.println( "LARGEUR "+ largeur + "   hauteur " + hauteur);


		//int sb[][] = new int [newhauteur][largeur];
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
				/*System.out.println(" ALED " + i + " " + j);
					somme = SeamCarving.coutDijkstra(g, 0, compt);
					sb[i][j] = somme;
					tab[compt] = somme;
					compt++;*/
				if (j == 0) {
					//somme =  itr[hauteur - 2][j] + g.getEdge(compt - largeur, compt).cout() ;
					somme = tab[compt - largeur] + g.getEdge(compt - largeur, compt).cout() ;

					if  ( tab[compt - largeur +1]  + g.getEdge(compt - largeur + 1, compt).cout()  < somme) somme =  tab[compt - largeur + 1] +
							g.getEdge(compt - largeur + 1, compt).cout() ;
				}
				else if (j == largeur -1 ) {

					somme =  tab[compt - largeur] + g.getEdge(compt - largeur, compt).cout() ;
					if  ( tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout()< somme) somme =  tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout(); 


				} 

				else {
					// int ml = compt - largeur;
					//System.out.println(" precedent " + itr[hauteur - 1][j ]  +  " actuel " + compt );
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
						//somme =  itr[hauteur - 2][j] + g.getEdge(compt - largeur, compt).cout() ;
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
						// int ml = compt - largeur;
						//System.out.println(" precedent " + itr[hauteur - 1][j ]  +  " actuel " + compt );
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
					/*
					 */
				}else {
					somme = tab[compt - largeur]+ g.getEdge(compt - largeur, compt).cout();

					//sb[i-1][j] = somme;
					tab[compt] = somme;
					compt++;
				}
			} 
		}

		// parcours de lavant derniere ligne  vers la derniere ligne 
		for (int i = newhauteur - 1; i < newhauteur; i++) {

			for (int j = 0; j < largeur ; j++) {
				if (j == 0) {

					//somme =  itr[hauteur - 2][j] + g.getEdge(compt - largeur, compt).cout() ;
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
					// int ml = compt - largeur;

					somme =  tab[compt - largeur] + g.getEdge(compt - largeur, compt).cout() ;

					if  ( (tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout() )< somme) {
						somme =  tab[compt - largeur - 1] + g.getEdge(compt - largeur - 1, compt).cout(); 

						/*  
							   if (compt == 272) {
								   System.out.println( " aaaaaaaaaaaaaaaha "  + somme + "     " + compt) ;
								   System.out.println(" precedent " + (compt - largeur + 1)  +  " somme  " + (tab[compt - largeur + 1] + g.getEdge(compt - largeur + 1, compt).cout() ) + " actuel " + compt );
							   }*/
					}
					if  ( (tab[compt - largeur + 1] + g.getEdge(compt - largeur + 1, compt).cout() ) < somme) {
						somme =  tab[compt - largeur + 1] + g.getEdge(compt - largeur + 1, compt).cout(); 

						//System.out.println( " aaaaaaaaaaaaaaaha "  + somme + "     " + compt) ;
					}
				}
				//sb[i][j] = somme;
				tab[compt] = somme;


				compt++;
			}
		}

		// derniere ligne vers le dernier point fictif

		somme = 500000;

		for (int i = 0; i < largeur; i++ ) 
		{
			//int haut = hauteur * largeur + i + 1;
			int add = tab[compt - largeur + i] + g.getEdge(compt - largeur + i, compt).cout();
			if (somme > add) { 
				tab[compt] = add; 
				//System.out.println("   " +add );
				somme = add;
			}
		}

		// 1er chemin le plus court
		ArrayList<Integer> suurb = SeamCarving.Dijkstra(g, 0, newhauteur * largeur + 1 );
		compt = 0;


		// Inversion du chemin le plus court

		for (Edge e : g.edges()) {
			e.setCout(  e.cout() + tab[e.depart()] - tab[e.arrive()]);

			//	if ( e.cout() == -2)System.out.println(" cout " + tab[e.depart()] + " depart " + e.depart() + " arrive " + e.arrive() + "  cout " + tab[e.arrive()] + "   length " + tab.length);

			if (compt < suurb.size()-1 && e.depart() == suurb.get(compt) && e.getTo() == suurb.get(compt+1) ) {
				int dep = e.depart();
				e.setDepart(e.getTo());
				e.setTo(dep);
				compt++;
			}
		}
		//for (Edge e : g.edges()) System.out.println("  " + e.cout() + "  " + e.depart() + "  " + e.arrive());

		// Dijsktra pour chercher le 2eme chemin le plus court

		//System.out.println(" ALED ");

		ArrayList<Integer> suurb2 = SeamCarving.Dijkstra(g, 0,  newhauteur * largeur + 1);

		//System.out.println(" ALED 2");

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


		/*
			suurb = SeamCarving.Dijkstra(g, 0,  newhauteur * largeur + 1);

			ArrayList<Integer> cout = new ArrayList<Integer>();

			for (i= 0; i < suurb.size() - 1; i++){

				//inversion

				Edge e = g.getEdge(suurb.get(i),  suurb.get(i+1));

				int dep = e.depart();
				e.setDepart(e.getTo());
				e.setTo(dep);

				}



			suurb2 = SeamCarving.Dijkstra(g, 0,  newhauteur * largeur + 1);

			for (i= 0; i < suurb.size() - 1; i++){

				//rinversion
				Edge e = g.getEdge(suurb.get(i+1),  suurb.get(i));

				int dep = e.depart();
				e.setDepart(e.getTo());
				e.setTo(dep);
			}


*/
			System.out.print("\nCHEMIN 1 SUrb AV fefskedf :");
			for (Integer p: suurb)  System.out.print("   " + p);
/*
			 System.out.print("\nCHEMIN 2 dsiyfsfkudsf :");
			for (Integer p: suurb2)  System.out.print("   " + p);
		 		*/

			
			// transfert des bonnes aretes
			for (int j = 1; j < suurb.size(); j++) {
				int diff = Math.abs( suurb2.get(j-1) - suurb2.get(j) ) ;

				boolean verif = diff > itr[0].length && suurb.get(j)% itr[0].length == 0 ;
				boolean verif2 = diff > itr[0].length + 1 && suurb.get(j)% itr[0].length != 0 ;
				//System.out.print("\n" +verif +  " VERIF  " + verif2 + "    veriff " + ( verif || verif2 ) );
				if (verif || verif2 ) {
					int echange = suurb.get(j);
					suurb.set(j, suurb2.get(j));
					suurb2.set(j, echange);
				}
			}
			
			/*
			System.out.print("\nCHEMIN 1 SUrb AP fefskedf :");
			for (Integer p: suurb)  System.out.print("   " + p);

			 System.out.print("\nCHEMIN 2 dsiyfsfkudsf :");
			for (Integer p: suurb2)  System.out.print("   " + p);
		 */
			
			//int[][] tabi = new int[2][suurb2.size() ];
			
			int[][] tabi = new int[2][ 4 + ( (suurb2.size() - 4) / 2)];
			
			//System.out.println( "\n TABI " + ( 4 + ( (suurb2.size() - 4) / 2)));
			
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

			for (int p = 0; p < 2; p++) {
				m = 3;
				for (int k = 4; k < (suurb.size()); k+=2 ) {
					if (p == 0) tabi[p][m] = suurb.get(k);
					else tabi[p][m] = suurb2.get(k);
					m++;
				}
			}
			
			tabi[0][tabi[0].length - 1] = suurb.get(suurb.size()-1);
			tabi[1][tabi[0].length - 1] = suurb2.get(suurb2.size()-1);
			
/*
			for (int p = 0; p < 2; p++) {
				System.out.print("\n INIT  ");
				for (int k = 0; k < tabi[0].length; k++ ) {
					System.out.print("   " + tabi[p][k]);
				}
			}*/
			
			// calcul des bonnes positions pour le chemin 2
						for (int p = 0; p < 2; p++) {
							//System.out.print(" \nVALEUR TABLEAU ");
							for (int k = 1; k < tabi[0].length; k++ ) {

								//if ( p == 1) {
									if ( tabi[p][k] > g.getLargeur()*2 ) { 
										int nbLigneDuplique = 0;
										if (k == tabi[0].length - 1) {
											nbLigneDuplique =  (int) (g.getLargeur() *   (  (int)Math.ceil((double)tabi[p][k]/(double)g.getLargeur()-2.0)/2 ) );
										} else {
											nbLigneDuplique =  (int) (g.getLargeur() *   (  (int)Math.ceil((double)tabi[p][k]/(double)g.getLargeur()-1.0)/2 ) );
										}
										if (tabi[p][k]%g.getLargeur() == 1) {
											//nbDePixelSupprAvant = ((tab[p][k]/g.getLargeur()) -1 )/2 - 1;
										}
										tabi[p][k] = tabi[p][k] -  nbLigneDuplique;// - nbDePixelSupprAvant; 
									}
								//}
								//System.out.print("  "+  tabi[p][k] );
							}
						}


		g.setLargeur(largeur);
		return tabi;
	}

}
