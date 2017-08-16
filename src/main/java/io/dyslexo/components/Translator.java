package io.dyslexo.components;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by stefano on 22/12/2016.
 */
public class Translator {
    private static HashMap <String, Locale> supportedLocales;
    private static ResourceBundle translation;

    public Translator() {
        supportedLocales = new HashMap();
        supportedLocales.put("en", Locale.ENGLISH);
        supportedLocales.put("it", Locale.ITALIAN);
        supportedLocales.put("fr", Locale.FRENCH);
        supportedLocales.put("de", Locale.GERMAN);
    }

    public Translator(String language) {
        translation = ResourceBundle.getBundle("language.dictionary.language", supportedLocales.get(language), getClass().getClassLoader());
    }

    public static HashMap<String, Locale> getSupportedLocales() {
        return supportedLocales;
    }

    public static boolean isSupported(Locale locale) {
        return supportedLocales.get(locale.toString()) != null;
    }

    public static String getWord(String keyword) {
        String value = translation.getString(keyword);
        try {
            return new String(value.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
