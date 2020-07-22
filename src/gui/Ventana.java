package gui;

import controller.ViewController;

public class Ventana {
	/*
	 * Clase ventana para heredar de todas las ventanas de aqui
	 * Poner metodos generales como initialize etc*/
	protected ViewController controller;
	public Ventana() {
	}
	public ViewController getController() {
		return controller;
	}
	public void setController(ViewController controller) {
		this.controller = controller;
	}
}
