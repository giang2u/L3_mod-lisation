package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.EcouteurRun;

import model.Modele;
import model.SeamCarving;

public class GestionImageLink extends JPanel implements Vue {
	protected String pathImage, imageName;
	protected Modele m;
	protected JLabel inPathName, outPathName;
	protected JButton ouvrir;
	
	
	public GestionImageLink(final Modele m){
		super();
		this.m = m;
		this.setLayout(new BorderLayout());
		
		//traitement affichage  file
		this.inPathName = new JLabel("In File Path: ");
		this.outPathName = new JLabel("Out File Path: ");
		ouvrir = new JButton("Select image");
		ouvrir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final JFileChooser jf = new JFileChooser();
				final int reponse = jf.showOpenDialog(ouvrir);
				Thread t = new Thread(new Runnable(){
					public void run(){
						if (reponse == JFileChooser.APPROVE_OPTION){
							imageName = jf.getSelectedFile().getName();
							pathImage = jf.getSelectedFile().getAbsolutePath();
							m.setInFilePath(pathImage);
							m.setInFileName(imageName);
						}
					}
				});
				t.start() ;

			}
		});
		
		

		JPanel panel  = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		
		panel.add(this.inPathName);
		panel.add(this.outPathName);

		this.add(this.ouvrir, BorderLayout.WEST);
		this.add(panel,BorderLayout.EAST);
		this.m.ajouterVue(this);
		
	}


	@Override
	public void maj() {
		if(this.m.getInFilePath().contains("pgm") || this.m.getInFilePath().contains("ppm")){
			this.m.setoutFilePath(this.m.getInFilePath());
			inPathName.setText("In File Name: "+this.imageName);
			outPathName.setText("Out File Name: "+this.m.getInFileName());
		}
		else{
			JOptionPane.showMessageDialog(null,"Selection un fichier .pgm ou .ppm","Erreur select",JOptionPane.ERROR_MESSAGE);
		}
	}
}
