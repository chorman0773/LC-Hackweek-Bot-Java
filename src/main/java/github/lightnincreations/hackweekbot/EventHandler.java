package github.lightnincreations.hackweekbot;

import com.mojang.brigadier.CommandDispatcher;

import github.lightnincreations.hackweekbot.command.CommandRegistry;
import github.lightnincreations.hackweekbot.command.CommandSource;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventHandler extends ListenerAdapter {
	private CommandDispatcher<CommandSource> dispatcher = new CommandDispatcher<>();
	@Override
	public void onReady(ReadyEvent event) {
		System.out.println("Ready!");
		CommandRegistry.registerCommands(dispatcher);
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		//TODO, extract the prefix from the message, then if necessary, pass it off to Brigadier.
	}

}
