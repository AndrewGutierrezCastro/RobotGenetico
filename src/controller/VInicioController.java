package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.VentanaInicio;

 public class VInicioController extends ViewController{
	VentanaInicio ventana;
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		ventana.frame.setVisible(true);
		
	}
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	public VInicioController() {
		ventana = new VentanaInicio();
		ventana.setController(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "btnIniciar":
			System.out.println("Boton iniciar listo");
			break;

		default:
			break;
		}
	}


	
	
	
	
	
}
