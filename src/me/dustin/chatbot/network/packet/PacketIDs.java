package me.dustin.chatbot.network.packet;

import me.dustin.chatbot.network.Protocols;

public class PacketIDs {

    public enum ClientBound {
        CHAT_MESSAGE(0x02, 0x02, 0x02, 0x0F, 0x0F, 0x0F, 0x0F, 0x0F, 0x0F, 0x0F, 0x0F, 0x0F, 0x0F, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x0F, 0x0F, 0x0F, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x0F, 0x0F, 0x0F, 0x0F),
        COMBAT_EVENT(-1, -1, 0x42, 0x2C, 0x2C, 0x2C, 0x2C, 0x2C, 0x2C, 0x2C, 0x2C, 0x2D, 0x2D, 0x2F, 0x2F, 0x2F, 0x32, 0x32, 0x32, 0x32, 0x32, 0x33, 0x33, 0x33, 0x32, 0x32, 0x31, 0x31, 0x31, 0x35, 0x35, 0x35, 0x35),
        DISCONNECT(0x40, 0x40, 0x40, 0x1A, 0x1A, 0x1A, 0x1A, 0x1A, 0x1A, 0x1A, 0x1A, 0x1A, 0x1A, 0x1B, 0x1B, 0x1B, 0x1A, 0x1A, 0x1A, 0x1A, 0x1A, 0x1B, 0x1B, 0x1B, 0x1A, 0x1A, 0x19, 0x19, 0x19, 0x1A, 0x1A, 0x1A, 0x1A),
        KEEP_ALIVE(0x00, 0x00, 0x00, 0x1F, 0x1F, 0x1F, 0x1F, 0x1F, 0x1F, 0x1F, 0x1F, 0x1F, 0x1F, 0x21, 0x21, 0x21, 0x20, 0x20, 0x20, 0x20, 0x20, 0x21, 0x21, 0x21, 0x20, 0x20, 0x19, 0x19, 0x19, 0x21, 0x21, 0x21, 0x21),
        PLAYER_INFO(0x38,0x38, 0x38, 0x2D, 0x2D, 0x2D, 0x2D, 0x2D, 0x2D, 0x2D, 0x2D, 0x2E, 0x2E, 0x30, 0x30, 0x30, 0x33, 0x33, 0x33, 0x33, 0x33, 0x34, 0x34, 0x34, 0x33, 0x33, 0x32, 0x32, 0x32, 0x36, 0x36, 0x36, 0x36),
        PLAYER_POS_LOOK(0x06, 0x06, 0x08, 0x2E, 0x2E, 0x2E, 0x2E, 0x2E, 0x2E, 0x2E, 0x2E, 0x2F, 0x2F, 0x32, 0x32, 0x32, 0x35, 0x35, 0x35, 0x35, 0x35, 0x36, 0x36, 0x36, 0x35, 0x35, 0x34, 0x34, 0x34, 0x38, 0x38, 0x38, 0x38),
        RESOURCE_PACK_SEND(-1, -1, 0x48, 0x32, 0x32, 0x32, 0x32, 0x32, 0x32, 0x32, 0x33, 0x34, 0x34, 0x37, 0x37, 0x37, 0x39, 0x39, 0x39, 0x39, 0x39, 0x3A, 0x3A, 0x3A, 0x39, 0x39, 0x38, 0x38, 0x38, 0x3C, 0x3C, 0x3C, 0x3C),
        TAB_COMPLETE(0x3A, 0x3A, 0x3A, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x11, 0x11, 0x11, 0x10, 0x10, 0x0F, 0x0F, 0x0F, 0x11, 0x11, 0x11, 0x11),
        UPDATE_HEALTH(0x06, 0x06, 0x06, 0x3E, 0x3E, 0x3E, 0x3E, 0x3E, 0x3E, 0x3E, 0x40, 0x41, 0x41, 0x44, 0x44, 0x44, 0x48, 0x48, 0x48, 0x48, 0x48, 0x49, 0x49, 0x49, 0x4A, 0x4A, 0x49, 0x49, 0x49, 0x52, 0x52, 0x52, 0x52),
        WORLD_TIME(0x03, 0x03, 0x03, 0x44, 0x44, 0x44, 0x44, 0x44, 0x44, 0x44, 0x46, 0x47, 0x47, 0x4A, 0x4A, 0x4A, 0x4E, 0x4E, 0x4E, 0x4E, 0x4E, 0x4F, 0x4F, 0x4F, 0x4E, 0x4E, 0x4E, 0x4E, 0x4E, 0x59, 0x59, 0x59, 0x59),
        JOIN_GAME(0x01, 0x01, 0x01, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x25, 0x26, 0x26, 0x26, 0x25, 0x25, 0x24, 0x24, 0x24, 0x26, 0x26, 0x26, 0x26);

