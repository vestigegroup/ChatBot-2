package me.dustin.chatbot.network.packet.impl.login.c2s;

import me.dustin.chatbot.network.key.SaltAndSig;
import me.dustin.chatbot.network.packet.Packet;
import me.dustin.chatbot.network.ProtocolHandler;
import me.dustin.chatbot.network.packet.pipeline.PacketByteBuf;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ServerBoundEncryptionResponsePacket extends Packet {
    private final byte[] encryptedSecret;
    private final byte[] encryptedVerify;
    private final SaltAndSig saltAndSig;

    public ServerBoundEncryptionResponsePacket(byte[] encryptedSecret, byte[] encryptedVerify) {
        super(0x01);
        this.encryptedSecret = encryptedSecret;
        this.encryptedVerify = encryptedVerify;
        this.saltAndSig = null;
    }

    public ServerBoundEncryptionResponsePacket(byte[] encryptedSecret, SaltAndSig saltAndSig) {
        super(0x01);
        this.encryptedSecret = encryptedSecret;
        this.encryptedVerify = null;
        this.saltAndSig = saltAndSig;
    }

    public static SecretKey generateSecret() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createPacket(PacketByteBuf packetByteBuf) throws IOException {
        if (ProtocolHandler.getCurrent().getProtocolVer() > ProtocolHandler.getVersionFromName("1.18.2").getProtocolVer()) {
            packetByteBuf.writeByteArray(encryptedSecret);
            if (saltAndSig == null) {
                packetByteBuf.writeBoolean(true);
                packetByteBuf.writeByteArray(encryptedVerify);
            } else {
                packetByteBuf.writeBoolean(false);
                saltAndSig.write(packetByteBuf);
            }
            return;
        }
        if (ProtocolHandler.getCurrent().getProtocolVer() <= ProtocolHandler.getVersionFromName("1.7.10").getProtocolVer())
            packetByteBuf.writeShort(encryptedSecret.length);
        else
            packetByteBuf.writeVarInt(encryptedSecret.length);
        packetByteBuf.writeBytes(encryptedSecret);

        if (ProtocolHandler.getCurrent().getProtocolVer() <= ProtocolHandler.getVersionFromName("1.7.10").getProtocolVer())
            packetByteBuf.writeShort(encryptedVerify.length);
        else
            packetByteBuf.writeVarInt(encryptedVerify.length);
        packetByteBuf.writeBytes(encryptedVerify);
    }
}
