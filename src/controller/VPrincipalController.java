package controller;

import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.VentanaPrincipal;
import model.Simulacion;
import model.Terreno;

public class VPrincipalController extends ViewController{

	private VentanaPrincipal ventana;
	private Terreno terreno;
	private JLabel[][] lblTerreno;
	public VPrincipalController() {
		ventana = new VentanaPrincipal();
		ventana.setController(this);
		terreno = Simulacion.getInstance().getTerreno();
		cargarTerrenoGUI();
	}

	public void cargarTerrenoGUI() {
		lblTerreno = new JLabel[terreno.ancho][terreno.alto];
		
		for (int i = 0; i < lblTerreno.length; i++) {
			for (int j = 0; j < lblTerreno[0].length; j++) {
				Icon icon = getImagenBloque(terreno.terreno[i][j].name());
				JLabel lblBloque = new JLabel();
				lblBloque.setIcon(icon);
				ventana.pnlTerreno.add(lblBloque, "cell "+i+" "+j);
			}
		}
			
	}
	
	private Icon getImagenBloque(String nombreBloque) {
		
		String path = System.getProperty("user.dir")+"/src/images/"+nombreBloque+".jpg";
		File archivoImagen = new File(path);
		ImageIcon iconoImagen = null;
		Image IconReescalada = null;
		int x,y;
		x = (ventana.pnlTerreno.getWidth() - terreno.ancho*5 )/ terreno.ancho;
		y = (ventana.pnlTerreno.getHeight() - terreno.alto*5 )/ terreno.alto;
		try {
			  
			iconoImagen = new ImageIcon(ImageIO.read(archivoImagen));
			IconReescalada = iconoImagen.getImage();
			Image newimg = IconReescalada.getScaledInstance(x, y,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
			iconoImagen = new ImageIcon(newimg);
		} catch (IOException e) {
			System.out.print(e.getMessage());
			System.out.println(" No se pudo cargar el bloque, " + nombreBloque +" "+path );
			
		}
		return iconoImagen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "ComandoEjemplo":
			System.out.println("Comando ejemplo");
			break;

		default:
			break;
		}
		
	}

	@Override
	public void show() {
		ventana.frame.setVisible(true);	
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
}
