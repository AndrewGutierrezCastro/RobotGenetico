package gui;

import javax.swing.DefaultListModel;

import com.sun.tools.javac.util.List;

import model.Robot;

public class ListRobotModel extends DefaultListModel<Robot> {

	public ListRobotModel(Robot[] robots) {
		this.addAll(List.from(robots));
	}

	
}
