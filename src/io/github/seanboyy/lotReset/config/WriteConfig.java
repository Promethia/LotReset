package io.github.seanboyy.lotReset.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Utility for writing config files
 * @author <a href=https://github.com/seanboyy>Seanboyy</a>
 * @since 2.0
 */
public class WriteConfig {
	/**
	 * Write configuration file
	 * @param filename name/location of the configuration file
	 * @param a list of the alphabet in string form "A, B, C, ..."
	 * @param t list of the types of lots in string form "T, T, ..."
	 * @param w list of the worlds in string form "WLD, WLD, ..."
	 * @param j URL location of the JSON file
	 */
	public static void writeFile(String filename, String a, String t, String w, String j){
		Properties prop = new Properties();
		OutputStream out = null;
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
