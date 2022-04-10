package me.dustin.chatbot.network.packet.c2s.play;

import me.dustin.chatbot.network.packet.Packet;
import me.dustin.chatbot.network.packet.PacketIDs;
import me.dustin.chatbot.network.packet.pipeline.PacketByteBuf;

import java.io.IOException;

public class ServerBoundResourcePackStatusPacket extends Packet {

    public static final int SUCCESSFULLY_LOADED = 0, DECLINED = 1, FAILED_DL = 2, ACCEPTED = 3;

    private final int result;

    public ServerBoundResourcePackStatusPacket(int result) {
        super(PacketIDs.ServerBound.RESOURCE_PACK_STATUS.getPacketId());
        this.result = result;
    }

    @Override
    public void createPacket(PacketByteBuf packetByteBuf) throws IOException {
        packetByteBuf.writeVarInt(result);
    }
}