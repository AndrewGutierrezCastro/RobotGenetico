package gui;

import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import model.Generacion;

public class ListGeneracionModel extends DefaultComboBoxModel<Generacion> {
	
	public ListGeneracionModel(Collection<Generacion> pGeneraciones) {
		this.addAll(pGeneraciones);
	}
}
