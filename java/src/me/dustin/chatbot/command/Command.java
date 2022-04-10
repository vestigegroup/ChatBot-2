package me.dustin.chatbot.command;

import me.dustin.chatbot.network.ClientConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Command {

    private final String name;
    private final List<String> alias = new ArrayList<>();
    private ClientConnection clientConnection;

    public Command(String name) {
        this.name = name;
    }

    public abstract void run(String str, UUID sender);

    public String getName() {
        return name;
    }

    public List<String> getAlias() {
        return alias;
    }

    public ClientConnection getClientConnection() {
        return clientConnection;
    }

    public void setClientConnection(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    public void sendChat(String message) {
        getClientConnection().getClientPlayer().chat(message);
    }
}