package core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
//            	Config.loadConfig();
            	Gui.createAndShowGUI();
            }
        });
	}
	
	public static String getVersion(boolean withBuild) {
		
		/**	MAJOR CHANGE NOTES
		 * 		Version 0
		 * 			Information largely stored in fields.
		 * 		Version 1
		 * 			Information moved to context menus and tool-bar.
		 * 			Console view is primary focus.
		 * 
		 * 		TODO:
		 * 			- Add tabs / support for concurrent bots.
		 */
		int major = 1;
		
		/**	MINOR CHANGE NOTES
		 * 		Minor 4
		 * 			Added notification to say bot's offline when attempting actions that affect server.
		 * 		Minor 5
		 * 			Added support for idle ignore ignore channel.
		 * 		Minor 6
		 * 			Added additional events to be logged.
		 * 
		 * 		TODO:
		 * 			- Add more chat commands.
		 * 			- Display basic server info on title bar.
		 */
		int minor = 6;
		
		/**	RELEASE NOTES
		 * 		Release 1
		 * 			Added "rename" chat command.
		 * 		Release 2
		 * 			Fixed error where message-sending would attempt to send on cancel/close.
		 * 			Fixed spelling issues on user movement logging.
		 * 			Repositioned pop-up location to be center of program (previously screen).
		 * 		Release 3
		 * 			Fixed application icon to properly display.
		 * 		Release 4:
		 * 			Added distinct console logging for server-user-kick events.
		 * 
		 * 		TODO:
		 * 			- Trim fat on exception catches.
		 * 			- Set focus to input box on pop-up launches.
		 * 			- Ensure idle checker starts on connection if enabled on D/C.
		 */
		int release = 4; 	
		
		int build = 90;
		
		if (withBuild) {
			return "v." + major + "." + minor + "." + release + "." + build;
		} else {
			return "v." + major + "." + minor + "." + release;
		}
	}

	public static void confirmExit() {
		if (Gui.getTitle().contains("-")) {
			JPanel panel = new JPanel();
			JLabel label = new JLabel("The bot is still connected. Are you sure you wish to exit?");
			panel.add(label);
			int confirm = JOptionPane.showConfirmDialog(Gui.getFrame(), panel, "Warning!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if (confirm == JOptionPane.OK_OPTION) {
				System.exit(0);
			} else if (confirm == JOptionPane.CANCEL_OPTION || confirm == JOptionPane.CLOSED_OPTION) {
				return;
			}
		} else {
			System.exit(0);
		}
	}
	
	public static String timeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return("[" + dateFormat.format(System.currentTimeMillis()) + "] ");
	}
}
