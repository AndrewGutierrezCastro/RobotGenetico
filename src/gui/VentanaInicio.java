package gui;

import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Helpers;
import controller.ViewController;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

public class VentanaInicio extends Ventana{

	public JFrame frmSimulacionRobots;
	public JButton btnIniciar;
	private int width = 500;
	private int height = 350;
	public JTextField txtFieldSizePoblacion;
	public JTextField txtFieldEsperaRobots;
	public JSlider sldrMutacion;
	/**
	 * Create the application.
	 */
	
	public VentanaInicio() {
		frmSimulacionRobots = new JFrame();
		frmSimulacionRobots.setResizable(false);
		frmSimulacionRobots.setBackground(Color.BLACK);
		frmSimulacionRobots.setForeground(Color.LIGHT_GRAY);
		frmSimulacionRobots.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicio.class.getResource("/images/Robot.png")));
		frmSimulacionRobots.setTitle("Simulacion Robots");
		frmSimulacionRobots.setSize(259, 377);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmSimulacionRobots.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(513, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		btnIniciar = new JButton("Iniciar");
		btnIniciar.setBounds(59, 256, 113, 47);
		panel.add(btnIniciar);
		
		JLabel lblNewLabel_5 = new JLabel("Value: 20%");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBackground(Color.GRAY);
		lblNewLabel_5.setBounds(119, 52, 91, 14);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		
		sldrMutacion = new JSlider();
		sldrMutacion.setValue(20);
		sldrMutacion.setBounds(10, 68, 200, 31);
		panel.add(sldrMutacion);
		sldrMutacion.setBackground(Color.BLACK);
		
		JLabel lblNewLabel_1 = new JLabel("Porcentaje de mutacion");
		lblNewLabel_1.setBackground(new Color(0, 0, 139));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 32, 200, 14);
		panel.add(lblNewLabel_1);
		
		txtFieldEsperaRobots = new JTextField();
		txtFieldEsperaRobots.setBounds(167, 128, 35, 20);
		panel.add(txtFieldEsperaRobots);
		txtFieldEsperaRobots.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFieldEsperaRobots.setText("5");
		txtFieldEsperaRobots.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tiempo de espera Robots");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(9, 115, 193, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Tama\u00F1o de la poblacion");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(UIManager.getColor("Table.selectionBackground"));
		lblNewLabel_2.setBounds(10, 172, 200, 14);
		panel.add(lblNewLabel_2);
		
		txtFieldSizePoblacion = new JTextField();
		txtFieldSizePoblacion.setBounds(148, 202, 62, 20);
		panel.add(txtFieldSizePoblacion);
		txtFieldSizePoblacion.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFieldSizePoblacion.setText("26");
		txtFieldSizePoblacion.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("ms");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(206, 131, 24, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Imagen");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setBackground(new Color(0, 255, 0));
		lblNewLabel_4.setBounds(0, 0, 249, 342);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setIcon(Helpers.getImagenResized("Robot", ".png", lblNewLabel_4.getWidth(), lblNewLabel_4.getHeight()));
		
		JLabel lblFondo = new JLabel("Fondo");
		lblFondo.setBounds(0, 0, 251, 342);
		panel.add(lblFondo);
		lblFondo.setIcon(Helpers.getImagenResized("BLOQUEADO", ".jpg", lblFondo.getWidth(), lblFondo.getHeight()));
		sldrMutacion.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent e) {
	        	 lblNewLabel_5.setText("Value : " + ((JSlider)e.getSource()).getValue()+"%");
	         }
	      });
		frmSimulacionRobots.getContentPane().setLayout(groupLayout);
		
		
	}

	public void setController(ViewController pController) {
		//poner todoso los action listener aqui
		super.setController(pController);
		btnIniciar.addActionListener(controller);
		btnIniciar.setActionCommand("btnIniciar");
	}
}
