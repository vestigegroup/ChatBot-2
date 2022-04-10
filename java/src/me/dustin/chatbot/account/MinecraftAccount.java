package me.dustin.chatbot.account;

import me.dustin.chatbot.account.login.MSLoginHelper;
import me.dustin.chatbot.account.login.MojangLoginHelper;
import me.dustin.chatbot.chat.ChatMessage;
import me.dustin.chatbot.helper.GeneralHelper;

public class MinecraftAccount {
    protected String email, password;
    private boolean loginAgain;
    public static class MicrosoftAccount extends MinecraftAccount {

        public MicrosoftAccount(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    public static class MojangAccount extends MinecraftAccount {
        private boolean isCracked;

        public MojangAccount(String username) {
            this.email = username;
            this.password = "";
            this.isCracked = true;
        }

        public MojangAccount(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isCracked() {
            return isCracked;
        }

        public void setCracked(boolean cracked) {
            isCracked = cracked;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginAgain() {
        return loginAgain;
    }

    public void setLoginAgain(boolean loginAgain) {
        this.loginAgain = loginAgain;
    }

    public Session login() {
        if (this instanceof MicrosoftAccount microsoftAccount) {
            GeneralHelper.print("Logging in to Microsoft account...", ChatMessage.TextColor.YELLOW);
            MSLoginHelper msLoginHelper = new MSLoginHelper(microsoftAccount);
            return msLoginHelper.login(s -> {});
        } else {
            GeneralHelper.print("Logging in to Mojang account...", ChatMessage.TextColor.YELLOW);
            MojangAccount mojangAccount = (MojangAccount)this;
            MojangLoginHelper mojangLoginHelper = new MojangLoginHelper(mojangAccount);
            return mojangLoginHelper.login();
        }
    }
}