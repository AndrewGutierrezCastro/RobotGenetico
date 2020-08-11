package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;

import Application.App;
import gui.VentanaInicio;
import model.Poblacion;
import model.Simulacion;

 public class VInicioController extends ViewController{
	VentanaInicio ventana;
	
	@Override
	public void show() {
		ventana.frmSimulacionRobots.setVisible(true);
		
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
		switch (e.getActionCommand()) {
		case "btnIniciar":
			Poblacion.getInstance().SizePoblacion = Integer.valueOf(ventana.txtFieldSizePoblacion.getText());
			Poblacion.getInstance().tiempoEspera = Integer.valueOf(ventana.txtFieldEsperaRobots.getText());
			Poblacion.getInstance().probabilidadMutacion = Double.valueOf(ventana.sldrMutacion.getValue()) / 100;
			App.controllerMap.put("VPRINCIPALCONTROLLER", new VPrincipalController());
			App.controllerMap.get("VPRINCIPALCONTROLLER").show();	
			ventana.frmSimulacionRobots.hide();
			
			break;

		default:
			break;
		}
	}
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
}
