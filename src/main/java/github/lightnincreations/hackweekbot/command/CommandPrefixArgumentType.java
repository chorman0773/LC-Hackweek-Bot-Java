package github.lightnincreations.hackweekbot.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class CommandPrefixArgumentType implements ArgumentType<String> {
	public static final CommandPrefixArgumentType instance = new CommandPrefixArgumentType();
	private CommandPrefixArgumentType() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String parse(StringReader reader) throws CommandSyntaxException {
		StringBuilder build = new StringBuilder();
		char c;
		while(reader.canRead() && !Character.isWhitespace(c = reader.peek())) {
			reader.read();
			build.append(c);
		}
		return build.toString();
	}

}
