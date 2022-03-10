package me.dustin.chatbot.command.impl;

import me.dustin.chatbot.command.Command;
import me.dustin.chatbot.network.player.OtherPlayer;

import java.util.UUID;

public class CommandWorstPing extends Command {
    public CommandWorstPing() {
        super("worstping");
        getAlias().add("wp");
    }

    @Override
    public void run(String str, UUID sender) {
        if (getClientConnection().getPlayerManager().getPlayers().isEmpty()) {
            sendChat("Error! I don't see any players :(");
            return;
        }
        int worst = -1;
        OtherPlayer player = null;

        for (OtherPlayer instancePlayer : getClientConnection().getPlayerManager().getPlayers()) {
            if (player == null || instancePlayer.getPing() > worst) {
                player = instancePlayer;
                worst = player.getPing();
            }
        }
        sendChat(player.getName() + " has the highest ping at " + worst + "ms");
    }
}
