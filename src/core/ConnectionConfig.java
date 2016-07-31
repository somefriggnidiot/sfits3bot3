package core;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectionConfig 
{
	private static JPanel panel = new JPanel(new GridBagLayout());
	private static GridBagConstraints c = new GridBagConstraints();
	private static JLabel serverAddressLabel, queryPortLabel, queryLoginNameLabel, queryLoginPassLabel, botNameLabel, vsidLabel;
	private static JTextField serverAddress, queryPort, queryLoginName, queryLoginPass, botName, vsid;
	private static JCheckBox slowMode;
	//private static JPasswordField queryLoginPass;
	
	public static JPanel createServerInfoPanel()
	{	
		int labelColumn = 0;
		int inputColumn = 1;
		
        c.gridx = labelColumn;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets (5, 5, 5, 5); 
        c.anchor = GridBagConstraints.WEST;
        serverAddressLabel = new JLabel("Server Address: ");
        panel.add (serverAddressLabel, c);
        
        c.gridy = 1;
        queryPortLabel = new JLabel("ServerQuery Port: ");
        panel.add(queryPortLabel, c);
        
        c.gridy = 2;
        queryLoginNameLabel = new JLabel("ServerQuery Name: ");
        panel.add(queryLoginNameLabel, c);
		
        c.gridy = 3;
        queryLoginPassLabel = new JLabel("ServerQuery Password: ");
        panel.add(queryLoginPassLabel, c);
        
        c.gridy = 4;
        botNameLabel = new JLabel("Bot Name: ");
        panel.add(botNameLabel, c);
        
        c.gridy = 5;
        vsidLabel = new JLabel("Virtual Server ID: ");
        panel.add(vsidLabel, c);
        
        c.gridx = inputColumn;
        c.gridy = 0;
        serverAddress = new JTextField(15);
        serverAddress.setText(Config.getProperty("server_address", ""));
        panel.add(serverAddress, c);
        
        c.gridy = 1;
        queryPort = new JTextField(15);
        queryPort.setText(Config.getProperty("server_query_port", ""));
        panel.add(queryPort, c);
        
        c.gridy = 2;
        queryLoginName = new JTextField(15);
        queryLoginName.setText(Config.getProperty("server_query_name", ""));
        panel.add(queryLoginName, c);
        
        c.gridy = 3;
        queryLoginPass = new JTextField(15);
        queryLoginPass.setText(Config.getProperty("server_query_pass", ""));
        panel.add(queryLoginPass, c);
        
        c.gridy = 4;
        botName = new JTextField(15);
        botName.setText(Config.getProperty("bot_name", ""));
        panel.add(botName, c);
        
        c.gridy = 5;
        vsid = new JTextField(15);
        vsid.setText(Config.getProperty("server_virtual_server_id", ""));
        panel.add(vsid, c);
        
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        slowMode = new JCheckBox("Enable Slow Mode", Config.getServerSlowModeFlag());
        panel.add(slowMode, c);
        
		return panel;
	}

	public static void updateConfig()
	{
		Config.setProperty("server_address", getAddress());
		Config.setProperty("server_query_port", getQueryPort());
		Config.setProperty("server_query_name", getQueryLoginName());
		Config.setProperty("server_query_pass", getQueryLoginPass());
		Config.setProperty("bot_name", getBotName());
		Config.setProperty("server_virtual_server_id", getVirtualServerId());
		if (slowMode.isSelected())
		{
			Config.setProperty("bot_slow_mode", "1");
		}
		else if (!slowMode.isSelected())
		{
			Config.setProperty("bot_slow_mode", "0");
		}
	}
	
	public static boolean updateNickname()
	{
		//If nickname in field doesn't match current name, change current name.
		if (!getBotName().equals(Server.getApi().getClientInfo(Server.getMyClientId()).getNickname()))
		{
			botName.setText(Config.getProperty("bot_name"));
			Server.getApi().setNickname(getBotName());
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private static String getAddress()
	{
		return serverAddress.getText();
	}
	
	private static String getQueryPort()
	{
		return queryPort.getText();
	}
	
	private static String getQueryLoginName()
	{
		return queryLoginName.getText();
	}
	
	private static String getQueryLoginPass()
	{
		return queryLoginPass.getText();
	}
	
	private static String getBotName()
	{
		return botName.getText();
	}
	
	private static String getVirtualServerId()
	{
		return vsid.getText();
	}

	public static void setNickname(String newName) 
	{
		Config.setProperty("bot_name", newName);
		Config.saveConfig();
	}
}