        private final int v1_7_2, v1_7_10, v1_8, v1_9, v1_9_1, v1_9_2, v1_9_4, v1_10, v1_11, v1_11_2, v1_12, v1_12_1, v1_12_2, v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_5, v1_17, v1_17_1, v1_18, v1_18_2;

        ClientBound(int v1_7_2, int v1_7_10, int v1_8, int v1_9, int v1_9_1, int v1_9_2, int v1_9_4, int v1_10, int v1_11, int v1_11_2, int v1_12, int v1_12_1, int v1_12_2, int v1_13, int v1_13_1, int v1_13_2, int v1_14, int v1_14_1, int v1_14_2, int v1_14_3, int v1_14_4, int v1_15, int v1_15_1, int v1_15_2, int v1_16, int v1_16_1, int v1_16_2, int v1_16_3, int v1_16_5, int v1_17, int v1_17_1, int v1_18, int v1_18_2) {
            this.v1_7_2 = v1_7_2;
            this.v1_7_10 = v1_7_10;
            this.v1_8 = v1_8;
            this.v1_9 = v1_9;
            this.v1_9_1 = v1_9_1;
            this.v1_9_2 = v1_9_2;
            this.v1_9_4 = v1_9_4;
            this.v1_10 = v1_10;
            this.v1_11 = v1_11;
            this.v1_11_2 = v1_11_2;
            this.v1_12 = v1_12;
            this.v1_12_1 = v1_12_1;
            this.v1_12_2 = v1_12_2;
            this.v1_13 = v1_13;
            this.v1_13_1 = v1_13_1;
            this.v1_13_2 = v1_13_2;
            this.v1_14 = v1_14;
            this.v1_14_1 = v1_14_1;
            this.v1_14_2 = v1_14_2;
            this.v1_14_3 = v1_14_3;
            this.v1_14_4 = v1_14_4;
            this.v1_15 = v1_15;
            this.v1_15_1 = v1_15_1;
            this.v1_15_2 = v1_15_2;
            this.v1_16 = v1_16;
            this.v1_16_1 = v1_16_1;
            this.v1_16_2 = v1_16_2;
            this.v1_16_3 = v1_16_3;
            this.v1_16_5 = v1_16_5;
            this.v1_17 = v1_17;
            this.v1_17_1 = v1_17_1;
            this.v1_18 = v1_18;
            this.v1_18_2 = v1_18_2;
        }

