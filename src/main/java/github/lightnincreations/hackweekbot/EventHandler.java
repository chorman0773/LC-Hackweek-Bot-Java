package github.lightnincreations.hackweekbot;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import github.lightnincreations.hackweekbot.command.CommandRegistry;
import github.lightnincreations.hackweekbot.command.CommandSource;
import net.dv8tion.jda.api.entities.Message;
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
		//TODO Load Prefix from properties
		Message m = event.getMessage();
		String content = m.getContentRaw();
		String prefix = ">";
		if(content.startsWith(prefix)) {
			content = content.substring(prefix.length());
			System.out.println(content);
			try {
				dispatcher.execute(content, new CommandSource(m.getTextChannel(),m.getGuild(),m.getMember()));
			} catch (CommandSyntaxException e) {
				m.getChannel().sendMessageFormat("Failed To process command: %s", e.getMessage()).submit();
			}
		}
	}

}
