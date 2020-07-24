package model;
import Application.App;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Simulacion.getInstance();
		App.getInstance();
		App.start();
	}
}
