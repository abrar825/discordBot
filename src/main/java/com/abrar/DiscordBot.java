package com.abrar;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManager;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

/**
 * Hello world!
 *
 */
public class DiscordBot {

    private final ShardManager shardmanager;

    public DiscordBot() throws LoginException {
        String token = "MTA4MzY1OTEzMjEzOTIyOTE5NA.G8-v02.TcAdTa0Jqdh2aCXkOwAkoLLEllNzRlg_w3CgBg";
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("with Naveen"));
        shardmanager = builder.build();
        System.out.println("Good!");

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
