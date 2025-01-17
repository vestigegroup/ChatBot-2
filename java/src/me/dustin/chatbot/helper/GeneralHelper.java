package me.dustin.chatbot.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.dustin.chatbot.ChatBot;
import me.dustin.chatbot.chat.ChatMessage;

import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;
import java.util.logging.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralHelper {


    public static final String ANSI_RESET = "\u001B[0m";
    private static final Pattern FORMATTING_CODE_PATTERN = Pattern.compile("(?i)\u00a7[0-9A-FK-OR]");

    public static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    public static final Gson prettyGson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private static Logger logger;

    public static void print(String s, ChatMessage.TextColor color) {
        String timeStampString = getCurrentTimeStamp();
        timeStampString = String.format("[%s] ", timeStampString);
        if (ChatBot.getGui() != null) {
            try {
                StyledDocument document = ChatBot.getGui().getOutput().getStyledDocument();
                document.insertString(document.getLength(), timeStampString, ChatMessage.TextColor.GRAY.getStyle());
                document.insertString(document.getLength(), s + "\n", ChatBot.getConfig() != null && ChatBot.getConfig().isColorConsole() ? color.getStyle() : null);

                ChatBot.getGui().getOutput().setCaretPosition(ChatBot.getGui().getOutput().getDocument().getLength());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ChatBot.getConfig() != null && ChatBot.getConfig().isColorConsole())
            System.out.println(timeStampString + color.getAnsi() + s + ANSI_RESET);
        else
            System.out.println(timeStampString + strip(s));
        if (logger != null)
            logger.info(s);
    }

    public static void printChat(ChatMessage chatMessage) {
        if (!chatMessage.getSenderName().isEmpty() && !chatMessage.getSenderName().startsWith("<"))
            chatMessage = new ChatMessage("<" + chatMessage.getSenderName() + (chatMessage.getSenderName().contains("§") ? "§f" : "") +">", chatMessage.getBody());
        String m = chatMessage.getMessage();
        if ((!m.contains("__COLOR_") && !m.contains("§")) || !ChatBot.getConfig().isColorConsole()) {
            print(strip(m), ChatMessage.TextColor.WHITE);
            return;
        }
        printColorText(chatMessage.getMessage());
        if (logger != null)
            logger.info(chatMessage.getMessage());
    }

    public static void printColorText(String text) {
        text = convertColors(text);
        ChatMessage.TextColor color = null;
        StyledDocument document = ChatBot.getGui() != null ? ChatBot.getGui().getOutput().getStyledDocument() : null;
        String timeStampString = String.format("[%s] ", getCurrentTimeStamp());
        try {
            if (document != null)
                document.insertString(document.getLength(), timeStampString, ChatMessage.TextColor.GRAY.getStyle());
            System.out.print(ANSI_RESET + timeStampString);
            for (String s : text.split("__COLOR_")) {
                if (s.length() == 0)
                    continue;
                boolean bl = s.startsWith("(") && s.length() > 12 && s.charAt(12) == ')';
                if (bl) {
                    int red = Integer.parseInt(s.substring(1, 4));
                    int green = Integer.parseInt(s.substring(5, 8));
                    int blue = Integer.parseInt(s.substring(9, 12));
                    Color c = new Color(red, green, blue);
                    color = ChatMessage.TextColor.getFromColor(c);
                }
                String s1 = bl ? s.substring(13) : s;
                if (color == null)
                    color = ChatMessage.TextColor.WHITE;
                if (document != null)
                    document.insertString(document.getLength(), s1, color.getStyle());
                System.out.print(color.getAnsi() + s1 + ANSI_RESET);
            }
            if (document != null) {
                document.insertString(document.getLength(), "\n", ChatMessage.TextColor.WHITE.getStyle());
                ChatBot.getGui().getOutput().setCaretPosition(ChatBot.getGui().getOutput().getDocument().getLength());
            }
            System.out.print(ANSI_RESET + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convertColors(String text) {
        for (ChatMessage.TextColor value : ChatMessage.TextColor.values()) {
            text = text.replace("§" + value.getChar(), "__COLOR_(%03d,%03d,%03d)".formatted(value.getColor().getRed(), value.getColor().getGreen(), value.getColor().getBlue()));
        }
        return text;
    }

    public static String strip(String string) {
        string = FORMATTING_CODE_PATTERN.matcher(string).replaceAll("");
        if (string.contains("__COLOR_")) {
            StringBuilder stringBuilder = new StringBuilder();
            String[] l = string.split("__COLOR_");
            for (String s1 : l) {
                if (s1.length() == 0)
                    continue;
                if (s1.startsWith("(") && s1.charAt(12) == ')')
                    stringBuilder.append(s1.substring(13));
            }
            string = stringBuilder.toString();
        }
        return string;
    }

    public static boolean matchUUIDs(String s, String s1) {
        return s.replace("-", "").equalsIgnoreCase(s1.replace("-", ""));
    }

    public static boolean matchUUIDs(UUID s, UUID s1) {
        return matchUUIDs(s.toString(), s1.toString());
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        return sdfDate.format(now);
    }

    public static String getCurrentTimeStamp(long ms) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date(ms);
        return sdfDate.format(now);
    }

    public static int countMatches(String in, String search) {
        Matcher m = Pattern.compile(search).matcher(in);
        int i = 0;
        while (m.find()) {
            i++;
        }
        return i;
    }

    public static String readFile(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void writeFile(File file, java.util.List<String> content) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            StringBuilder stringBuilder = new StringBuilder();
            content.forEach(string -> stringBuilder.append(string).append("\r\n"));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDurationString(long ms) {
        if (ms <= 0) {
            return "-";
        }

        long days = TimeUnit.MILLISECONDS.toDays(ms);
        ms -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(ms);
        ms -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(ms);
        ms -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(ms);

        StringBuilder sb = new StringBuilder();
        if (days > 0) {
            sb.append(days);
            sb.append("d ");
        }
        if (hours > 0) {
            String s = String.format("%02d", hours);
            if (s.startsWith("0"))
                s = s.substring(1);
            sb.append(s);
            sb.append("h ");
        }
        if (minutes > 0) {
            String s = String.format("%02d", minutes);
            if (s.startsWith("0"))
                s = s.substring(1);
            sb.append(s);
            sb.append("min ");
        }
        if (seconds > 0) {
            String s = String.format("%02d", seconds);
            if (s.startsWith("0"))
                s = s.substring(1);
            sb.append(s);
            sb.append("s");
        }

        return sb.toString();
    }

    public static HttpResponse httpRequest(String url, Object data, Map<String, String> headers, String requestMethod) {
        try {
            URL theURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) theURL.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.setConnectTimeout(10 * 1000);
            connection.setDoInput(true);
            if (headers != null)
                headers.forEach(connection::setRequestProperty);
            if (data != null) {
                connection.setDoOutput(true);
                byte[] bytes = new byte[0];
                if (data instanceof Map<?, ?> m) {
                    String encoded = encode((Map<Object, Object>) m);
                    bytes = encoded.getBytes();
                } else if (data instanceof String s) {
                    bytes = s.getBytes();
                }
                try (OutputStream outputStream = connection.getOutputStream()) {
                    outputStream.write(bytes);
                }
            }
            StringBuilder sb = new StringBuilder();
            int code = connection.getResponseCode();
            if (code >= 200 && code < 300) {
                BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                for (String line; (line = input.readLine()) != null; ) {
                    sb.append(line);
                    sb.append("\n");
                }
            }
            connection.disconnect();
            return new HttpResponse(sb.toString(), code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HttpResponse("", 404);
    }

    private static String encode(Map<Object, Object> map) {
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<?, ?> entry : map.entrySet())
            sj.add(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        return sj.toString();
    }

    public static void initLogger() {
        try {
            logger = Logger.getLogger("ChatBot");
            File logsFolder = new File(new File("").getAbsolutePath(), "logs");
            if (!logsFolder.exists())
                logsFolder.mkdirs();
            EasyFormatter easyFormatter = new EasyFormatter();
            FileHandler fileHandler = new FileHandler(new File(logsFolder, getCurrentTimeStamp() + ".txt").getAbsolutePath(), true);
            fileHandler.setFormatter(easyFormatter);

            logger.setUseParentHandlers(false);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static UUID uuidFromStringNoDashes(String uuid) {
        return UUID.fromString(uuid.replaceFirst(
                "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5"
        ));
    }

    public record HttpResponse(String data, int responseCode){}

    static class EasyFormatter extends Formatter {

        @Override
        public String format(LogRecord record) {
            return getCurrentTimeStamp(record.getMillis()) + ':' + record.getMessage() + '\n';
        }
    }
}
