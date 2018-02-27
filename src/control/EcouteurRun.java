package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Modele;

public class EcouteurRun implements ActionListener {
	protected Modele m;
	protected JTextField valeur;
	protected String s;
	
	public EcouteurRun( Modele m, JTextField valeurZone, String s){
		this.m = m;
		this.valeur = valeurZone;
		this.s = s;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			
			int number = Integer.parseInt(this.valeur.getText());
			this.m.setNb_pixel(number);
			if(this.m.getoutFilePath() != ""){
				System.out.println(this.m.getoutFilePath());
				if(this.m.getSupC()){
			    	m.addType("_Colonne");
				}
				if(this.m.getSupL()){
					m.addType("_Ligne");
				}
				System.out.println(this.m.getoutFilePath());
				this.m.start(this.m.getInFilePath(), this.m.getoutFilePath() ,this.m.getNb_pixel());
				JOptionPane.showMessageDialog(null,"Supression Reussir","Correct",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null,"Select un fichier","Erreur saisir",JOptionPane.ERROR_MESSAGE);
			}
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null,"SVP enter un nombre","Erreur saisir",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
