package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Modele;

public class AffichageVue extends JPanel implements Vue{

	public JLabel imageF;
	public Modele m;
	public ImageIcon image;
	
	public AffichageVue(Modele m){
		this.m = m;
		image = new ImageIcon("C:\\Users\\User\\Documents\\ex1.pgm");
		imageF = new JLabel(image);
		this.add(imageF);
		this.m.ajouterVue(this);
	}

	@Override
	public void maj() {
	}
	
}
