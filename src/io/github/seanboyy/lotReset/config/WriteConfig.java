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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Utility for writing config files
 * @author <a href=https://www.github.com/seanboyy>Seanboyy</a>
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
