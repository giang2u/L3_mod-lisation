package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import view.Vue;


public class Modele extends Observable {
	protected ArrayList<Vue> vues;
	protected String inFileName,inFilePath, outFilePath;
	protected int nb_pixel = 50;
	protected boolean supL = false;
	protected boolean supC = true;
	protected String col;

	public Modele(){
		this.vues = new ArrayList<>();
		inFileName = "";
		inFilePath = "";
		outFilePath = "";
		col = "colonne";
	}


	public void ajouterVue(Vue v){
		this.vues.add(v);
	}
	
	public void setCol(String s){
		this.col = s;
	}
	
	public String getcol(){
		return this.col;
	}
	
	public void majVue(){
		for(Vue v : vues){
			v.maj();
		}
	}
	
	public String getInFileName() {
		return this.inFileName;
	}
	
	public void setInFileName(String s){
		if(s.contains("pgm")){
			String[] parts = s.split(".pgm");
			String part1 = parts[0]+ "_remove.pgm" ;
			this.inFileName = part1;
			
		}
		else{
			String[] parts = s.split(".ppm");
			String part1 = parts[0]+ "_remove.ppm" ;
			this.inFileName = part1;
		}
		majVue();
	}
	
	public void setInFilePath(String s){
		this.inFilePath = s;
	}
	
	public String getInFilePath(){
		return this.inFilePath;
	}
	
	public int getNb_pixel() {
		return nb_pixel;
	}

	public void setNb_pixel(int nb_pixel) {
		this.nb_pixel = nb_pixel;
	}
	

	public String getoutFilePath(){
		return this.outFilePath;
	}
	
	public void setoutFilePath(String s){
		String part1 ="";
		if(s.contains("pgm")){
			String[] parts = s.split(".pgm");
			part1 = parts[0]+ "_remove.pgm" ;
			this.outFilePath = part1;
		
		}
		else{
		String[] parts = s.split(".ppm");
			part1 = parts[0]+ "_remove.ppm" ;
			this.outFilePath = part1;
		}
		
	}
	
	public void addType(String s){
		String part1 ="";
		if(this.outFilePath.contains("pgm")){
			String[] parts = this.outFilePath.split(".pgm");
			part1 = parts[0]+ s+ ".pgm" ;
			this.outFilePath = part1;
			System.out.println(this.outFilePath);
		
		}
		else{
			String[] parts = this.outFilePath.split(".ppm");
			part1 = parts[0]+ s+ ".ppm" ;
			this.outFilePath = part1;
		}
	}
	
	public void start(String pathFile, String fileOutName, int nombre_pixel){

		majVue();
		if(pathFile.contains("pgm")){
			if(this.supC){
				setCol("Supression Colonne");
				System.out.println("colone");
				Supprimer.supprimerPixel(pathFile, fileOutName, nombre_pixel);
			}
			if(this.supL){
				setCol("Supression Ligne");
				System.out.println("ligne");
				Supprimer.supprimerPixelLine(pathFile, fileOutName, nombre_pixel);
			}
		}
		else{
			Supprimer.supprimerPixelPPM(pathFile, fileOutName, nombre_pixel);
		}
	}

	public void supprimeLine(boolean b) {
		this.supL = b;
		
	}

	public void supprimeColone(boolean b) {
		this.supC = b;
		
	}
	
	public boolean getSupL(){
		return this.supL;
	}
	public boolean getSupC(){
		return this.supC;
	}
}
