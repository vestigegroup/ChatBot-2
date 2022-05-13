package me.dustin.chatbot.nbt;

import com.google.gson.JsonElement;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface NbtElement {
    public static final int END_TYPE = 0;
    public static final int BYTE_TYPE = 1;
    public static final int SHORT_TYPE = 2;
    public static final int INT_TYPE = 3;
    public static final int LONG_TYPE = 4;
    public static final int FLOAT_TYPE = 5;
    public static final int DOUBLE_TYPE = 6;
    public static final int BYTE_ARRAY_TYPE = 7;
    public static final int STRING_TYPE = 8;
    public static final int LIST_TYPE = 9;
    public static final int COMPOUND_TYPE = 10;
    public static final int INT_ARRAY_TYPE = 11;
    public static final int LONG_ARRAY_TYPE = 12;

    public static NbtElement read(int type, DataInput input, int depth) {
        try {
            switch (type) {
                case END_TYPE -> {
                    return new NbtEnd();
                }
                case BYTE_TYPE -> {
                    return NbtByte.read(input, depth);
                }
                case SHORT_TYPE -> {
                    return NbtShort.read(input, depth);
                }
                case INT_TYPE -> {
                    return NbtInt.read(input, depth);
                }
                case LONG_TYPE -> {
                    return NbtLong.read(input, depth);
                }
                case FLOAT_TYPE -> {
                    return NbtFloat.read(input, depth);
                }
                case DOUBLE_TYPE -> {
                    return NbtDouble.read(input, depth);
                }
                case BYTE_ARRAY_TYPE -> {
                    return NbtByteArray.read(input, depth);
                }
                case STRING_TYPE -> {
                    return NbtString.read(input, depth);
                }
                case LIST_TYPE -> {
                    return NbtList.read(input, depth);
                }
                case COMPOUND_TYPE -> {
                    return NbtCompound.read(input, depth);
                }
                case INT_ARRAY_TYPE -> {
                    return NbtIntArray.read(input, depth);
                }
                case LONG_ARRAY_TYPE -> {
                    return NbtLongArray.read(input, depth);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public default int getType() {
        if (this instanceof NbtEnd)
            return END_TYPE;
        if (this instanceof NbtByte)
            return BYTE_TYPE;
        if (this instanceof NbtShort)
            return SHORT_TYPE;
        if (this instanceof NbtInt)
            return INT_TYPE;
        if (this instanceof NbtLong)
            return LONG_TYPE;
        if (this instanceof NbtFloat)
            return FLOAT_TYPE;
        if (this instanceof NbtDouble)
            return DOUBLE_TYPE;
        if (this instanceof NbtByteArray)
            return BYTE_ARRAY_TYPE;
        if (this instanceof NbtString)
            return STRING_TYPE;
        if (this instanceof NbtList)
            return LIST_TYPE;
        if (this instanceof NbtCompound)
            return COMPOUND_TYPE;
        if (this instanceof NbtIntArray)
            return INT_ARRAY_TYPE;
        if (this instanceof NbtLongArray)
            return LONG_ARRAY_TYPE;
        return 0;
    }

    void write(DataOutput dataOutput) throws IOException;
    Object getValue();
    JsonElement toJson();
}
