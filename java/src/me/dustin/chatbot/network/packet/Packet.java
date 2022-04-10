package me.dustin.chatbot.network.packet;

import me.dustin.chatbot.ChatBot;
import me.dustin.chatbot.network.ClientConnection;
import me.dustin.chatbot.network.packet.pipeline.PacketByteBuf;
import me.dustin.chatbot.network.packet.handler.ClientBoundPacketHandler;

import java.io.IOException;

public class Packet {

    private final int packetId;

    public Packet(int packetId) {
        this.packetId = packetId;
    }

    public void createPacket(PacketByteBuf packetByteBuf) throws IOException {
    }

    public static abstract class ClientBoundPacket extends Packet {
        protected final ClientBoundPacketHandler clientBoundPacketHandler;

        public ClientBoundPacket(int packetId, ClientBoundPacketHandler clientBoundPacketHandler) {
            super(packetId);
            this.clientBoundPacketHandler = clientBoundPacketHandler;
        }

        public abstract void apply();
    }

    public ClientConnection getClientConnection() {
        return ChatBot.getClientConnection();
    }

    public int getPacketId() {
        return packetId;
    }
}
