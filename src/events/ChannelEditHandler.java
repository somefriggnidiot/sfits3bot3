package events;

import com.github.theholywaffle.teamspeak3.api.event.ChannelEditedEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;

import core.Main;
import core.Server;

public class ChannelEditHandler {
	ChannelEditedEvent editEvent;
	String invokerName, invokerUid, channelName;
	ChannelInfo channel;
	
	public ChannelEditHandler(ChannelEditedEvent editEvent) {
		this.editEvent = editEvent;
		invokerName = this.editEvent.getInvokerName();
		invokerUid = this.editEvent.getInvokerUniqueId();
		channel = Server.getApi().getChannelInfo(this.editEvent.getChannelId());
		channelName = channel.getName();
		
		handleEditEvent();
	}
	
	private void handleEditEvent() {
		if (true) {
			displayEditEvent();
		}
	}
	
	private void displayEditEvent() {
		System.out.println(Main.timeStamp() + invokerName + " (" + invokerUid + ") edited channel: " + channelName);
	}
}
