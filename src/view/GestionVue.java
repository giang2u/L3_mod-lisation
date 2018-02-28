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
	protected JButton valider;
	protected Modele m;
	protected String valueText;
	protected JComboBox jc;
	
	
	public GestionVue( Modele m){
		super();
		this.m = m;
		this.setLayout(new BorderLayout());
		
		this.lapelpixel = new JTextField("50",10);
		this.valider = new JButton("RUN!!!");
		String[] elements = new String[]{"Supression Colonne","Supression Ligne"};

		jc = new JComboBox<String>(elements);
		
		this.valider.addActionListener(new EcouteurRun(m,this.lapelpixel,jc));
		
		this.add(this.lapelpixel, BorderLayout.WEST);
		this.add(this.valider, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.add(this.jc);
		
		this.add(panel,BorderLayout.EAST);
		this.m.ajouterVue(this);
		
	}
	
	
	
	@Override
	public void maj() {
	}

}
