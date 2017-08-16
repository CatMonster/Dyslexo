package io.dyslexo.components;

import com.google.common.collect.ImmutableList;
import com.optimaize.langdetect.DetectedLanguage;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import io.dyslexo.graphics.LanguageSelector;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.*;

/**
 * Created by stefano on 26/03/2017.
 */
public class Language {

    public static Locale detect(String text) throws IOException, LineUnavailableException {
        List<LdLocale> preferredLang = new ArrayList<>();
        preferredLang.add(LdLocale.fromString("en"));
        preferredLang.add(LdLocale.fromString("it"));
        preferredLang.add(LdLocale.fromString("de"));
        preferredLang.add(LdLocale.fromString("fr"));
        List<LdLocale> languages = ImmutableList.copyOf(preferredLang);
        List<LanguageProfile> languageProfiles = new LanguageProfileReader().readBuiltIn(languages);

        LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard()).withProfiles(languageProfiles).build();

        List<DetectedLanguage> detectedProbabilities = languageDetector.getProbabilities(text);

        System.out.println(detectedProbabilities);

        if(detectedProbabilities.size() > 1)
            for(DetectedLanguage temp : detectedProbabilities)
                if (temp.getProbability() < 0.7)
                    return getLanguageFromUser();

        Locale detectedLanguage = new Locale(detectedProbabilities.get(0).getLocale().getLanguage());
        if(detectedLanguage == Locale.ENGLISH) {
            if(Boolean.parseBoolean(Settings.get("voices/voice[@locale='en_US']/default")))
                return Locale.US;
            else
                return Locale.UK;
        }
        else if(Translator.isSupported(detectedLanguage))
            return detectedLanguage;
        else {
            return getLanguageFromUser();
        }
    }

    /**
     *
     * @return It returns the selected Locale from user
     * @throws LineUnavailableException
     */
    private static Locale getLanguageFromUser() throws LineUnavailableException {
        LanguageSelector languageSelector = new LanguageSelector();
        try {
            Locale selectedLocale = languageSelector.getUserInput();
            if(selectedLocale == null)
                throw new Exception("Operation cancelled by user");
            else
                return selectedLocale;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
