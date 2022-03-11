package me.dustin.chatbot.command.impl;

import me.dustin.chatbot.ChatBot;
import me.dustin.chatbot.command.Command;
import me.dustin.chatbot.network.packet.c2s.play.ServerBoundClientSettingsPacket;

import java.util.UUID;

public class CommandReload extends Command {

    public CommandReload() {
        super("reload");
    }

    @Override
    public void run(String str, UUID sender) {
        getClientConnection().getCommandManager().init();
        try {
            ChatBot.getConfig().loadConfig();
            getClientConnection().sendPacket(new ServerBoundClientSettingsPacket(ChatBot.getConfig().getLocale(), ChatBot.getConfig().isAllowServerListing()));
            sendChat("Reloaded commands and config!");
        } catch (Exception e) {
            sendChat("Error in config! " + e.getMessage());
        }
    }
}
