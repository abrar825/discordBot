package com.abrar.commands;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class CommandManager extends ListenerAdapter {

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equalsIgnoreCase("welcome")) {
            String userTag = event.getUser().getAsTag();
            event.reply("Welcome, **" + userTag + "**!").queue();
        }
    }

    public void onGuildReady(GuildReadyEvent event) {

        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("welcome", "Get welcomed"));
        commandData.add(Commands.slash("roles", "Display all roles on server"));

        // Command: /say <message> [channel]
        OptionData option1 = new OptionData(OptionType.STRING, "message", "The message the bot should say", true);
        commandData.add(Commands.slash("say", "Make the bot say something.").addOptions(option1));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

}