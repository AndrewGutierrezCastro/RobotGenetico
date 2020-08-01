package controller;


import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JLabel;
import gui.VentanaPrincipal;
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
			System.out.println("Hilo reanudado");
		}
	}
	
	public void run() {
		System.out.println("Hilo corriendo...");
		while(HiloGeneral.isAlive()) {
			
			try {
				System.out.println("Corriendo");
				ComportarPoblacion();
				refresh();
				HiloGeneral.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void ComportarPoblacion() {
		this.poblacion.ComportarGeneracion();
	}

	public void Pause() {
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "IniciarSimulacion":
			Start();
			break;
		case "PausarSimulacion":
			Pause();
			break;
		default:
			break;
		}
		
	}

	@Override
	public void show() {
		ventana.frame.setVisible(true);	
		cargarTerrenoGUI(); 
		crearMatrizPanel();
		refresh();
	}
	
	public void refresh() {		
		MostrarTerreno();
		MostrarRobots();
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	public Poblacion getPoblacion() {
		return poblacion;
	}
	
	public void setPoblacion(Poblacion poblacion) {
		this.poblacion = poblacion;
	}
	
	private void MostrarRobots() {
		int generacionActual = poblacion.Generacion.size() - 1;
		Robot[] listaRobots = poblacion.Generacion.get(generacionActual).robots;
		Icon imagen = Helpers.getImagen("Robot", ".png", ventana.pnlTerreno.getWidth(),	ventana.pnlTerreno.getHeight());
		for (Robot robot : listaRobots) {
			robot.lblRobot.setIcon(imagen);
			lblMatriz[robot.posicion.x][robot.posicion.y].setIcon(robot.lblRobot.getIcon());
		
		}
	}
}
