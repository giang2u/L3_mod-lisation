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
		this.setPreferredSize(new Dimension(750, 500)) ;
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jp = new JPanel();
		SeamCarving sc = new SeamCarving();
		Modele m = new Modele(sc);
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
	   
	   	int[][] fin = SeamCarving.readpgm("test.pgm");		
		SeamCarving.writepgm(fin, "test1");
		int[][] outTAb = Interest.interest(fin);
		
		TraitementLine l = new TraitementLine();
		l.inverseTab();
		
		/*int largeur = outTAb[0].length;
		int hauteur = outTAb.length;

		


		Graph g = GraphTraitement.toGraph2(outTAb);
		
		
		Graph gg= GraphTraitement.suurballe(outTAb);
		
		
		
		gg.writeFile("graph.dot");
		
		
		Supprimer.supprimerPixelPPM("pbmlib.ppm","pbmlibout.ppm", 50);

		Supprimer.supprimerPixel2("test.pgm","test22.pgm",  1);

		
		Supprimer.supprimerPixel("test.pgm","feep23.pgm",  2);
		Supprimer.supprimerPixel2("test.pgm","feep22.pgm", 2);*/
	 }
}
