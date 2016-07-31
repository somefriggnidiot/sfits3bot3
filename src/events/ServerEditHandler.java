package events;

import com.github.theholywaffle.teamspeak3.api.event.ServerEditedEvent;

import core.Main;

public class ServerEditHandler {
	ServerEditedEvent editEvent;
	String invokerName, invokerUid;
	
	public ServerEditHandler(ServerEditedEvent editEvent) {
		this.editEvent = editEvent;
		invokerName = this.editEvent.getInvokerName();
		invokerUid = this.editEvent.getInvokerUniqueId();
		handleEditEvent();
	}
	
	private void handleEditEvent() {
		if (true) {
			displayEditEvent();
		}
	}
	
	private void displayEditEvent() {
		System.out.println(Main.timeStamp() + invokerName + " (" + invokerUid + ") edited the server.");
	}
	
//	private void logEditEvent() {
		//TODO: Log edit event.
//	}
}
