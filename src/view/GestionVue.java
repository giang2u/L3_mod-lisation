package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.EcouteurRun;

import model.Modele;

public class GestionVue extends JPanel implements Vue {

	protected JLabel pixel;
	protected JTextField lapelpixel;
	protected JButton valider;
	protected Modele m;
	protected String valueText;
	
	
	public GestionVue( Modele m){
		super();
		this.m = m;
		this.setLayout(new BorderLayout());
		
		this.lapelpixel = new JTextField("50",10);
		this.valider = new JButton("RUN!!!");
		this.valider.addActionListener(new EcouteurRun(m,this.lapelpixel));
		
		this.add(this.lapelpixel, BorderLayout.WEST);
		this.add(this.valider, BorderLayout.EAST);
		

		this.m.ajouterVue(this);
	}
	
	
	
	@Override
	public void maj() {
	}

}