        public int getPacketId() {
            return switch (Protocols.getCurrent()) {
                case V1_7_2 -> v1_7_2;
                case V1_7_10 -> v1_7_10;
                case V1_8 -> v1_8;
                case V1_9 -> v1_9;
                case V1_9_1 -> v1_9_1;
                case V1_9_2 -> v1_9_2;
                case V1_9_4 -> v1_9_4;
                case V1_10 -> v1_10;
                case V1_11 -> v1_11;
                case V1_11_2 -> v1_11_2;
                case V1_12 -> v1_12;
                case V1_12_1 -> v1_12_1;
                case V1_12_2 -> v1_12_2;
                case V1_13 -> v1_13;
                case V1_13_1 -> v1_13_1;
                case V1_13_2 -> v1_13_2;
                case V1_14 -> v1_14;
                case V1_14_1 -> v1_14_1;
                case V1_14_2 -> v1_14_2;
                case V1_14_3 -> v1_14_3;
                case V1_14_4 -> v1_14_4;
                case V1_15 -> v1_15;
                case V1_15_1 -> v1_15_1;
                case V1_15_2 -> v1_15_2;
                case V1_16 -> v1_16;
                case V1_16_1 -> v1_16_1;
                case V1_16_2 -> v1_16_2;
                case V1_16_3 -> v1_16_3;
                case V1_16_5 -> v1_16_5;
                case V1_17 -> v1_17;
                case V1_17_1 -> v1_17_1;
                case V1_18 -> v1_18;
                case V1_18_2 -> v1_18_2;
            };
        }
    }

    public enum ServerBound {
        CHAT_MESSAGE(0x01, 0x01,0x01, 0x02, 0x02, 0x02, 0x02, 0x02, 0x02, 0x02, 0x03, 0x02, 0x02, 0x02, 0x02, 0x02, 0x03, 0x03, 0x03, 0x03, 0x03, 0x03, 0x03, 0x03, 0x03,0x03, 0x03, 0x03, 0x03, 0x03, 0x03, 0x03, 0x03),
        CLIENT_SETTINGS(0x15, 0x15, 0x15, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x05, 0x04, 0x04, 0x04, 0x04, 0x04, 0x05, 0x05, 0x05, 0x05, 0x05, 0x05, 0x05, 0x05, 0x05, 0x05, 0x05, 0x05, 0x05, 0x05, 0x05, 0x05, 0x05),
        CLIENT_STATUS(0x16, 0x16, 0x16, 0x03, 0x03, 0x03, 0x03, 0x03, 0x03, 0x03, 0x04, 0x03, 0x03, 0x03, 0x03, 0x03, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04),
        CONFIRM_TELEPORT(-1, -1, -1, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00),
        KEEP_ALIVE(0x00, 0x00, 0x00, 0x0B, 0x0B, 0x0B, 0x0B, 0x0B, 0x0B, 0x0B, 0x0C, 0x0B, 0x0B, 0x0E, 0x0E, 0x0E, 0x10, 0x0F, 0x0F, 0x0F, 0x0F, 0x0F, 0x0F, 0x0F, 0x10, 0x10, 0x10, 0x10, 0x10, 0x0F, 0x0F, 0x0F, 0x0F),
        PLAYER_ROTATION(0x05, 0x05, 0x05, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x0E, 0x10, 0x0F, 0x0F, 0x12, 0x12, 0x12, 0x13, 0x13, 0x13, 0x13, 0x13, 0x13, 0x13, 0x13, 0x14, 0x14, 0x14, 0x14, 0x14, 0x13, 0x13, 0x13, 0x13),
        PLAYER_SWING(0x0A, 0x0A,0x0A, 0x1A, 0x1A, 0x1A, 0x1A, 0x1A, 0x1A, 0x1A, 0x1D, 0x1D, 0x1D, 0x27, 0x27, 0x27, 0x2A, 0x2A, 0x2A, 0x2A, 0x2A, 0x2A, 0x2A, 0x2A, 0x2B, 0x2B, 0x2C, 0x2C, 0x2C, 0x2C, 0x2C, 0x2C, 0x2C),
        RESOURCE_PACK_STATUS(-1, -1,0x19, 0x16, 0x16, 0x16, 0x16, 0x16, 0x16, 0x16,0x18, 0x18, 0x18, 0x1D, 0x1D, 0x1D, 0x1F, 0x1F, 0x1F, 0x1F, 0x1F, 0x1F, 0x1F, 0x1F, 0x20, 0x20, 0x21, 0x21, 0x21, 0x21, 0x21, 0x21, 0x21),
        TAB_COMPLETE(0x14, 0x14, 0x14, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x02, 0x01, 0x01, 0x05, 0x05, 0x05, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06, 0x06);

