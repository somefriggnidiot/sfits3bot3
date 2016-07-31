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
		System.out.println(invokerName + " " + invokerUid);
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
		System.out.println(Main.timeStamp() + invokerName + " (" + invokerUid + ") changed the description of " + chanName + " to: " + chanDescription);
	}
	
}
