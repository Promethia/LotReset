package org.seanb.lotReset.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.seanb.lotReset.util.objects.Config;

/**
 * Utility for reading config files
 * @author <a href=https://github.com/seanboyy>Seanboyy</a>
 * @since 2.0
 */
public class ReadConfig {
	/** 
	 *  Read the configuration file
	 * @param filename string of the name/location of the file
	 * @return <code>{@link Config} config</code> if no errors are encountered
	 */
	public static Config read(String filename){
		Properties prop = new Properties();
		InputStream in = null;
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
}