        private final int v1_7_2, v1_7_10, v1_8, v1_9, v1_9_1, v1_9_2, v1_9_4, v1_10, v1_11, v1_11_2, v1_12, v1_12_1, v1_12_2, v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_5, v1_17, v1_17_1, v1_18, v1_18_2;

        ServerBound(int v1_7_2, int v1_7_10, int v1_8, int v1_9, int v1_9_1, int v1_9_2, int v1_9_4, int v1_10, int v1_11, int v1_11_2, int v1_12, int v1_12_1, int v1_12_2, int v1_13, int v1_13_1, int v1_13_2, int v1_14, int v1_14_1, int v1_14_2, int v1_14_3, int v1_14_4, int v1_15, int v1_15_1, int v1_15_2, int v1_16, int v1_16_1, int v1_16_2, int v1_16_3, int v1_16_5, int v1_17, int v1_17_1, int v1_18, int v1_18_2) {
            this.v1_7_2 = v1_7_2;
            this.v1_7_10 = v1_7_10;
            this.v1_8 = v1_8;
            this.v1_9 = v1_9;
            this.v1_9_1 = v1_9_1;
            this.v1_9_2 = v1_9_2;
            this.v1_9_4 = v1_9_4;
            this.v1_10 = v1_10;
            this.v1_11 = v1_11;
            this.v1_11_2 = v1_11_2;
            this.v1_12 = v1_12;
            this.v1_12_1 = v1_12_1;
            this.v1_12_2 = v1_12_2;
            this.v1_13 = v1_13;
            this.v1_13_1 = v1_13_1;
            this.v1_13_2 = v1_13_2;
            this.v1_14 = v1_14;
            this.v1_14_1 = v1_14_1;
            this.v1_14_2 = v1_14_2;
            this.v1_14_3 = v1_14_3;
            this.v1_14_4 = v1_14_4;
            this.v1_15 = v1_15;
            this.v1_15_1 = v1_15_1;
            this.v1_15_2 = v1_15_2;
            this.v1_16 = v1_16;
            this.v1_16_1 = v1_16_1;
            this.v1_16_2 = v1_16_2;
            this.v1_16_3 = v1_16_3;
            this.v1_16_5 = v1_16_5;
            this.v1_17 = v1_17;
            this.v1_17_1 = v1_17_1;
            this.v1_18 = v1_18;
            this.v1_18_2 = v1_18_2;
        }

        public int getPacketId() {
            return switch (Protocols.getCurrent()) {
                case V1_7_2 -> v1_7_2;
                case V1_7_10 -> v1_7_10;
                case V1_8 -> v1_8;
                case V1_9 -> v1_9;
                case V1_9_1 -> v1_9_1;
                case V1_9_2 -> v1_9_2;
                case V1_9_4 -> v1_9_4;
                case V1_10 -> v1_10;
                case V1_11 -> v1_11;
                case V1_11_2 -> v1_11_2;
                case V1_12 -> v1_12;
                case V1_12_1 -> v1_12_1;
                case V1_12_2 -> v1_12_2;
                case V1_13 -> v1_13;
                case V1_13_1 -> v1_13_1;
                case V1_13_2 -> v1_13_2;
                case V1_14 -> v1_14;
                case V1_14_1 -> v1_14_1;
                case V1_14_2 -> v1_14_2;
                case V1_14_3 -> v1_14_3;
                case V1_14_4 -> v1_14_4;
                case V1_15 -> v1_15;
                case V1_15_1 -> v1_15_1;
                case V1_15_2 -> v1_15_2;
                case V1_16 -> v1_16;
                case V1_16_1 -> v1_16_1;
                case V1_16_2 -> v1_16_2;
                case V1_16_3 -> v1_16_3;
                case V1_16_5 -> v1_16_5;
                case V1_17 -> v1_17;
                case V1_17_1 -> v1_17_1;
                case V1_18 -> v1_18;
                case V1_18_2 -> v1_18_2;
            };
        }
    }
}
