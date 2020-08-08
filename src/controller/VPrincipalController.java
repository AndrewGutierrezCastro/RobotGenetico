package controller;


import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;

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
	private JLabel[][] lblMatriz;
	private JLabel[][] lblTerreno;
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
		lblTerreno = new JLabel[terreno.alto][terreno.ancho];
		
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
			
	}
	
	private void crearMatrizPanel() {
		lblMatriz = new JLabel[terreno.alto][terreno.ancho];
		
		for (int i = 0; i < lblMatriz.length ; i++) {
			for (int j = 0; j < lblMatriz[0].length; j++) {
				JLabel lblMatriz = new JLabel();
				ventana.pnlTerreno.add(lblMatriz, "cell "+j+" "+i);
				this.lblMatriz[i][j] = lblMatriz;
			}
		}
	}
	
	private void MostrarTerreno() {
		for (int i = 0; i < lblTerreno.length ; i++) {
			for (int j = 0; j < lblTerreno[0].length; j++) {	
				lblMatriz[i][j].setIcon(lblTerreno[i][j].getIcon());
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
			
			break;
		case "CrearNuevaGeneracion":
			procesoNuevaGeneracion();
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
		MostrarTerreno();
		MostrarRobots();
	}
	
	@Override
	public void initialize() {
		cargarTerrenoGUI(); 
		crearMatrizPanel();
		refresh();
		Poblacion.getInstance().setLblTerreno(lblMatriz);
		
	}
	
	public JLabel[][] getLblMatriz() {
		return lblMatriz;
	}

	public void setLblMatriz(JLabel[][] lblMatriz) {
		this.lblMatriz = lblMatriz;
	}
	
}
