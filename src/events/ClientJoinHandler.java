package events;

import java.util.Map;

import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;

import core.Main;
import core.Server;

public class ClientJoinHandler {
	ClientJoinEvent event;
	String clientName, clientUid, channelName;
	int clientId;
	ChannelInfo channel;
	ClientInfo client;
	
	public ClientJoinHandler(ClientJoinEvent event) {
		this.event = event;
		clientName = this.event.getClientNickname();
		clientUid = this.event.getUniqueClientIdentifier();
		clientId = this.event.getClientId();
		channel = Server.getApi().getChannelInfo(this.event.getClientTargetId());
		client = Server.getApi().getClientInfo(clientId);
		channelName = channel.getName();
		handleConnection();
	}
	
	private void handleConnection() {
		Server.addClient(clientId, client);
		if(true) {
			displayConnect();
		}
	}
	
	private void displayConnect() {
		System.out.println(Main.timeStamp() + clientName + " (" + clientUid + ") connected to \"" + channelName + "\".");
	}
	
	private void logConnect() {
		//TODO: Log to file.
	}
	
	private void updateActiveUserList() {
		
	}
	
}
