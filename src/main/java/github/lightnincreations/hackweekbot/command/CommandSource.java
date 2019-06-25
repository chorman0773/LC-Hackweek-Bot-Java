package github.lightnincreations.hackweekbot.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;

public class CommandSource {
	private final GuildChannel channel;
	private final Guild guild;
	private final Member member;
	
	public CommandSource(GuildChannel channel,Guild guild,Member member) {
		this.channel = channel;
		this.guild = guild;
		this.member = member;
	}

	public GuildChannel getChannel() {
		return channel;
	}

	public Guild getGuild() {
		return guild;
	}

	public Member getMember() {
		return member;
	}
}
