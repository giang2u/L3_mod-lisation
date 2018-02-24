package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import view.Vue;


public class Modele extends Observable {
	
	protected SeamCarving sc;
	protected Supprimer sp;
	protected ArrayList<Vue> vues;
	protected String inFileName,inFilePath, outFilePath;
	protected int nb_pixel = 50;
	

	public Modele(SeamCarving sc){
		this.sc = sc ;
		this.vues = new ArrayList<>();
		inFileName = "";
		inFilePath="";
		outFilePath="";
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
		String[] parts = s.split(".pgm");
		String part1 = parts[0]+ "_remove.pgm" ;
		this.inFileName = part1;
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
	
	public SeamCarving getSeamCarving(){
		return this.sc;
	}
	
	public String getoutFilePath(){
		return this.outFilePath;
	}
	
	public void setoutFilePath(String s){
		String[] parts = s.split(".pgm");
		String part1 = parts[0]+ "_remove.pgm" ;
		this.outFilePath = part1;
		
	}
	
	public void start(String pathFile, String fileOutName, int nombre_pixel){
		this.sp.supprimerPixel(pathFile, fileOutName, nombre_pixel);
	}
}
