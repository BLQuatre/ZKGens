package fr.blquatre.zknetwork.zkgens.utils;

import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class Messages {

    private final static String prefix = "&8[&6ZKGens&8] ";
    public static String format(String msg, boolean usePrefix) {

        if (usePrefix) {
            msg = prefix + msg;
        }

        return msg.replaceAll("&", "§");
    }
    public static Component formatC(String msg, boolean usePrefix) {

        if (usePrefix) {
            msg = prefix + msg;
        }

        return Component.text(msg.replaceAll("&", "§"));
    }

    public static List<Component> formatListC(List<String> list) {
        List<Component> componentList = new ArrayList<>();
        for (String element : list) {
            componentList.add(Component.text(element));
        }
        return componentList;
    }
}
