package github.lightnincreations.hackweekbot.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.TextChannel;

public class PingCommand {
	public static void registerCommand(CommandDispatcher<CommandSource> src) {
		src
		.getRoot()
		.addChild(LiteralArgumentBuilder
				.<CommandSource>literal("ping")
				.executes(PingCommand::runPingCommand)
				.build());
		System.out.println("Regstering Ping Command");
	}
	public static int runPingCommand(CommandContext<CommandSource> ctx) {
		System.out.println("Running Command: ping");
		CommandSource src = ctx.getSource();
		TextChannel channel = src.getChannel();
		channel.sendMessage("Pong!");
		return Command.SINGLE_SUCCESS;
	}
}
