package github.lightnincreations.hackweekbot.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.CommandNode;

import github.lightnincreations.hackweekbot.EventHandler;
import net.dv8tion.jda.api.Permission;

public class SetPrefixCommand {

	public static void registerCommand(CommandDispatcher<CommandSource> dispatcher) {
		System.out.println("Registering setprefix Command");
		CommandNode<CommandSource> node = LiteralArgumentBuilder.<CommandSource>literal("setprefix")
		.requires(src->src.hasDiscordPermission(Permission.MANAGE_SERVER))
		.build();
		node.addChild(RequiredArgumentBuilder
				.<CommandSource,String>argument("prefix", CommandPrefixArgumentType.instance)
				.executes(SetPrefixCommand::runSetPrefixCommand)
				.build());
		dispatcher.getRoot().addChild(node);
	}
	
	private static int runSetPrefixCommand(CommandContext<CommandSource> ctx) {
		String newprefix = ctx.getArgument("prefix", String.class);
		EventHandler.setProperty("command.prefix", newprefix, ctx.getSource().getGuild());
		ctx.getSource().sendMessage("Set prefix to "+newprefix);
		return Command.SINGLE_SUCCESS;
	}

}
