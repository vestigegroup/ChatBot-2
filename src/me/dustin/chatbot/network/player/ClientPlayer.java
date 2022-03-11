package me.dustin.chatbot.network.player;

import me.dustin.chatbot.ChatBot;
import me.dustin.chatbot.helper.GeneralHelper;
import me.dustin.chatbot.network.ClientConnection;
import me.dustin.chatbot.network.packet.c2s.play.ServerBoundPlayerRotationPacket;
import me.dustin.chatbot.network.packet.c2s.play.ServerBoundPlayerSwingPacket;

import java.util.Random;
import java.util.UUID;

public class ClientPlayer {

    private final UUID uuid;
    private final String name;
    private final ClientConnection clientConnection;

    private double x,y,z;
    private float yaw, pitch;

    private long afkTimer = -1;
    private long lastKeepAlive = -1;

    public ClientPlayer(String name, UUID uuid, ClientConnection clientConnection) {
        this.name = name;
        this.uuid = uuid;
        this.clientConnection = clientConnection;
    }

    public void tick() {
        if (getClientConnection().getNetworkState() == ClientConnection.NetworkState.PLAY) {
            if (System.currentTimeMillis() - lastKeepAlive >= ChatBot.getConfig().getKeepAliveCheckTime() * 1000L) {
                GeneralHelper.print("Time out detected, closing connection.", GeneralHelper.ANSI_PURPLE);
                getClientConnection().close();
                return;
            }
            if (ChatBot.getConfig().isAntiAFK() && System.currentTimeMillis() - afkTimer >= ChatBot.getConfig().getAntiAFKDelay() * 1000L) {
                Random random = new Random();
                float yaw = random.nextInt(360) - 180;
                float pitch = random.nextInt(180) - 90;
                getClientConnection().sendPacket(new ServerBoundPlayerRotationPacket(yaw, pitch, true));
                getClientConnection().sendPacket(new ServerBoundPlayerSwingPacket(ServerBoundPlayerSwingPacket.MAIN_HAND));
                updateAntiAFK();
            }
        }
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

    public void updateKeepAlive() {
        this.lastKeepAlive = System.currentTimeMillis();
    }

    public void updateAntiAFK() {
        this.afkTimer = System.currentTimeMillis();
    }
}
