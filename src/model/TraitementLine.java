package model;

public class TraitementLine {

	public int[][] inverseTab(){
		
		int [][] refTab = SeamCarving.im;
		int [][] outTab = new int[refTab[0].length][refTab.length];
		int colonne = refTab[0].length;
		int ligne = refTab.length;
		System.out.println("Colonne :" + refTab[0].length);
		System.out.println("Line : "+ refTab.length);

		
        int ii = 0;
        int jj = 0;
        for(int i=0; i<colonne; ++i){
            for(int j= ligne-1; j>=0; --j){
            	outTab[ii][jj] = refTab[i][j];
                jj++;
            }
            ii++;
        }
		return outTab;
	}
	
}
