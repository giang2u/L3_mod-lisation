package model;

import java.util.ArrayList;

import javax.lang.model.type.IntersectionType;

public class Supprimer {
	
	public static void supprimerPixel2(String filename,String outfile, int iteration) {
		
		//filename += "2";
		
		//int[][] fin = SeamCarving.readpgm(filename+".pgm");	
		int[][] fin = SeamCarving.readpgm(filename);	
		int[][] outTAb = Interest.interest(fin);
		Graph g = GraphTraitement.suurballe(outTAb);
		int [][] test = SeamCarving.twopath(g, 0, fin.length*fin[0].length + 1);
		ArrayList<Integer> list = new ArrayList<>(), list2 = new ArrayList<>();
		
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < test[0].length; j++) {
				if (i == 0) list.add(test[i][j]);
				else list2.add(test[i][j]);
			}
		}
		
		
		int  width = outTAb[0].length;
		int height = outTAb.length;
		
		int[][] tab = new int[fin.length][fin[0].length-2] ;
		
		int indicePixelASuppr = 1;
		
		int indicePixelASuppr2 = 1;
		
		
		// 1ere boucle
		
		for(int i = 0 ; i<tab.length;i++) {
			int z = 0;
			for(int j = 0 ; j<tab[0].length ;j++) {
				System.out.println(" JE BLOQUE ");
				
				int longu = j+1;
				
				if (list.get(indicePixelASuppr) == (  (i* outTAb[0].length )  + longu)  )  {
					indicePixelASuppr++;
					z++; tab[i][j] = fin[i][z]; 
				} else if ( list2.get(indicePixelASuppr2) == (  (i* outTAb[0].length )  + longu) ) {
					indicePixelASuppr2++;
					z++; tab[i][j] = fin[i][z]; 
				}
				else tab[i][j] = fin[i][z];
				
				z++;
			}
		}
		
		
		// reste des iterations
		
		for (int k = 0; k < (iteration/2  - 1) ; k++) {
			System.out.println(" JE FAIS LA " + k + " eme * 2 iteration");
			indicePixelASuppr = 1;

			indicePixelASuppr2 = 1;
			
			
			fin = tab;
			outTAb = Interest.interest(fin);
			//g = SeamCarving.toGraph(outTAb);
			
			
			g = GraphTraitement.suurballe(outTAb);
			test = SeamCarving.twopath(g, 0, fin.length*fin[0].length + 1);
			
			for (int i = 0; i < test.length; i++) {
				for (int j = 0; j < test[0].length; j++) {
					if (i == 0) list.add(test[i][j]);
					else list2.add(test[i][j]);
				}
			}
			
			tab = new int[fin.length][fin[0].length-2] ;

			for(int i = 0 ; i<tab.length;i++) {
				int z = 0;
				for(int j = 0 ; j<tab[0].length ;j++) {
					
					
					int longu = j+1;
					
					if (list.get(indicePixelASuppr) == (  (i* outTAb[0].length )  + longu)  )  {
						indicePixelASuppr++;
						 z++; tab[i][j] = fin[i][z]; 
					} else if ( list2.get(indicePixelASuppr2) == (  (i* outTAb[0].length )  + longu) ) {
						indicePixelASuppr2++;
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
	
public static void supprimerPixel(String filename,String outfile, int iteration) {
		
		//filename += "2";
		
		//int[][] fin = SeamCarving.readpgm(filename+".pgm");	
		int[][] fin = SeamCarving.readpgm(filename);	
		int[][] outTAb = Interest.interest(fin);
		Graph g = GraphTraitement.toGraph(outTAb);
		ArrayList<Integer> list = SeamCarving.Dijkstra(g, 0, fin.length*fin[0].length + 1);
		
		
		int  width = outTAb[0].length;
		int height = outTAb.length;
		
		int[][] tab = new int[fin.length][fin[0].length-1] ;
		
		int indicePixelASuppr = 1;
		
		
		
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
			outTAb = Interest.interest(fin);
			g = GraphTraitement.toGraph(outTAb);
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
	
	
public static void supprimerPixelPPM(String filename,String outfile, int iteration) {
		
		//filename += "2";
		
		//int[][] fin = SeamCarving.readpgm(filename+".pgm");	
		int[][][] fin = SeamCarving.readppm(filename);	
		int[][] outTAb = Interest.interestPPM(fin);
		Graph g = GraphTraitement.toGraph(outTAb);
		ArrayList<Integer> list = SeamCarving.Dijkstra(g, 0, fin.length*fin[0].length + 1);
		
		
		int  width = outTAb[0].length;
		int height = outTAb.length;
		
		int[][][] tab = new int[fin.length][fin[0].length-1][3] ;
		
		int indicePixelASuppr = 1;
			
		for(int i = 0 ; i<tab.length;i++) {
			int z = 0;
			for(int j = 0 ; j<tab[0].length ;j++) {
				
				
				int longu = j+1;
				
				if (list.get(indicePixelASuppr) == (  (i* outTAb[0].length )  + longu)  )  {
					indicePixelASuppr++;
					z++; 
					 tab[i][j][0] = fin[i][z][0]; 
					 tab[i][j][1] = fin[i][z][1]; 
					 tab[i][j][2] = fin[i][z][2]; 
				} 
				else {
					 tab[i][j][0] = fin[i][z][0]; 
					 tab[i][j][1] = fin[i][z][1]; 
					 tab[i][j][2] = fin[i][z][2];
					 
				}
				System.out.println(tab[i][j][0] + 
					 tab[i][j][1]+
					 tab[i][j][2] + "\n" );
				z++;
			}
		}
		
		
		for (int k = 0; k < iteration - 1; k++) {
			indicePixelASuppr = 1;
			
			fin = tab;
			outTAb = Interest.interestPPM(fin);
			g = GraphTraitement.toGraph(outTAb);
			list = SeamCarving.Dijkstra(g, 0, fin.length*fin[0].length + 1);
			 
			tab = new int[fin.length][fin[0].length-1][3] ;

			for(int i = 0 ; i<tab.length;i++) {
				int z = 0;
				for(int j = 0 ; j<tab[0].length ;j++) {
					int longu = j+1;
					if (list.get(indicePixelASuppr)==((i*outTAb[0].length)+longu)){
						indicePixelASuppr++;
						 z++; 
						 tab[i][j][0] = fin[i][z][0]; 
						 tab[i][j][1] = fin[i][z][1]; 
						 tab[i][j][2] = fin[i][z][2]; 
					} 
					else {
						 tab[i][j][0] = fin[i][z][0]; 
						 tab[i][j][1] = fin[i][z][1]; 
						 tab[i][j][2] = fin[i][z][2]; 
					}
					z++;
				}
			}
			
		}
		//outfile = "reduction_"+outfile;
		SeamCarving.writeppm(tab, outfile);
		System.out.println("DONE!!!!");
		
	}

}