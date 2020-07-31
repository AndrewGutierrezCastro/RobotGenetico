package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.ViewController;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;

public class VentanaPrincipal extends Ventana{

	public JFrame frame;
	public JPanel pnlTerreno;
	private int width = 760;
	private int height = 670;
	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		pnlTerreno = new JPanel();
		pnlTerreno.setBounds(214, 11, 520, 609);
		panel.add(pnlTerreno);
		pnlTerreno.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][]"));
		
		
	}
	public void setController(ViewController pController) {
		//poner todoso los action listener aqui
		super.setController(pController);
	}
}
