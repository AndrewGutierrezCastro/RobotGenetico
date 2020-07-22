package gui;

import javax.swing.JFrame;
import controller.ViewController;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class VentanaInicio extends Ventana{

	public JFrame frame;
	public JButton btnIniciar;
	private int width = 500;
	private int height = 350;
	/**
	 * Create the application.
	 */
	
	public VentanaInicio() {
		frame = new JFrame();
		frame.setSize(width, height);
		
		btnIniciar = new JButton("Iniciar");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(305)
					.addComponent(btnIniciar, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(222)
					.addComponent(btnIniciar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	public void setController(ViewController pController) {
		//poner todoso los action listener aqui
		super.setController(pController);
		btnIniciar.addActionListener(controller);
		btnIniciar.setActionCommand("btnIniciar");
	}
}
