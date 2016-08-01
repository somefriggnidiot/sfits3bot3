package events;

import com.github.theholywaffle.teamspeak3.api.event.ChannelDescriptionEditedEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;

import core.Main;
import core.Server;

public class ChannelDescriptionChangeHandler {
	ChannelDescriptionEditedEvent chanDescEditEvent;
	ChannelInfo chan;
	String invokerName, invokerUid, chanName, chanDescription;
	
	public ChannelDescriptionChangeHandler(ChannelDescriptionEditedEvent chanDescEditEvent) {
		this.chanDescEditEvent = chanDescEditEvent;
		invokerName = this.chanDescEditEvent.getInvokerName();
		invokerUid = this.chanDescEditEvent.getInvokerUniqueId();
		chan = Server.getApi().getChannelInfo(this.chanDescEditEvent.getChannelId());
		chanName = chan.getName();
		chanDescription = chan.getDescription();
		
		handleDescriptionChangeEvent();
	}
	
	public void handleDescriptionChangeEvent() {
		if (true) {
			displayDescChangeEvent();
		}
	}
	
	public void displayDescChangeEvent() {
		if (chanDescription.equals("")) {
			System.out.println(Main.timeStamp() + "The channel description for " + chanName + " has been removed.");
		} else {
			System.out.println(Main.timeStamp() + "The channel description for " + chanName + " has been changed to: " + chanDescription);
		}
	}
	
}
