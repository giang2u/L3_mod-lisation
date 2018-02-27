package view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.text.NumberFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import control.EcouteurRun;

import model.Modele;

public class GestionVue extends JPanel implements Vue {

	protected JLabel pixel;
	protected JTextField lapelpixel;
	protected JButton valider;
	protected Modele m;
	protected String valueText;
	protected JRadioButton colonnee;
	protected JRadioButton ligne;
	protected ButtonGroup group; 
	
	
	public GestionVue( Modele m){
		super();
		this.m = m;
		this.setLayout(new BorderLayout());
		
		this.lapelpixel = new JTextField("50",10);
		this.valider = new JButton("RUN!!!");
		this.colonnee = new JRadioButton("Supression Colone");
		this.ligne = new JRadioButton("Supression Ligne");
		this.valider.addActionListener(new EcouteurRun(m,this.lapelpixel));
		
		this.add(this.lapelpixel, BorderLayout.WEST);
		this.add(this.valider, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		group = new ButtonGroup();
		panel.add(this.colonnee);
		panel.add(this.ligne);
		
		group.add(this.ligne);
		group.add(this.colonnee);
		this.add(panel,BorderLayout.EAST);
		this.m.ajouterVue(this);
		
	}
	
	
	
	@Override
	public void maj() {
		if(this.m.getInFileName().contains("pgm")){
			this.ligne.setEnabled(true);
			if(this.colonnee.isSelected()){
				this.m.supprimeColone(true);
				this.m.supprimeLine(false);
			}
			else{
				this.m.supprimeLine(true);
				this.m.supprimeColone(false);
			}
		}
		else{
			this.ligne.setEnabled(false);
		}
	}

}
