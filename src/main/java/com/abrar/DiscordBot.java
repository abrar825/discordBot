package com.abrar;

import javax.security.auth.login.LoginException;

import com.abrar.listeners.EventListener;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManager;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

/**
 * Hello world!
 *
 */
public class DiscordBot {

    private final ShardManager shardmanager;
    private final Dotenv config;

    public DiscordBot() throws LoginException {
        config = Dotenv.configure().ignoreIfMissing().load();
        String token = config.get("TOKEN");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("with Naveen"));
        shardmanager = builder.build();

        // Gateway Intents
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES);
        // builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);

        // Register Listener
        shardmanager.addEventListener(new EventListener());

    }

    public ShardManager getShardManager() {
        return shardmanager;
    }

    public static void main(String[] args) {
        try {
            DiscordBot bot = new DiscordBot();
        } catch (LoginException e) {
            System.out.println("LOGIN ERROR");
        }

    }
}
