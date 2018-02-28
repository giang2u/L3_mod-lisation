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
	protected int nb_pixel;
	protected boolean supL = false;
	protected boolean supC = true;

	public Modele(){
		this.vues = new ArrayList<>();
		inFileName = "";
		inFilePath = "";
		outFilePath = "";
		nb_pixel = 0;
	}


	public void ajouterVue(Vue v){
		this.vues.add(v);
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
	
	
	public void start1(String pathFile, String fileOutName, int nombre_pixel, boolean estCol){
		if(pathFile.contains("pgm")){
			if(estCol){
				Supprimer.supprimerPixel(pathFile, fileOutName, nombre_pixel);
			}
			else{
				Supprimer.supprimerPixelLine(pathFile, fileOutName, nombre_pixel);
			}
		}
		else{
			Supprimer.supprimerPixelPPM(pathFile, fileOutName, nombre_pixel);
		}
	}
	
	public void start2(String pathFile, String fileOutName, int nombre_pixel, boolean estCol){
		if(pathFile.contains("pgm")){
			if(estCol){
				Supprimer.supprimerPixel2(pathFile, fileOutName, nombre_pixel);
			}
			else{
				//Supprimer.supprimerPixelLine(pathFile, fileOutName, nombre_pixel);
			}
		}
		else{
			//Supprimer.supprimerPixelPPM(pathFile, fileOutName, nombre_pixel);
		}
	}

}
