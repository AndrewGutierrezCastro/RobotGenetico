package controller;


import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;

import Application.App;
import gui.JTableModel;
import gui.ListGeneracionModel;
import gui.ListRobotModel;
import gui.VentanaPrincipal;
import model.Generacion;
import model.Poblacion;
import model.Robot;
import model.Simulacion;
import model.Terreno;

public class VPrincipalController extends ViewController implements Runnable{

	private VentanaPrincipal ventana;
	private Terreno terreno;
	private Poblacion poblacion;
	private Thread HiloGeneral;
	public VPrincipalController() {
		ventana = new VentanaPrincipal();
		ventana.setController(this);
		terreno = Simulacion.getInstance().getTerreno();
		poblacion = Poblacion.getInstance();
		HiloGeneral = new Thread(this);
	}
	
	public void Start() {
		if(!HiloGeneral.isAlive()) {
			HiloGeneral.start();
			terreno.HiloTerreno.start();
		}else {
			HiloGeneral.resume();
			ComportarPoblacion();
			System.out.println("Hilo reanudado");
		}
	}
	
	public void run() {
		System.out.println("Hilo corriendo...");
		ComportarPoblacion();
		while(HiloGeneral.isAlive()) {		
			try {
				refresh();
				HiloGeneral.sleep(100);
				if(Poblacion.getInstance().isAllDead()) {
					System.out.println("Antes");
					procesoNuevaGeneracion();
					System.out.println("Despues");
					
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void ComportarPoblacion() {
		//Realmente inicia los hilos de cada robot de la generacion actual
		Poblacion.getInstance().ComportarGeneracion();
	}
	
	private void PausaPoblacion() {
		Poblacion.getInstance().PausaGeneracion();
	}
	
	public void Pause() {
		PausaPoblacion();
		if(!HiloGeneral.isInterrupted()) {
			HiloGeneral.suspend();
			System.out.println("Hilo suspendido");
		}else {
			HiloGeneral.stop();
			System.out.println("Hilo detenido");
		}
	}
	
	public void cargarTerrenoGUI() {
		JLabel[][] lblTerreno = new JLabel[terreno.alto][terreno.ancho];
		
		for (int i = 0; i < lblTerreno.length ; i++) {
			for (int j = 0; j < lblTerreno[0].length; j++) {
				Icon icon = Helpers
					.getImagen(terreno.terreno[i][j].name(),
							".jpg",
							ventana.pnlTerreno.getWidth(),
							ventana.pnlTerreno.getHeight());
				JLabel lblBloque = new JLabel();
				lblBloque.setIcon(icon);		
				lblTerreno[i][j] = lblBloque;
			}
		}
		this.terreno.setLblTerreno(lblTerreno);
			
	}
	
	private void crearMatrizPanel() {
		terreno.setLblMatriz(new JLabel[terreno.alto][terreno.ancho]);
		JLabel[][] lblMatriz = terreno.getLblMatriz();
		
		for (int i = 0; i < lblMatriz.length ; i++) {
			for (int j = 0; j < lblMatriz[0].length; j++) {
				JLabel lblTemporal = new JLabel();
				ventana.pnlTerreno.add(lblTemporal, "cell "+j+" "+i);
				lblMatriz[i][j] = lblTemporal;
			}
		}
	}
	
	private void MostrarGeneraciones() {	
		ventana.cmbGeneracion.setModel(new ListGeneracionModel(Poblacion.getInstance().Generacion.values()));	
		ventana.cmbGeneracion.setSelectedIndex(Poblacion.getInstance().Generacion.size()-1);
	}
	
	private void MostrarRobots() {
		Generacion generacionSeleccionada = (Generacion) ventana.cmbGeneracion.getSelectedItem();
		if(generacionSeleccionada != null) {
			Robot[] robotsGeneracionActual = generacionSeleccionada.robots;
			ventana.tblRobots.setModel(new JTableModel(robotsGeneracionActual) );
			ventana.tblRobots.AdjustColumnWidth();
		}else {
			System.out.println("Sin generacion seleccionada");
		}
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {	
		/* Este metodo el cambio de valor en las listas de la ventana
		 * */
		if (e.getSource().equals(ventana.tblRobots) & e.getFirstRow() != -1) {
			System.out.println("Fila: " + e.getFirstRow());
		}else {
			
		}
	} 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "IniciarSimulacion":
			MostrarGeneraciones();	
			Start();
			
			break;
		case "PausarSimulacion":
			Pause();
			break;
		case "CmbGeneracion":
			refresh();
			break;
		case "CrearNuevaGeneracion":
			procesoNuevaGeneracion();
			break;
		case "VerInfo":
			int rowSelected = ventana.tblRobots.getSelectedRow();
			if( rowSelected != -1) {
				JTableModel tableModel = (JTableModel) ventana.tblRobots.getModel();
				System.out.println(tableModel.getRobot(rowSelected));
				new VInformacionRobot(terreno,
						tableModel.getRobot(rowSelected)).show();
				
			}
			break;
		default:
			break;
		}
		
	}
	
	private void procesoNuevaGeneracion() {
		//Pause();
		PausaPoblacion();
		System.out.println("Pause despues");
		Poblacion.getInstance().CrearNuevaGeneracion();
		MostrarGeneraciones();		
		Start();
	}
	
	@Override
	public void show() {
		ventana.frame.setVisible(true);	
		initialize();
	}
	
	public void refresh() {		
		MostrarRobots();
	}
	
	@Override
	public void initialize() {
		cargarTerrenoGUI(); 
		crearMatrizPanel();
		refresh();
		Poblacion.getInstance().setLblTerreno(terreno.getLblMatriz());
		
	}
	
}
