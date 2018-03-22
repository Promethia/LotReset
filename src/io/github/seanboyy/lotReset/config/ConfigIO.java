package io.github.seanboyy.lotReset.config;

import io.github.seanboyy.lotReset.util.objects.Config;

import java.io.*;
import java.util.Properties;

/**
 * Utility for reading and writing configuration file
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
 * @since 4.0
 */
public class ConfigIO {
    /**
     *  Read the configuration file
     * @param filename string of the name/location of the file
     * @return <code>{@link Config} config</code> if no errors are encountered
     */
    public static Config read(String filename){
        Properties prop = new Properties();
        InputStream in;
        try{
            in = new FileInputStream(filename);
            prop.load(in);
            Config config = new Config(prop.getProperty("alphabet"), prop.getProperty("types"), prop.getProperty("worlds"), prop.getProperty("json"));
            in.close();
            return config;
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Write configuration file
     * @param filename name/location of the configuration file
     * @param a list of the alphabet in string form "A, B, C, ..."
     * @param t list of the types of lots in string form "T, T, ..."
     * @param w list of the worlds in string form "WLD, WLD, ..."
     * @param j URL location of the JSON file
     * @since 2.0
     */
    public static void writeFile(String filename, String a, String t, String w, String j){
        Properties prop = new Properties();
        OutputStream out;
        try{
            out = new FileOutputStream(filename);
            prop.setProperty("alphabet", a);
            prop.setProperty("types", t);
            prop.setProperty("worlds", w);
            prop.setProperty("json", j);
            prop.store(out, null);
            out.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
