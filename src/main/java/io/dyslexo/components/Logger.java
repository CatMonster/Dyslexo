package io.dyslexo.components;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;

/**
 * Created by stefano on 01/02/2017.
 */
public class Logger {
    private static boolean termLog;
    private static String path;

    public static java.util.logging.Logger logger;

    public Logger() throws IOException {
        termLog = true;
        path = null;
    }

    public Logger(String path) throws IOException {
        Logger.path = path;

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
        String logFilePath = path + "/DyslexoTTS_" + dateFormat.format(new Date()) + ".log";
        File logFile = new File(logFilePath);
        logFile.createNewFile();

        termLog = false;

        FileHandler fileHandler = new FileHandler(logFilePath, true);
        logger = java.util.logging.Logger.getLogger("/Dyslexo");
        logger.addHandler(fileHandler);
    }

    public Logger(String path, boolean termLog) throws IOException {
        Logger.path = path;

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
        String logFilePath = path + "/DyslexoTTS_" + dateFormat.format(new Date()) + ".log";
        File logFile = new File(logFilePath);
        logFile.createNewFile();

        Logger.termLog = termLog;

        FileHandler fileHandler = new FileHandler(logFilePath, true);
        logger = java.util.logging.Logger.getLogger("Dyslexo");
        logger.addHandler(fileHandler);
    }

    public static void appendToLog(Level level, String message) {
        if(termLog)
            System.err.println(level + ": " + message);
        if(path != null)
            logger.log(level, message);
    }
}