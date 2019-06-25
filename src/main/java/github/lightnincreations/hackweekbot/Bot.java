package github.lightnincreations.hackweekbot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Bot {
	public static void main(String[] args) throws LoginException {
		String token = args[0];
		JDA jda = new JDABuilder(token)
				.addEventListeners(new EventHandler())
				.build();
	}
}
