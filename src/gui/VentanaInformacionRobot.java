package gui;

import javax.swing.JFrame;

import controller.ViewController;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Toolkit;

public class VentanaInformacionRobot extends Ventana{

	public JFrame frmInformacionRobot;
	public JTable tblRobot;
	public JPanel pnlTerreno;
	public JLabel lblImagenRobot;

	/**
	 * Create the application.
	 */
	public VentanaInformacionRobot() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInformacionRobot = new JFrame();
		frmInformacionRobot.getContentPane().setBackground(Color.CYAN);
		frmInformacionRobot.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInformacionRobot.class.getResource("/images/Robot.png")));
		frmInformacionRobot.setTitle("Informacion Robot");
		frmInformacionRobot.setForeground(Color.CYAN);
		frmInformacionRobot.setResizable(false);
		frmInformacionRobot.setBounds(100, 100, 699, 599);
		frmInformacionRobot.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmInformacionRobot.getContentPane().setLayout(null);
		
		lblImagenRobot = new JLabel("New label");
		lblImagenRobot.setBounds(497, 11, 128, 128);
		frmInformacionRobot.getContentPane().add(lblImagenRobot);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 141, 235, 280);
		frmInformacionRobot.getContentPane().add(scrollPane);
		
		tblRobot = new JTable();
		scrollPane.setViewportView(tblRobot);
		
		pnlTerreno = new JPanel();
		pnlTerreno.setBackground(Color.BLUE);
		pnlTerreno.setBounds(10, 21, 428, 508);
		frmInformacionRobot.getContentPane().add(pnlTerreno);
		pnlTerreno.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][]"));

	}

	@Override
	public void setController(ViewController controller) {
		super.setController(controller);
		tblRobot.setModel(new JTableModel());
		tblRobot.getModel().addTableModelListener(controller);
		
		
	}
}
