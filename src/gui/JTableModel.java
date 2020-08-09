package gui;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabableView;

import model.Estado;
import model.Hardware.TiposHardware;
import model.Robot;

public class JTableModel extends DefaultTableModel {
	/**
     * Se carga con tres filas y tres columnas con numeros.
     */
	private static String[] columnModel = new String[] {"Estado", "Alive", "Camara", "Motor","Generador", "Bateria", "X", "Y" };
	private static String[] rowFormat = new String[] { 
			"Alive",
			"Camara",
			"Motor",
			"Generador",
			"Bateria",
			"Llego",
			"AVANZAR:",
			"OBSERVAR",
			"ESPERAR",
			"Observaciones",
			"Recargas",
			"Genero",
			"Gasto",
			"Avances Distintos",
			"Avances Totales",
			"Costo"};
    private Robot[] robots;
	public JTableModel() {
    	/*Esta hecha para representar Robots
    	 * Los mismo tienen:
    	 * 		Alive:true    Camara: MEDIO Motor: BASICO Generador: BASICO Bateria: 3000  X:19 Y:0 */
        super();
    }

    public JTableModel(Robot[] robotsGeneracionActual) {
    	super();
    	this.robots = robotsGeneracionActual;
    	Object[][] tablaObject = new Object[robotsGeneracionActual.length][columnModel.length];
		for (int i = 0; i < robotsGeneracionActual.length; i++) {
			tablaObject[i] = robotsGeneracionActual[i].getInfo();
		}
		this.setDataVector(tablaObject, columnModel);
    }
    
    public JTableModel(Robot robot) {
    	/*Retorna un array de Object de:
		 * Alive:true 
		 * Camara: MEDIO
		 * Motor: BASICO
		 * Bateria: Avanzado
		 * Llego: True
		 * AVANZAR:[5,5,90]
		 * OBSERVAR:[10,10,80]
		 * ESPERAR:[40,40,20]
		 * Observaciones:542
		 * Recargas: 14
		 * Genero: 1526
		 * Gasto: 6511
		 * MovientosDistintos: 58
		 * Movimientos: 125 
		 * Costo: 516
		 * */
    	super();
    	Object[] allInfo = robot.getAllInfo();
    	Object[][] tablaObjects = new Object[allInfo.length][2];
    	for (int i = 0; i < allInfo.length; i++) {
    		tablaObjects[i][0] = rowFormat[i];
    		tablaObjects[i][1] = allInfo[i];
		}
    	this.setDataVector(tablaObjects, new String[]{"Informacion Robot","Datos Robot"});
    }
    
    public JTableModel(Object[][] pMatrizObject) {
    	super(pMatrizObject, columnModel);
    }
	public JTableModel(Object[][] pMatrizObject, String[] pColumnModel) {
		super(pMatrizObject, pColumnModel);
	}
    public Robot getRobot(int row) {
    	return robots[row];
    }
    
    @Override
    public Class<?> getColumnClass(int arg0) {
    	/**
         * El contenido de las celdas sera template.
         */
        return this.getClass();
    }
    
}
