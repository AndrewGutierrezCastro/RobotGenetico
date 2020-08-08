package gui;

public class JTable extends javax.swing.JTable {
	private int[] columnSizes = {15,40,40,20,10,10};
	public JTable() {
		super();
	}
	
	public void AdjustColumnWidth() {
		/*Este metodo ajusta el ancho de las columnas segun su contenido
		 * */
		for (int i = 0; i < columnSizes.length; i++) {
			getColumnModel().getColumn(i).setWidth(columnSizes[i]);
			getColumnModel().getColumn(i).setPreferredWidth(columnSizes[i]);
		}
	}
	
}
