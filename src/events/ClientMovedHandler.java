package events;

import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;

import core.Main;
import core.Server;

public class ClientMovedHandler {
	private ClientMovedEvent movedEvent;
	String invokerName, invokerUid, movedClientName, movedClientUid, channelName;
	ClientInfo movedClient;
	ChannelInfo channel;
	
	public ClientMovedHandler(ClientMovedEvent movedEvent) {
		this.movedEvent = movedEvent;
		channel = Server.getApi().getChannelInfo(this.movedEvent.getTargetChannelId());
		channelName = channel.getName();
		invokerName = this.movedEvent.getInvokerName();
		invokerUid = this.movedEvent.getInvokerUniqueId();
		movedClient = Server.getApi().getClientInfo(this.movedEvent.getClientId());
		movedClientName = movedClient.getNickname();
		movedClientUid = movedClient.getUniqueIdentifier();
		
		System.out.println(invokerName);
		
		handleClientMovedEvent();
	}
	
	private void handleClientMovedEvent() {
		if (true) {
			displayClientMovedEvent();
		}
	}
	
	private void displayClientMovedEvent() {
		if (invokerName.equals("")) {
			System.out.println(Main.timeStamp() + movedClientName + " (" + movedClientUid + ") moved to \"" + channelName + "\".");
		} else {
			System.out.println(Main.timeStamp() + movedClientName + " (" + movedClientUid + ") was moved to \"" + channelName + "\" by " + invokerName + " (" + invokerUid + ").");
		}
	}
}
