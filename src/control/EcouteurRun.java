package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Modele;

public class EcouteurRun implements ActionListener {
	protected Modele m;
	protected JTextField valeur;
	
	public EcouteurRun( Modele m, JTextField valeurZone){
		this.m = m;
		this.valeur = valeurZone;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(this.valeur.getText());
		try{
			int number = Integer.parseInt(this.valeur.getText());
			this.m.setNb_pixel(number);
			if(this.m.getoutFilePath() != null){
				this.m.start(this.m.getInFilePath(), this.m.getoutFilePath() ,this.m.getNb_pixel());
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
