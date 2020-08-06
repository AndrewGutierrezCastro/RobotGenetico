package gui;

import java.util.Arrays;
import javax.swing.DefaultListModel;
import model.Robot;

public class ListRobotModel extends DefaultListModel<Robot> {

	public ListRobotModel(Robot[] robots) {
		this.addAll( Arrays.asList(robots));
	}

	
}
