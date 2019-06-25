package github.lightnincreations.hackweekbot;

import java.util.HashMap;
import java.util.Map;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import github.lightnincreations.hackweekbot.command.CommandRegistry;
import github.lightnincreations.hackweekbot.command.CommandSource;
import github.lightnincreations.hackweekbot.properties.ServerProperties;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventHandler extends ListenerAdapter {
	
	private static final Map<Guild,ServerProperties> properties = new HashMap<>();
	private CommandDispatcher<CommandSource> dispatcher = new CommandDispatcher<>();
	@Override
	public void onReady(ReadyEvent event) {
		System.out.println("Ready!");
		CommandRegistry.registerCommands(dispatcher);
	}
	
	private String getProperty(String key,Guild guild) {
		if(properties.containsKey(guild))
			return properties.get(guild).getProperty(key);
		else {
			ServerProperties props = new ServerProperties(guild);
			properties.put(guild,props);
			return props.getProperty(key);
		}
	}
	
	private void setProperty(String key,String value,Guild guild) {
		if(properties.containsKey(guild))
			properties.get(guild).setProperty(key, value);
		else {
			ServerProperties props = new ServerProperties(guild);
			properties.put(guild,props);
			props.setProperty(key,value);
		}
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		//TODO Load Prefix from properties
		Message m = event.getMessage();
		String content = m.getContentRaw();
		String prefix = getProperty("command.prefix",m.getGuild()).trim();
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
