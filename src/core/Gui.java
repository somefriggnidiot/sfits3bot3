package core;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import util.CustomOutputStream;
import util.SmartScroller;

public class Gui {
	private static JFrame frame = new JFrame();
    private static JTextArea console = new JTextArea();
    private static JCheckBoxMenuItem slowModeBox = null;
	private static JCheckBoxMenuItem idleCheckerBox = null;
	private static JCheckBoxMenuItem serverActivityBox = null;
	private static JCheckBoxMenuItem chatDisplayBox = null;
	private static JCheckBoxMenuItem chatCommandsBox = null;
	
	private static String title = "";
	
	public static void createAndShowGUI() {
        //Create and set up the window.
    	frame = new JFrame("sFITs3 Bot (" + Main.getVersion(false) + ")");
    	try {
			frame.setIconImage(Toolkit.getDefaultToolkit().createImage(new URL("http://somefriggnidiot.com/favicon.ico")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	frame.addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent we) {
    			Main.confirmExit();
    		}
		});
    	frame.setResizable(true);
    	frame.setPreferredSize(new Dimension(800,400));
    	frame.setMinimumSize(new Dimension(800,400));
        
        Gui gui = new Gui();
        frame.setJMenuBar(gui.createMenuBar());
        frame.setContentPane(gui.createContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
        //TODO Tutorial if bot staff groups not set.
	}
	
	public static void updateTitle() {
		//TODO
	}
	
    public static JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();
        
        /*********************************
         * File Menu *********************
         *********************************/
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        
        menu.add(createMenuItem("Clear Console", new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		console.setText("");
        	}
        }, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK)));
        
        menuBar.add(menu);
        
        /*********************************
         * Server Menu *******************
         *********************************/
        menu = new JMenu("Server");
        menu.setMnemonic(KeyEvent.VK_S);
        
        menu.add(createMenuItem("Edit Connection Info", new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(JOptionPane.showConfirmDialog(frame, ConnectionConfig.createServerInfoPanel(), "Connection Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
        			ConnectionConfig.updateConfig();
        			Config.saveConfig();
        		}
        	}
        }));
        
        menu.add(createMenuItem("Connect", new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		Server.connect();
        	}
        }));
        
        menu.add(createMenuItem("Disconnect", new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (Server.isConnected()) {
        			Server.disconnect();
        			System.out.println(Main.timeStamp() + "Bot disconnected from console.");
        		} else {
        			System.out.println(Main.timeStamp() + "Bot is not currently connected to a server.");
        		}
        	}
        }));
        
        menuBar.add(menu);
        
        return menuBar;
    }
    
    public static Container createContentPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        console = new JTextArea(20, 70);
        console.setEditable(false);
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(console));
		System.setOut(printStream);
		//TODO : Allow only if debug on. 
		//System.setErr(printStream);
        JScrollPane scrollPane = new JScrollPane(console);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
        scrollBar.addAdjustmentListener(new SmartScroller(scrollPane));

        contentPane.add (scrollPane, BorderLayout.CENTER);

        return contentPane;
    }
	
	private static JMenuItem createMenuItem(String displayText, ActionListener functionality) {
		JMenuItem menuItem = new JMenuItem(displayText);
		menuItem.addActionListener(functionality);
		
		return menuItem;
	}
	
	private static JMenuItem createMenuItem(String displayText, ActionListener functionality, KeyStroke accelerator) {
		JMenuItem menuItem = new JMenuItem(displayText);
		menuItem.addActionListener(functionality);
		menuItem.setAccelerator(accelerator);
		
		return menuItem;
	}
    
	public static void setTitle(String title) {
		frame.setTitle(title);
	}
	
    public static String getTitle() {
		return title;
	}
	
	public static JFrame getFrame(){
		return frame;
	}
	
	public static boolean showChatOn() {
		//TODO
		return true;
	}

	public static boolean chatCommandsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
