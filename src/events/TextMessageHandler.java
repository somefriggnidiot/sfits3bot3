package events;

import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;

import core.Main;

public class TextMessageHandler {
	TextMessageEvent event;
	String invokerName, invokerUid, method, message;
	
	public TextMessageHandler(TextMessageEvent event) {
		this.event = event;
		invokerName = this.event.getInvokerName();
		invokerUid = this.event.getInvokerUniqueId();
		method = this.event.getTargetMode().toString().toLowerCase();
		message = this.event.getMessage();
		handleTextMessage();
	}
	
	private void handleTextMessage() {
		if(true) { //TODO: Logic for display. Blocked by GUI & Config.
			displayTextMessage();
		}
/*		if(true) { //TODO: Logic for log. Blocked by GUI & Config.
			logTextMessage();
		}*/
	}
	
	private void displayTextMessage() {
		System.out.println(Main.timeStamp() + " [MESSAGE] " + invokerName + " (" + invokerUid + ") to " + method + ": " + message);
	}
	
	private void logTextMessage() {
		//TODO: Log message.
	}
	
	
}
