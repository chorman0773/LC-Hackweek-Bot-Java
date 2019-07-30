package github.lightnincreations.hackweekbot.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

public class IdCommand {
	public static void registerCommand(CommandDispatcher<CommandSource> dispatcher) {
		System.out.println("Registering id Command");
		dispatcher.getRoot()
			.addChild(LiteralArgumentBuilder.<CommandSource>literal("id")
					.executes(IdCommand::runIdCommand)
					.build());
	}
	private static int runIdCommand(CommandContext<CommandSource> src) {
		src.getSource().sendMessage("This is the Java Version");
		return Command.SINGLE_SUCCESS;
	}
}
