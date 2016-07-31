package events;

import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientLeaveEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;

import core.Main;
import core.Server;

public class ClientLeaveHandler {
	ClientLeaveEvent event;
	String invokerName, invokerUid, targetName, targetUid, reason;
	int targetId;
	ClientInfo target;

	
	public ClientLeaveHandler(ClientLeaveEvent event) {
		this.event = event;
		invokerName = this.event.getInvokerName();
		invokerUid = this.event.getInvokerUniqueId();
		targetId = this.event.getClientTargetId();
		target = Server.getApi().getClientInfo(targetId);
		targetName = target.getNickname();
		targetUid = target.getUniqueIdentifier();
		reason = this.event.getReasonMessage();

		handleConnection();
	}
	
	private void handleConnection() {
		if(true) { //TODO: Add logic for GUI & Config check.
			displayDisconnect(invokerName.equals(""));
		}
		//Add additional logic for other actions.
		Server.removeClient(targetId);
	}
	
	private void displayDisconnect(boolean wasForced) {
		if (wasForced) {
			System.out.println(Main.timeStamp() + invokerName + " (" + invokerUid + ") has kicked " +  targetName + " (" + targetUid + ") from the server for the reason: " + reason + ".");
		} else {
			System.out.println(Main.timeStamp() + targetName + "(" + targetUid + ") has disconnected from the server.");
		}
	}
	
	private void logDisconnect() {
		//TODO: Log to file.
	}
	
	private void updateActiveUserList() {
		
	}
}
