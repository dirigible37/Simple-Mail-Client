package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class ActionListeners{
	
	public ActionListener getRadioAction(final DataStore myData, final boolean pressed) {
		ActionListener listener =  new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myData.getConfig().setTsl(pressed);
			}
		};
		return listener;
	}	
	
	public ActionListener getOkAction(final DataStore myData, final boolean pressed) {
		ActionListener listener =  new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myData.getConfig().setTsl(pressed);
			}
		};
		return listener;
	}	
	
}
