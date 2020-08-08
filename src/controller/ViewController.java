package controller;

import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;

public abstract class  ViewController implements ActionListener, TableModelListener{
	
	public abstract void show();
	
	public abstract  void initialize();
}
