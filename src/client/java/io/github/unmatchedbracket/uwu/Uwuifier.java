package io.github.unmatchedbracket.uwu;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import net.minecraft.text.Text;

public abstract class Uwuifier {

    public enum Mode {
        READ, WRITE
    }

    public static Mode processMode = Mode.READ;
    public static String rawText = "";
    public static int ind = 0;


    static public Text uwuify(Text input) {
        String json;
        try {
            json = Text.Serialization.toJsonString(input);
        } catch (com.google.gson.JsonParseException e) {
            if (e.getMessage().equals("Action not allowed: OPEN_FILE")) {
                // Screenshot message; we can't JSONify this
                // This is fine cuz we uwu the langfile so the screenshot message gets uwu'd anyway
                return input;
            } else {
                throw e;
            }
        }
        JsonElement tree = JsonParser.parseString(json);
        processMode = Mode.READ;
        rawText = "";
        uwujson(tree, null);
        processMode = Mode.WRITE;
        ind = 0;
        tree = uwujson(tree, null);
        String newjson = tree.toString();
        return Text.Serialization.fromJson(newjson);
    }

    static private JsonElement uwujson(JsonElement el, String key) {
        if (el.isJsonObject()) {
            var obj = el.getAsJsonObject();
            var keys = obj.asMap().keySet().toArray();
            for (int i = 0; i < keys.length; i++) {
                var key2 = (String)keys[i];
                var newEl = uwujson(obj.get(key2), key2);
                if (newEl != null) {
                    obj.remove(key2);
                    obj.add(key2, newEl);
                }
            }
            /* .forEach((JsonElement key2) -> {
            });*/
        } else if (el.isJsonArray()) {
            var obj = el.getAsJsonArray();
            var newList = new JsonArray();
            obj.asList().forEach((el2) -> {
                // System.out.println("list item:");
                // System.out.println(el2.toString());
                var newEl = uwujson(el2, null);
                if (newEl != null) {
                    newList.add(newEl);
                } else {
                    newList.add(el2);
                }
            });
            return newList;
        } else if (el.isJsonPrimitive()) {
            var obj = el.getAsJsonPrimitive();
            // System.out.println("aaaand");
            // System.out.println(obj.toString());
            // System.out.println(key);
            if (obj.isString() && (key == null || key.equals("text"))) {
                String origstring = obj.getAsString();
                switch (processMode) {
                    case READ:
                    rawText += origstring;
                        break;
                    case WRITE:
                    default:
                        int strlen = origstring.length();
                        String preText = rawText.substring(Math.max(ind-2, 0), ind);
                        String postText = "";//rawText.substring(strlen, Math.min(strlen+2, rawText.length()));
                        // System.out.println("ok so " + preText + "/" + origstring + "/" + postText);
                        String string = (
                            preText
                            + origstring +
                            postText
                        );
                        string = string.replaceAll("r", "w").replaceAll("R", "W").replaceAll("l", "w").replaceAll("L", "W");
                        string = string.replaceAll("(" + ".".repeat(preText.length()) + "[Nn])([aeiou]" + ".".repeat(Math.max(postText.length()-1, 0)) + ")", "$1y$2").replaceAll("(.[Nn])([AEIOU].)", "$1Y$2");
                        string = string.replaceAll("o([Vv][Ee])", "u$1").replaceAll("O([Vv][Ee])", "U$1");
                        // i dont really like this one
                        // string = string.replaceAll("(" + ".".repeat(preText.length()) + ")([Ii])(" + ".".repeat(postText.length()) + ")", "$1$2-$2$3");
                        ind += strlen;
                        return new JsonPrimitive(string.substring(preText.length(), string.length()-postText.length()));
                }
            }
        }
        return el;
    }

    public static String uwustring(String string) {
        return string.replaceAll("r", "w").replaceAll("R", "W").replaceAll("l", "w").replaceAll("L", "W")
            .replaceAll("([Nn])([aeiou])", "$1y$2").replaceAll("(.[Nn])([AEIOU].)", "$1Y$2")
            .replaceAll("o([Vv][Ee])", "u$1").replaceAll("O([Vv][Ee])", "U$1");
    }
}
