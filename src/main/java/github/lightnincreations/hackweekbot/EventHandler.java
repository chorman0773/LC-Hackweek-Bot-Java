package github.lightnincreations.hackweekbot;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventHandler extends ListenerAdapter {

	@Override
	public void onReady(ReadyEvent event) {
		System.out.println("Ready!");
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		//TODO, extract the prefix from the message, then if necessary, pass it off to Brigadier.
	}

}
