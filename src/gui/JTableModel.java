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
    
	public JTableModel() {
    	/*Esta hecha para representar Robots
    	 * Los mismo tienen:
    	 * 		Alive:true    Camara: MEDIO Motor: BASICO Generador: BASICO Bateria: 3000  X:19 Y:0 */
        super(new Object[][] { 
        		{ Estado.ESPERANDO, true, TiposHardware.AVANZADO, TiposHardware.MEDIO,TiposHardware.BASICO, 1000, 0, 0}}
        		, columnModel);
    }

    public JTableModel(Robot[] robotsGeneracionActual) {
    	super();
    	Object[][] tablaObject = new Object[robotsGeneracionActual.length][columnModel.length];
		for (int i = 0; i < robotsGeneracionActual.length; i++) {
			tablaObject[i] = robotsGeneracionActual[i].getInfo();
		}
		this.setDataVector(tablaObject, columnModel);
    }
    
    public JTableModel(Object[][] pMatrizObject) {
    	super(pMatrizObject, columnModel);
    }
	
    @Override
    public Class<?> getColumnClass(int arg0) {
    	/**
         * El contenido de las celdas sera template.
         */
        return this.getClass();
    }
}
