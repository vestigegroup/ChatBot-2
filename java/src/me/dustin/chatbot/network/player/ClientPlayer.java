package me.dustin.chatbot.network.player;

import me.dustin.chatbot.ChatBot;
import me.dustin.chatbot.helper.StopWatch;
import me.dustin.chatbot.network.ClientConnection;
import me.dustin.chatbot.network.packet.ProtocolHandler;
import me.dustin.chatbot.network.packet.c2s.play.ServerBoundChatPacket;
import me.dustin.chatbot.network.packet.c2s.play.ServerBoundPlayerOnGroundPacket;
import me.dustin.chatbot.network.packet.c2s.play.ServerBoundPlayerPositionPacket;

import java.util.UUID;

public class ClientPlayer {

    private final UUID uuid;
    private final String name;
    private final ClientConnection clientConnection;

    private int entityId;
    private double x,y,z;
    private float yaw, pitch;
    private int ticks;

    private OtherPlayer.GameMode gameMode = OtherPlayer.GameMode.SURVIVAL;

    private final StopWatch messageStopwatch = new StopWatch();

    private String lastMessage = "";
    private boolean below1_9;
    public ClientPlayer(String name, UUID uuid, ClientConnection clientConnection) {
        this.name = name;
        this.uuid = uuid;
        this.clientConnection = clientConnection;
        below1_9 = ProtocolHandler.getCurrent().getProtocolVer() < ProtocolHandler.getVersionFromName("1.9").getProtocolVer();
    }

    public void tick() {
        if (below1_9) {
            if (ticks % 20 == 0) {
                getClientConnection().sendPacket(new ServerBoundPlayerPositionPacket(getX(), getY(), getZ(), true));
            } else {
                getClientConnection().sendPacket(new ServerBoundPlayerOnGroundPacket(true));
            }
        } else if (ticks % 20 == 0) {
            getClientConnection().sendPacket(new ServerBoundPlayerPositionPacket(getX(), getY(), getZ(), true));
        }
        ticks++;
    }

    public void chat(String message) {
        if ((!ChatBot.getConfig().isRepeatMessages() && lastMessage.equalsIgnoreCase(message)) || !messageStopwatch.hasPassed(ChatBot.getConfig().getMessageDelay())) {
            return;
        }
        getClientConnection().sendPacket(new ServerBoundChatPacket((ChatBot.getConfig().isGreenText() && !message.startsWith("/") ? ">" : "") + message));
        messageStopwatch.reset();
        lastMessage = message;
    }

    public ClientConnection getClientConnection() {
        return clientConnection;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public OtherPlayer.GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(OtherPlayer.GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void moveX(double x) {
        this.x += x;
    }

    public void moveY(double y) {
        this.y += y;
    }

    public void moveZ(double z) {
        this.z += z;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void moveYaw(float yaw) {
        this.yaw += yaw;
    }

    public void movePitch(float pitch) {
        this.pitch += pitch;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
}
