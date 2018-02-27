import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.GestionImageLink;
import view.GestionVue;

import model.Edge;
import model.Graph;
import model.GraphTraitement;
import model.Heap;
import model.Interest;
import model.Modele;
import model.SeamCarving;
import model.Supprimer;
import model.TraitementLine;



public class Modeli extends JFrame{
	static boolean visite[];
	public Modeli(){
		super("Projet Modelisation - Gestion Image PMG");
		this.setPreferredSize(new Dimension(900, 600)) ;
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jp = new JPanel();
		Modele m = new Modele();
		GestionImageLink fi = new GestionImageLink(m);
		GestionVue fo = new GestionVue(m);
		jp.add(fi,BorderLayout.WEST);
		jp.add(fo,BorderLayout.EAST);
		
		this.add(jp,BorderLayout.NORTH);
		pack() ;
		setVisible(true);
	}
   
   
   public static void dfs(Graph g, int u)
	 {
		visite[u] = true;
		System.out.println("Je visite " + u);
		for (Edge e: g.next(u))
		  if (!visite[e.getTo()])
			dfs(g,e.getTo());
	 }

   public static void testHeap()
	 {
		// Crée ue file de priorité contenant les entiers de 0 à 9, tous avec priorité +infty
		Heap h = new Heap(10);
		h.decreaseKey(3,1664);
		h.decreaseKey(4,5);
		h.decreaseKey(3,8);
		h.decreaseKey(2,3);
		// A ce moment, la priorité des différents éléments est:
		// 2 -> 3
		// 3 -> 8
		// 4 -> 5
		// tout le reste -> +infini
		int x=  h.pop();
		System.out.println("On a enlevé "+x+" de la file, dont la priorité était " + h.priority(x));
		x=  h.pop();
		System.out.println("On a enlevé "+x+" de la file, dont la priorité était " + h.priority(x));
		x=  h.pop();
		System.out.println("On a enlevé "+x+" de la file, dont la priorité était " + h.priority(x));
		// La file contient maintenant uniquement les éléments 0,1,5,6,7,8,9 avec priorité +infini
	 }
   
   public static void testGraph()
	 {
		int n = 5;
		int i,j;
		Graph g = new Graph(n*n+2);
		
		for (i = 0; i < n-1; i++)
		  for (j = 0; j < n ; j++)
			g.addEdge(new Edge(n*i+j, n*(i+1)+j, 1664 - (i+j)));

		for (j = 0; j < n ; j++)		  
		  g.addEdge(new Edge(n*(n-1)+j, n*n, 666));
		
		for (j = 0; j < n ; j++)					
		  g.addEdge(new Edge(n*n+1, j, 0));
		
		g.addEdge(new Edge(13,17,1337));
		g.writeFile("test.dot");
		// dfs à partir du sommet 3
		visite = new boolean[n*n+2];
		dfs(g, 3);
	 }
   
   public static void main(String[] args)
	 {
	   
	   	//int[][] fin = SeamCarving.readpgm("test.pgm");		

   

	   //new Modeli();
	   	/*int[][] fin = SeamCarving.readpgm("feep.pgm");	
		SeamCarving.writepgm(fin, "test1");
		int[][] outTAb = Interest.interest(fin);
		int largeur = outTAb[0].length;
		int hauteur = outTAb.length;
		


		//Graph g = GraphTraitement.toGraph2(outTAb);
		//g.writeFile("graph.dot"
		Graph g = GraphTraitement.toGraph2(outTAb);
		
		
		//Graph gg= GraphTraitement.suurballe(outTAb);
		

		//gg.writeFile("graph23.dot");
		//gg.writeFile("graph.dot");
		
		int newhau = outTAb.length*2 - 2;
		int largeurr = outTAb[0].length;
		//SeamCarving.twopath(gg, 0, newhau*largeurr + 1);
		
	   //Modeli model = new Modeli();
	   //int nb_pixel_del = Integer.valueOf(args[1]); // nb de fois on on veut supprimer le pixel

		//SeamCarving.supprimerPixel(args[0],args[0],  50);
		//SeamCarving.supprimerPixelPPM("poivron.ppm","poivronOut.ppm", 100);
		//Supprimer.supprimerPixelPPM("pbmlib.ppm","pbmlibout.ppm", 50);

		
		//int [][] tab = SeamCarving.twopath(gg, 0, gg.vertices() - 1);*/
		Supprimer.supprimerPixel("ex1.pgm","feep23.pgm",  200);
		Supprimer.supprimerPixel2("ex1.pgm","feep22.pgm", 100);
		//Supprimer.supprimerPixelLine("ex1.pgm","ex1ligne.pgm",  100);
		//Supprimer.supprimerPixel("ex1.pgm","ex1colone.pgm",  100);
		
	 }
}
