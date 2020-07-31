package controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Helpers {
	
	private static int ancho = 20, alto = 20;
	
	public static Icon getImagen(String nombreBloque, String extension, int anchoRoot, int altoRoot) {
		
		String path = System.getProperty("user.dir")+"/src/images/"+nombreBloque+extension;
		File archivoImagen = new File(path);
		ImageIcon iconoImagen = null;
		Image IconReescalada = null;
		int x,y;
		x = (anchoRoot - ancho*5 )/ ancho;
		y = (altoRoot - alto*5 )/ alto;
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
}