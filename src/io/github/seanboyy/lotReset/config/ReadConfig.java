/*
 * This is a part of LotReset, licensed under the MIT License (MIT).
 * 
 * Copyright (c) 2016 Seanboyy (Sean Bamford)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTIBILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIBLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.seanboyy.lotReset.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.github.seanboyy.lotReset.util.objects.Config;

/**
 * Utility for reading config files
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
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