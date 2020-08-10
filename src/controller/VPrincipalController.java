package controller;


import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.event.TableModelEvent;
import gui.JTableModel;
import gui.ListGeneracionModel;
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
		Simulacion.getInstance().createTerreno();
		terreno = Simulacion.getInstance().getTerreno();
		poblacion = Poblacion.getInstance();
		HiloGeneral = new Thread(this);
	}
	
	public void Start() {
		if(!HiloGeneral.isAlive()) {
			HiloGeneral.start();
			terreno.MostrarTerreno();
		}else {
			HiloGeneral.resume();
			terreno.MostrarTerreno();
			ComportarPoblacion();
			System.out.println("Hilo reanudado");
		}
	}
	
	public void run() {
		System.out.println("Hilo corriendo...");
		ComportarPoblacion();
		while(HiloGeneral.isAlive()) {		
			try {
				
				HiloGeneral.sleep(200);
				refresh();
				if(Poblacion.getInstance().isAllDead()) {
					procesoNuevaGeneracion();
					
				}
			} catch (InterruptedException e) {
				System.out.println("Desactualizacion Hilo");
			}catch (Throwable e) {
				System.out.println("Hilo: Tabla error");
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
		PausaPoblacion();
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
		Poblacion.getInstance().setLblBloques(lblTerreno);
	}
	
	private void crearMatrizPanel() {
		terreno.setLblMatriz(new JLabel[terreno.alto][terreno.ancho]);
		JLabel[][] lblMatriz = terreno.getLblMatriz();
		
		for (int i = 0; i < lblMatriz.length ; i++) {
			for (int j = 0; j < lblMatriz[0].length; j++) {
				JLabel lblTemporal = new JLabel();
				ventana.pnlTerreno.add(lblTemporal, "cell "+j+" "+i);
				lblTemporal.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						final int x = (lblTemporal.getY()/29) ;
						final int y = (lblTemporal.getX())/25;
						lblTemporal.setToolTipText("x: "+x +", y:"+y);
					}
				});
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
		Robot[] robotsGeneracionActual = generacionSeleccionada.robots;
		if(generacionSeleccionada != null && robotsGeneracionActual != null) {
			
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
			try {
				refresh();
			} catch (Exception e1) {
				System.out.println("ComboBox: Error tabla");
			}
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
		ventana.frmSimulacion.setVisible(true);	
		initialize();
	}
	
	public void refresh() throws Exception {		
		MostrarRobots();
	}
	
	@Override
	public void initialize() {
		cargarTerrenoGUI(); 
		crearMatrizPanel();
		try {
			refresh();
		} catch (Exception e) {
			System.out.println("Initialize: Tabla error");
		}
		Poblacion.getInstance().setLblTerreno(terreno.getLblMatriz());
		
		Simulacion.getInstance().createGeneracionInicial();
		
		
	}
	
}
