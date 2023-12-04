import java.awt.Frame;

import javax.swing.*;

import View.Menu;
import View.dangky;

public class Run {
public static void main(String[] args) {
	
	dangky frame = new dangky();
	frame.setVisible(true);
	frame.setBounds(450, 190, 1014, 597);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	try {
		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		javax.swing.UIManager.setLookAndFeel(info.getClassName());
		break;
		}
		}
		} catch (ClassNotFoundException ex) {
		java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
		java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
		java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
		java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
}

}

