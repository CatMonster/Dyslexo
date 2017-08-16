package io.dyslexo.components.audio.synthesis;

import io.dyslexo.components.Settings;
import io.dyslexo.components.Logger;
import marytts.LocalMaryInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.sound.sampled.AudioInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;

/**
 * Created by stefano on 03/02/2017.
 */
public class TextProcessor extends Thread {
    private final List<String> pieces;
    private final Locale locale;
    private final String speed;
    private final BlockingQueue<Optional<AudioInputStream>> outputQueue;

    public TextProcessor(Locale locale, String speed, List<String> pieces, BlockingQueue<Optional<AudioInputStream>> outputQueue) {
        super("Dyslexo Text Processer");
        this.pieces = pieces;
        this.locale = locale;
        this.speed = speed;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        try {
            for (String piece : pieces) {
                System.out.println("Renderizzo pezzo: " + piece);
                LocalMaryInterface marytts = new LocalMaryInterface();

                Set<String> voices = marytts.getAvailableVoices(locale);
                for (String temp : voices)
                    Logger.appendToLog(Level.INFO, "Available voice for " + locale.toString() + ": " + temp);

                marytts.setVoice(Settings.get("voices/voice[@locale='" + String.valueOf(locale) + "']/name"));
                Logger.appendToLog(Level.INFO, "Used voice: " + Settings.get("voices/voice[@locale='" + String.valueOf(locale) + "']/name"));

                marytts.setInputType("RAWMARYXML");
                AudioInputStream audioRender = marytts.generateAudio(genXML(locale, speed, piece));
                //saveXmlToFile(genXML(locale, speed, piece), "test.xml");
                System.out.println("Fine rendering, aggiungo alla coda");
                outputQueue.put(Optional.of(audioRender));
            }

            System.out.println("Finita frase");
            outputQueue.put(Optional.empty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Document genXML(Locale locale, String speed, String phrase) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element maryxml = doc.createElement("maryxml");
        doc.appendChild(maryxml);

        maryxml.setAttribute("xmlns", "http://mary.dfki.de/2002/MaryXML");
        maryxml.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        maryxml.setAttribute("version", "0.5");
        maryxml.setAttribute("xml:lang", String.valueOf(locale));

        Element p = doc.createElement("p");
        maryxml.appendChild(p);
        Element prosody = doc.createElement("prosody");
        prosody.setAttribute("rate", speed);
        prosody.setAttribute("volume", "medium");

        p.appendChild(prosody);

        prosody.appendChild(doc.createTextNode(phrase));

        return doc;
    }

    public void saveXmlToFile(Document doc, String path) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        StreamResult output = new StreamResult(new File(path));
        DOMSource input = new DOMSource(doc);
        transformer.transform(input, output);
        Logger.appendToLog(Level.INFO, "Xml saved to: " + path);
    }
}
