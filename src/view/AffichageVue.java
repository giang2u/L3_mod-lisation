package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Modele;
import model.SeamCarving;

public class AffichageVue extends JPanel implements Vue{

	public JLabel imageF;
	public Modele m;
	public ImageIcon image;
	
	public AffichageVue(Modele m){
		this.m = m;
		image = new ImageIcon("");
		imageF = new JLabel(image);
		this.add(imageF);
		
		this.m.ajouterVue(this);
	}

	@Override
	public void maj() {
		this.image = new ImageIcon(this.m.getInFilePath());
		this.imageF.setIcon(this.image);
	}
	
}
