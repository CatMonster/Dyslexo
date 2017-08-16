package io.dyslexo.system;

/**
 * Created by stefano on 16/12/2016.
 */
public class Software {
    static public String macOS = "macOS";
    static public String windows = "windows";
    static public String linux = "linux";

    public static String getOS() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if(os.contains("windows"))
                return windows;
            if(os.contains("mac"))
                return macOS;
            if(os.contains("linux"))
                return linux;
            else
                throw new Exception("OS not supported");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static String getVersion() {
        return System.getProperty("os.version");
    }

    public static String getArchitecture() {
        return System.getProperty("os.arch");
    }

    public static String getUser() {
        return System.getProperty("user.name");
    }
}