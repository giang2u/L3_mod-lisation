import java.util.ArrayList;
import java.io.*;
import java.util.*;
public class SeamCarving
{

   public static int[][] readpgm(String fn)
	 {		
        try {
            InputStream f = ClassLoader.getSystemClassLoader().getResourceAsStream(fn);
            BufferedReader d = new BufferedReader(new InputStreamReader(f));
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
		   int[][] im = new int[height][width];
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

    public static void main (String[] args) {
	

    }

    public static void writepgm(int [][] image, String filename) {


	try{
		
		int  width = image.length;
		int height = image[0].length;
	    //specify the name of the output..
	    FileWriter fstream = new FileWriter(filename+".pgm");
	    //we create a new BufferedWriter
	    BufferedWriter out = new BufferedWriter(fstream);
	    //we add the header, 128 128 is the width-height and 63 is the max value-1 of ur data
	    out.write("P2\n# CREATOR: MOI XV Version 3.10a  Rev: 12/29/94\n"+height+ " "+ width +"\n255\n");
	   
	    //2 loops to read the 2d array

		   for(int i = 0 ; i<width;i++)
	    for(int j = 0 ; j<height;j++)
		
		    //we write in the output the value in the position ij of the array
		    out.write(image[i][j]+" ");
	    //we close the bufferedwritter
	    out.close();
	}
	catch (Exception e){
	    System.err.println("Error : " + e.getMessage());
	}

    }

   
}
