package com.abrar.commands;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class CommandManager extends ListenerAdapter {

    MessageChannel archChannel;

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equalsIgnoreCase("welcome")) {
            String userTag = event.getUser().getAsTag();
            event.reply("Welcome, **" + userTag + "**!").queue();
        } else if (command.equalsIgnoreCase("setarchive")) {
            archChannel = event.getOption("archchannel").getAsTextChannel();
            event.reply("Archive channel set to " + archChannel.getAsMention()).queue();
        }
    }

    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (archChannel == null) {
            event.getChannel().sendMessage("Set a archive channel!").queue();
            return;
        }
        String message = event.retrieveMessage().complete().toString();
        System.out.println(message);
        archChannel.sendMessage(message).queue();
    }

    public void onGuildReady(GuildReadyEvent event) {

        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("welcome", "Get welcomed"));
        commandData.add(Commands.slash("roles", "Display all roles on server"));

        // Command: /say <message> [channel]
        OptionData option1 = new OptionData(OptionType.STRING, "message", "The message the bot should say", true);
        commandData.add(Commands.slash("say", "Make the bot say something.").addOptions(option1));

        // Command: /setArchive [channel]
        OptionData archChannel = new OptionData(OptionType.CHANNEL, "archchannel",
                "The channel where archives are stored.", true);
        commandData
                .add(Commands.slash("setarchive", "Set the channel where archives are stored").addOptions(archChannel));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

}
