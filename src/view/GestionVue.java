package view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.EcouteurRun;

import model.Modele;

public class GestionVue extends JPanel implements Vue {

	protected JLabel pixel;
	protected JTextField lapelpixel;
	protected JButton valider, valider2;
	protected Modele m;
	protected String valueText;
	protected JComboBox jc;
	
	
	public GestionVue( Modele m){
		super();
		this.m = m;
		this.setLayout(new BorderLayout());
		
		this.lapelpixel = new JTextField("Nb pixel a DEL",10);
		this.valider = new JButton("RUN METHOD 1");
		this.valider2 = new JButton("RUN METHOD 2");
		String[] elements = new String[]{"Supression Colonne","Supression Ligne"};

		jc = new JComboBox<String>(elements);
		
		this.valider.addActionListener(new EcouteurRun(m,this.lapelpixel,jc, valider));
		this.valider2.addActionListener(new EcouteurRun(m,this.lapelpixel,jc, valider2));
		
		this.add(this.lapelpixel, BorderLayout.WEST);
		JPanel panel = new JPanel();
		panel.add(this.valider, BorderLayout.NORTH);
		panel.add(this.valider2, BorderLayout.SOUTH);
		this.add(panel,BorderLayout.CENTER);;
		this.add(jc,BorderLayout.EAST);
		this.m.ajouterVue(this);
		
	}
	
	
	
	@Override
	public void maj() {
	}

}
