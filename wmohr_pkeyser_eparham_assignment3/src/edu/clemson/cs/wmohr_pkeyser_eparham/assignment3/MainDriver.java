package edu.clemson.cs.wmohr_pkeyser_eparham.assignment3;

import java.awt.EventQueue;

import javax.swing.UIManager;

public class MainDriver {
	public static void main(String[] args) {
		DataStore serialize = DataStore.getInstance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame frame = new MainFrame();
					RelativePosition.setMainWindow(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					//ErrorDlg error = new ErrorDlg("Exception Found");
					//error.setVisible(true);
				}
			}
		});
		serialize.Deserialize();
	}
}
