
public class Modelisation {

	
	public static void main(String[] args) {
			
		//SeamCarving sc = new SeamCarving();
		
		int[][] fin = SeamCarving.readpgm("ex1.pgm");

		int sautj=0, sauti=0;
		
		/*for (int i = 0; i < fin.length; i++) {
			for (int j = 0; j <fin[i].length; j++) {
				if (sautj == j) System.out.print(fin[i][j] + " ");
				else  System.out.println(fin[i][j]);
				sautj=j;
			}
		}*/
		
		SeamCarving.writepgm(fin, "tamere");
	}
}
