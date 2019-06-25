package github.lightnincreations.hackweekbot.command;

import com.mojang.brigadier.CommandDispatcher;

public class CommandRegistry {

	private CommandRegistry() {
		// TODO Auto-generated constructor stub
	}
	
	public static void registerCommands(CommandDispatcher<CommandSource> dispatcher) {
		System.out.println("Registering Commands");
		PingCommand.registerCommand(dispatcher);
		SetPrefixCommand.registerCommand(dispatcher);
	}

}
