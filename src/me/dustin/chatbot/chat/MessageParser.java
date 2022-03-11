package me.dustin.chatbot.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.dustin.chatbot.ChatBot;
import me.dustin.chatbot.helper.GeneralHelper;

public enum MessageParser {
    INSTANCE;

    public ChatMessage parse(String jsonData) {
        StringBuilder name = new StringBuilder();
        StringBuilder body = new StringBuilder();

        JsonObject jsonObject = GeneralHelper.gson.fromJson(jsonData, JsonObject.class);
        JsonArray with = jsonObject.getAsJsonArray("with");
        if (with != null) {
            for (int i = 0; i < with.size(); i++) {
                try {
                    JsonObject object = with.get(i).getAsJsonObject();
                    String text = object.get("text").getAsString();
                    name.append(text);
                } catch (Exception e) {
                    body.append(" ").append(with.get(i).getAsString());
                }
            }
        }
        if (jsonObject.get("translate") != null) {
            String translate = jsonObject.get("translate").getAsString();
            if (!translate.equalsIgnoreCase("chat.type.text"))
                body.append(" ").append(translate);
            else
                name = new StringBuilder("<").append(name).append(">");
        }
        body.append(getExtra(jsonObject));
        if (body.toString().startsWith("<") && body.toString().contains("> ") && name.toString().isEmpty()) {//crude way to move player name to actual name field if the text is set up weird
            String s = body.toString().split("<")[1].split(">")[0];
            name.append(s);
            body = new StringBuilder(body.toString().replace("<" + s + "> ", ""));
        }
        if (body.toString().startsWith(" ")) {
            body = new StringBuilder(body.substring(1));
        }
        return new ChatMessage(name.toString(), body.toString());
    }

    public String getExtra(JsonObject jsonObject) {
        StringBuilder s = new StringBuilder();
        JsonArray extra = jsonObject.getAsJsonArray("extra");
        if (extra != null) {
            for (int i = 0; i < extra.size(); i++) {
                try {
                    JsonObject object = extra.get(i).getAsJsonObject();
                    String text = object.get("text").getAsString();
                    s.append(text);

                    if (object.get("extra") != null)
                        s.append(getExtra(object));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return s.toString();
    }
}