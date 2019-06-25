package github.lightnincreations.hackweekbot.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class CommandSource {
	private final TextChannel channel;
	private final Guild guild;
	private final Member member;
	
	public CommandSource(TextChannel channel,Guild guild,Member member) {
		this.channel = channel;
		this.guild = guild;
		this.member = member;
	}

	public TextChannel getChannel() {
		return channel;
	}

	public Guild getGuild() {
		return guild;
	}

	public Member getMember() {
		return member;
	}
}
