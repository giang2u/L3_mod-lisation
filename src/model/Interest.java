package model;

public class Interest {

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
}
