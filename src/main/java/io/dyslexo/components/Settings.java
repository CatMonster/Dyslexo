package io.dyslexo.components;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Locale;

/**
 * Created by stefano on 03/01/2017.
 */
public class Settings {
    private static XMLConfiguration config;

    public Settings(File file) throws ConfigurationException {
        try {
            config = new XMLConfiguration(file);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        config.setExpressionEngine(new XPathExpressionEngine());
    }

    /**
     * It gets the value of a specified setting
     * @param path path of the desired setting
     * @return returns the current value of the selected setting
     */
    public static String get(String path) {
        return config.getString(path);
    }

    /**
     * It sets the value of a specified setting
     * @param path path of the desired setting
     * @param value value to set into specified path
     */
    public static void set(String path, String value) {
        config.setProperty(path,value);
        try {
            config.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return It returns a Locale of the preferred language to execute the speech, if the value it's automatic, it returns null
     */
    public static Locale getSpeechLanguage(){
        String language = get("language/speech");
        switch (language){
            case "auto" :
                return null;
            case "en" :
                return Locale.ENGLISH;
            case "it" :
                return Locale.ITALIAN;
            case "fr" :
                return Locale.FRENCH;
            case "de" :
                return Locale.GERMAN;
        }
        return null;
    }

    /**
     *
     * @return It returns a Locale of the preferred language to translate the GUI, if the value it's automatic, it returns null
     */
    public static String getProgramLanguage(){
        String language = get("language/translator");
        switch (language) {
            case "auto" :
                String defaultLocale = Locale.getDefault().toString().substring(0, 2);
                if(Translator.isSupported(new Locale(defaultLocale)))
                    return defaultLocale;
                else
                    return "en";
            case "en" :
                return "en";
            case "it" :
                return "it";
            case "fr" :
                return "fr";
            case "de" :
                return "de";
        }
        return null;
    }

    public static void setStartup() {

    }

    /**
     * Given a DOM Document it generate a settings.xml file
     * @param doc DOM Document
     * @throws TransformerException
     * @throws URISyntaxException
     */
    public static void genSettingsFile(Document doc) throws TransformerException, URISyntaxException {
        File file = new File("settings.xml");
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        StreamResult output = new StreamResult(file);
        DOMSource input = new DOMSource(doc);
        transformer.transform(input, output);
    }
}