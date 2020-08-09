package controller;

import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.event.TableModelEvent;

import gui.JTableModel;
import gui.VentanaInformacionRobot;
import model.Posicion;
import model.Robot;
import model.Terreno;

public class VInformacionRobot extends ViewController {
	private VentanaInformacionRobot ventana;
	private Terreno terreno;
	private Robot robot;
	private JLabel[][] lblMatriz;
	
	
	public VInformacionRobot(Terreno terreno, Robot robot) {
		super();
		this.ventana = new VentanaInformacionRobot();
		this.terreno = terreno;
		this.robot = robot;	
	}
	
	private void crearMatrizPanel() {
		lblMatriz = new JLabel[terreno.alto][terreno.ancho];
		
		for (int i = 0; i < lblMatriz.length ; i++) {
			for (int j = 0; j < lblMatriz[0].length; j++) {
				JLabel lblTemporal = new JLabel();
				ventana.pnlTerreno.add(lblTemporal, "cell "+j+" "+i);
				lblMatriz[i][j] = lblTemporal;
			}
		}
	}
	
	private void cargarRobotTabla() {
		ventana.tblRobot.setModel(new JTableModel(robot) );
	}
	
	private void mostrarCaminoRobot() {
		ventana.lblImagenRobot.
			setIcon(Helpers.
					getImagenResized(
							"Robot",
							".png",
							ventana.lblImagenRobot.getWidth(),
							ventana.lblImagenRobot.getHeight())
				);
		for (Posicion posicion : robot.getPosiciones()) {
			lblMatriz[posicion.x][posicion.y].
				setIcon(Helpers.
						getImagen("BVERDE",
								".png",
								ventana.pnlTerreno.getWidth(),
								ventana.pnlTerreno.getHeight())
						);
		}
	}
	
	private void mostrarTerreno() {
		for (int i = 0; i < lblMatriz.length ; i++) {
			for (int j = 0; j < lblMatriz[0].length; j++) {
				Icon icon = Helpers
					.getImagen(terreno.terreno[i][j].name(),
							".jpg",
							ventana.pnlTerreno.getWidth(),
							ventana.pnlTerreno.getHeight());
				lblMatriz[i][j].setIcon(icon);		
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		initialize();
		ventana.frmInformacionRobot.setVisible(true);	
		
	}

	@Override
	public void initialize() {
		crearMatrizPanel();
		cargarRobotTabla();
		mostrarTerreno();
		mostrarCaminoRobot();
	}

}
