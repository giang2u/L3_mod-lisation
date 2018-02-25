package model;

public class TraitementLine {

	public static int[][] inverseTab(int[][] refTab){
		
		int [][] outTab = new int[refTab[0].length][refTab.length];
		int colonne = refTab[0].length;
		int ligne = refTab.length;

        for(int i=0; i<colonne; i++){
            for(int j=ligne - 1; j>=0; j--){
            	outTab[i][j] = refTab[j][i];
            }
        }
		return outTab;
	}
	
}
