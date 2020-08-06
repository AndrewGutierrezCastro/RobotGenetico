package gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import controller.ViewController;
import model.Generacion;
import model.Robot;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;

public class VentanaPrincipal extends Ventana{

	public JFrame frame;
	public JPanel pnlTerreno;
	private int width = 760;
	private int height = 670;
	private JButton btnIniciarSimulacion;
	private JButton btnPausaSimulacion;
	public JComboBox<Generacion> cmbGeneracion;
	public JList<Robot> lstRobots;
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
		frame.setSize(970, 670);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		pnlTerreno = new JPanel();
		pnlTerreno.setBounds(424, 11, 520, 610);
		panel.add(pnlTerreno);
		pnlTerreno.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][]"));
		
		btnIniciarSimulacion = new JButton("Iniciar Simulacion");
		btnIniciarSimulacion.setBounds(10, 545, 136, 23);
		panel.add(btnIniciarSimulacion);
		
		btnPausaSimulacion = new JButton("Pausa");
		btnPausaSimulacion.setBounds(10, 579, 108, 42);
		panel.add(btnPausaSimulacion);
		
		cmbGeneracion = new JComboBox<Generacion>();
		cmbGeneracion.setBounds(206, 45, 194, 20);
		panel.add(cmbGeneracion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 76, 380, 449);
		panel.add(scrollPane);
		
		lstRobots = new JList<Robot>();
		scrollPane.setViewportView(lstRobots);
		
		
	}
	public void setController(ViewController pController) {
		//poner todoso los action listener aqui
		super.setController(pController);
		btnIniciarSimulacion.addActionListener(controller);
		btnIniciarSimulacion.setActionCommand("IniciarSimulacion");
		
		btnPausaSimulacion.addActionListener(controller);
		btnPausaSimulacion.setActionCommand("PausarSimulacion");
		
		lstRobots.addListSelectionListener(controller);
		
		cmbGeneracion.addActionListener(controller);
		cmbGeneracion.setActionCommand("CmbGeneracion");
	}
}
