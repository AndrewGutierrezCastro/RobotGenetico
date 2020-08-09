package gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import controller.ViewController;
import model.Generacion;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class VentanaPrincipal extends Ventana{

	public JFrame frame;
	public JPanel pnlTerreno;
	private int width = 760;
	private int height = 670;
	private JButton btnIniciarSimulacion;
	private JButton btnPausaSimulacion;
	public JComboBox<Generacion> cmbGeneracion;
	public JTable tblRobots;
	public JButton btnNuevaGeneracion;
	public JButton btnVerInfoRobot;
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
		frame.setForeground(UIManager.getColor("TextArea.foreground"));
		frame.setBackground(new Color(64, 64, 64));
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1040, 670);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaptionText);
		panel.setForeground(UIManager.getColor("TableHeader.foreground"));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		pnlTerreno = new JPanel();
		pnlTerreno.setBackground(new Color(0, 0, 0));
		pnlTerreno.setBounds(504, 11, 520, 610);
		panel.add(pnlTerreno);
		pnlTerreno.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][]"));
		
		btnIniciarSimulacion = new JButton("Iniciar Simulacion");
		btnIniciarSimulacion.setBounds(10, 545, 136, 23);
		panel.add(btnIniciarSimulacion);
		
		btnPausaSimulacion = new JButton("Pausa");
		btnPausaSimulacion.setBounds(10, 579, 108, 42);
		panel.add(btnPausaSimulacion);
		
		cmbGeneracion = new JComboBox<Generacion>();
		cmbGeneracion.setBounds(276, 45, 194, 20);
		panel.add(cmbGeneracion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 76, 450, 450);
		panel.add(scrollPane);
		
		tblRobots = new JTable();

		scrollPane.setViewportView(tblRobots);
		
		btnNuevaGeneracion = new JButton("Nueva Generacion");
		btnNuevaGeneracion.setBounds(321, 607, 149, 23);
		panel.add(btnNuevaGeneracion);
		
		btnVerInfoRobot = new JButton("Ver Info");
		btnVerInfoRobot.setBounds(375, 573, 95, 23);
		panel.add(btnVerInfoRobot);
		
		
	}
	public void setController(ViewController pController) {
		//poner todoso los action listener aqui
		super.setController(pController);
		btnIniciarSimulacion.addActionListener(controller);
		btnIniciarSimulacion.setActionCommand("IniciarSimulacion");
		
		btnPausaSimulacion.addActionListener(controller);
		btnPausaSimulacion.setActionCommand("PausarSimulacion");
		tblRobots.setModel(new JTableModel());
		tblRobots.getModel().addTableModelListener(controller);
		
		cmbGeneracion.addActionListener(controller);
		cmbGeneracion.setActionCommand("CmbGeneracion");
		
		btnNuevaGeneracion.addActionListener(controller);
		btnNuevaGeneracion.setActionCommand("CrearNuevaGeneracion");
		
		btnVerInfoRobot.addActionListener(controller);
		btnVerInfoRobot.setActionCommand("VerInfo");
	}
}
