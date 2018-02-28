package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Modele;

public class EcouteurRun implements ActionListener {
	protected Modele m;
	protected JTextField valeur;
	protected JComboBox s;
	
	public EcouteurRun( Modele m, JTextField valeurZone, JComboBox s){
		this.m = m;
		this.valeur = valeurZone;
		this.s = s;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			int number = Integer.parseInt(this.valeur.getText());
			this.m.setNb_pixel(number);
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null,"SVP enter un nombre","Erreur saisir",JOptionPane.ERROR_MESSAGE);
		}
		String cheminSortie ="";
		boolean estCol = true;
		if(s.getSelectedItem().toString().equals("Supression Colonne")){
			if(this.m.getInFilePath().contains("pgm")){
				String[] parts = this.m.getInFilePath().split(".pgm");
				cheminSortie = parts[0]+ "DelCol.pgm" ;
			}
			else{
				String[] parts = this.m.getInFilePath().split(".ppm");
				cheminSortie = parts[0]+ "DelCol.ppm" ;
			}
			estCol = true;
		}
		if(s.getSelectedItem().toString().equals("Supression Ligne")){
			if(this.m.getInFilePath().contains("pgm")){
				String[] parts = this.m.getInFilePath().split(".pgm");
				cheminSortie = parts[0]+ "DelLig.pgm" ;
				estCol = false;
			}
		}
		if(this.m.getInFilePath() != ""){
			this.m.start(this.m.getInFilePath(), cheminSortie ,this.m.getNb_pixel(), estCol);
			JOptionPane.showMessageDialog(null,"Supression Reussir","Correct",JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null,"Select un fichier","Erreur saisir",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
