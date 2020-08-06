package controller;

import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionListener;

public abstract class  ViewController implements ActionListener, ListSelectionListener{
	
	public abstract void show();
	
	public abstract  void initialize();
}
