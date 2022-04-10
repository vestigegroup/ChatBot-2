package me.dustin.chatbot.network.packet.c2s.play;

import me.dustin.chatbot.network.packet.Packet;
import me.dustin.chatbot.network.packet.PacketIDs;
import me.dustin.chatbot.network.packet.pipeline.PacketByteBuf;

import java.io.IOException;

public class ServerBoundConfirmTeleportPacket extends Packet {

    private final int id;

    public ServerBoundConfirmTeleportPacket(int id) {
        super(PacketIDs.ServerBound.CONFIRM_TELEPORT.getPacketId());
        this.id = id;
    }

    @Override
    public void createPacket(PacketByteBuf packetByteBuf) throws IOException {
        packetByteBuf.writeVarInt(id);
    }
}
